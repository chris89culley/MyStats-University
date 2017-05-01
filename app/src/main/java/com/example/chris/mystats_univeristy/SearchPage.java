package com.example.chris.mystats_univeristy;


import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.channguyen.rsv.RangeSliderView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.util.List;

import Animations.AnimatorUtils;
import Data.CourseTypes;
import Data.DatabaseInformationQuerier;
import GPS.MyLocationListener;
import GPS.RadiusChecker;


//A
public class SearchPage extends MenuViewActivity  {

    private ImageButton getLocation; //Button that sets the longitude and latitude to the users current location.
    private Button searchButton; //The button pressed to conduct a search
    private final int MAX_KM_RADIUS_SEARCH = 200; //The max radius a user is allowed to search
    private final int SEEK_BUTTON_RANGE = 100;
    private final int RADIUS_VALUE_MODIFIER = MAX_KM_RADIUS_SEARCH/SEEK_BUTTON_RANGE; //The modifier to the radius value (since the normal value only goes up to 100)
    private EditText searchedCourseEditTextField; //The text field where the user enters the course name they wish to search
    private EditText searchedLocationEditTextField; //The location field the user can choose to enter to search around (can be a location or a university or blank)
    private MenuViewActivity currentActivity = this;
    private SeekBar radiusBar; //This is the radius bar which can be slid by the user indicating a larger/smaller radius search
    private TextView radiusDisplay; //The text display of the current radius selected
    private double longitude; //The longitude of the location to be searched around
    private double latitude; // The latitude of the location to be searched around
    private int sizeOfRadius = 0; // The radius which is to be searched around the long and lat
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference(); //The database reference
    //This creates the database querier with the database, this will need to be passed to other activities that require
    private final DatabaseInformationQuerier databaseInfomationQuerier = new DatabaseInformationQuerier(database);
    private Geocoder longLatGrabber;  //utility class used to grab long and lat of a location
    private RadioGroup typeOfCourseSelector; //This radio button group allows the user to select the different study options
    private boolean shouldGetLocationFromLocationEditText = false;
    private boolean shouldGetLocationFromUserData = false;
    private LocationManager locationManager; //Class that handles the information sent by the LocationListener
    private Typeface marketDeco ;
    private AVLoadingIndicatorView loadingIcon;
    private ImageView searchArm;


    /**
     * This method updates the radius text view 'radiusDisplay' with the current selected search radius so that the
     * user can decide to increase or decrease
     * @param progress - The seekbars current percent across ie out of a 100
     */
    private void updateRadius(int progress, boolean full){
        if(full){
            sizeOfRadius = progress*500;
            radiusDisplay.setText("Whole UK search");
        }
        else{
            sizeOfRadius = progress*RADIUS_VALUE_MODIFIER;
            radiusDisplay.setText("within " +  String.valueOf(sizeOfRadius) + " km of");
        }
    }


    /**
     * This method sets up the radius bar and initialised the radius the user searches around a location
     */
    private void setUpRadiusBar(){
        radiusBar = (SeekBar) findViewById(R.id.radiusBar);
        sizeOfRadius =(int) radiusBar.getProgress();
        radiusBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateRadius(progress, (progress == SEEK_BUTTON_RANGE));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * Sets up the text displaying the current search radius to the user
     */
    private void setUpRadiusTextDisplay(){
        radiusDisplay = (TextView) findViewById(R.id.radiusText);
        radiusDisplay.setTypeface(marketDeco);
    }

    /**
     * This method sets up a listener to watch the location field (where the user will enter a location)
     * if the user starts to enter a location then it will mean they do not want to search around their
     * current location and  instead want to search around the entered location
     */
    private void watchForLocationTextToChange() {
        searchedLocationEditTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                shouldGetLocationFromUserData = false;
                shouldGetLocationFromLocationEditText = true;
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    /**
     * This method creates a shake for the passed in edit text indicating that it needs to be filled in
      */
    private void shakeTheCourseName(EditText textToBeShaken){
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        shake.setInterpolator(new CycleInterpolator(5));
        textToBeShaken.startAnimation(shake);
        showEditTextNotFilledInError(textToBeShaken);
    }

    /**
     * Creates an error which is shown to the user when the passed text field isn't filled in
     * @param textWithError - The edit text box with the error
     */
    private void showEditTextNotFilledInError(EditText textWithError){
        textWithError.setError("You need to fill me in!");
    }

    /**
     * Sets up the text fields allowing the user to enter a course and location name
     */
    private void setUpSearchOptions(){
        searchedCourseEditTextField = (EditText) findViewById(R.id.courseNameEntered);
        searchedCourseEditTextField.setTypeface(marketDeco);
        searchedLocationEditTextField = (EditText) findViewById(R.id.locationNameEntered);
        searchedLocationEditTextField.setTypeface(marketDeco);
    }

    /**
     * Sets up a button which when pressed starts a search
     */
    private void setUpSearchButton(){
        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setTypeface(marketDeco);
    }

    /**
     * Sets up the button group allowing the user to select a type of course ie fulltime
     */
    private void setUpCourseTypeRadioButtons(){
        typeOfCourseSelector = (RadioGroup) findViewById(R.id.coursetype);
        RadioButton r = (RadioButton) findViewById(R.id.fulltime);
        r.setTypeface(marketDeco);
        r = (RadioButton) findViewById(R.id.parttime);
        r.setTypeface(marketDeco);

    }

    /**
     * Gets the type of  course the user has selected from the radio button
     * @return - The text from the selected radio button
     */
    private String getTheCurrentSelectedRadioButtonCourseTypeText(){
        return ((RadioButton) findViewById(typeOfCourseSelector.getCheckedRadioButtonId())).getText().toString();
    }

    /**
     * Gets the course type based on the text extracted from the radio button
     * @return - The type of course the user wishes to search
     */
    private CourseTypes getTheTypeOfCourseSelected(){
        return getTheCurrentSelectedRadioButtonCourseTypeText().equals("full time") ? CourseTypes.FULL_TIME : CourseTypes.PART_TIME;

    }

    /**
     * Sets up the widgets on home page
     */
    private void setUpWidgetsOnHomePage(){
        setUpRadiusBar();
        setUpRadiusTextDisplay();
        setUpSearchOptions();
        setUpSearchButton();
        setUpCourseTypeRadioButtons();
        setUpgetLocationButton();
        setUpLoadingIcon();

    }

    /**
     * Sets up the loading icon to be displayed while a search is being made
     */
    private void setUpLoadingIcon(){
        loadingIcon = (AVLoadingIndicatorView) findViewById(R.id.loadingIcon);
        loadingIcon.hide();
    }

    /**
     * Sets the button up that sets the longitude and latitude to the current location.
     */
    private void setUpgetLocationButton() {
        getLocation = (ImageButton) findViewById(R.id.getLocation);
    }

    /**
     * Gets the location the user wishes to search around
     * @return - The location the user wants to search
     */
    private String getTheLocationFieldText(){
        return searchedLocationEditTextField.getText().toString();
    }

    /**
     * Finds out if the location field is not empty
     * @return true if the location field is not empty
     */
    private boolean theLocationFieldIsntMyLocation(){
        return !getTheLocationFieldText().equals("My Location");
    }

    /**
     * Updates the longitude and latitude to be searched with the long and lat of the passed location name
     * @param location  - The location where the long and lat is to be updated from
     */
    private void updateLongAndLatWithLocationGiven(String location){
        try{
            List<Address> addresses = longLatGrabber.getFromLocationName(location, 1);
            if(addresses.size() > 0){
                longitude = addresses.get(0).getLongitude();
                latitude = addresses.get(0).getLatitude();
            }
            else {
                shouldGetLocationFromLocationEditText = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Updates the database querier with the intent and current activity so that it can create
     * the search result page
     * @param intent
     */
    private void updateInfoQuerierWithIntentIntentions(Intent intent){
        databaseInfomationQuerier.setIntent(intent);
        databaseInfomationQuerier.setCurrent(currentActivity);

    }

    /**
     * Gets the course name that the user wishes to search for
     * @return - The course name
     */
    private String getTheCourseToBeSearched(){
        return searchedCourseEditTextField.getText().toString();
    }

    private boolean locationEditTextIsntEmpty(){
        return getTheLocationFieldText().length() > 0 && getTheLocationFieldText() != "My Location";
    }

    /**
     * This method deals with the event that the search button is pressed. It determines whether the
     * user wishes to search around their current location, an entered location or neither and makes a request
     * to the database either through radius checker or directly to the database querier
     *
     */
    private void handleSearchButtonPressed(){

        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(getTheCourseToBeSearched().isEmpty()){
                    shakeTheCourseName(searchedCourseEditTextField);
                    return;
                }

                loadingIcon.show();
                Intent intent = new Intent(view.getContext(), SearchResults.class);
                updateInfoQuerierWithIntentIntentions(intent);
                if(shouldGetLocationFromLocationEditText && locationEditTextIsntEmpty()) {
                    updateLongAndLatWithLocationGiven(getTheLocationFieldText());
                } else if (shouldGetLocationFromUserData) {
                    if(locationPermissionCheck() == true){
                    getUsersLocationalData();}
                }
                if(shouldGetLocationFromLocationEditText || shouldGetLocationFromUserData) {
                    RadiusChecker.getHitsAroundLocation(sizeOfRadius,
                            longitude,
                            latitude,
                            getTheCourseToBeSearched(),
                            databaseInfomationQuerier,
                            getTheTypeOfCourseSelected());
                }
                else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    databaseInfomationQuerier.getAllCoursesByCourseName(getTheCourseToBeSearched(),
                            getTheTypeOfCourseSelected());
                }
            }});}

    /**
     * Resets the page to the default starting values
     */
    private void resetPage(){
        longitude = 0.0;
        latitude = 0.0;
        shouldGetLocationFromLocationEditText = true;
        shouldGetLocationFromUserData = false;
    }

    /**
     * Handles the action listener for the location button, calls the setter to get permissions and set the lang and lat to the users current location.
     * Then places the text "My Location" in the text field to allow the user to know that we are using there own location
     */
    public void handlelocationButtonClick(){
        getLocation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (locationPermissionCheck() == true){
                    searchedLocationEditTextField.setText("My Location");
                    shouldGetLocationFromLocationEditText = false;
                shouldGetLocationFromUserData = true;}
            }
        });}

    private void setUpFonts(){
       marketDeco = Typeface.createFromAsset(this.getAssets(), "fonts/Market_Deco.ttf");
    }

    /**
     * Animates the search arm by rotating it slightly and translating across a bit of the x axis
     */
    private void animateSearchArm(){
        searchArm = (ImageView) findViewById(R.id.searchArmImage);
        Animator anim = ObjectAnimator.ofPropertyValuesHolder(searchArm, AnimatorUtils.rotation(5f, -10f), AnimatorUtils.translationX(300f, 50f));
        anim.setDuration(1000);
        anim.start();
    }

    /**
     * animates the arm when the app is resumed
     */
    @Override
    protected  void onResume(){
        animateSearchArm();
        super.onResume();
    }

    /**
     * Creates the home page and initialises the listeners
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        animateSearchArm();
        setUpFonts();
        setUpWidgetsOnHomePage();
        longLatGrabber = new Geocoder(this);
        watchForLocationTextToChange();
        handlelocationButtonClick();
        handleSearchButtonPressed();

    }

    /**
     * This Method sets the latitude and logitude variable to the latitude and longitude of the devices current location
     */
    public void getUsersLocationalData() {

        locationManagerInitialiser(0,0);
        //Assigns the last location got by the location listener and adds it into the location manager
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }}

    /**
     * /**
     * This method initilalisers the Location Manager and starts the Location listener and begins updating the current
     * location in conjunction to however many seconds or hte distance changed is.
     * @param mili
     * @param distance
     */
    public void locationManagerInitialiser(int mili, int distance) {
        //Checks if the
        if (locationPermissionCheck() == true){

            //Initialisees the Location manager witht he Location services.
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            //Starts the lcoation listener to start listening to the where the location is updating every 0 miliseconds or 0 distance moved
            //Then assigns the the location listener to the location manager
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, mili, distance, new MyLocationListener());
            return;
        }else{

        }

    }

    /**
     * Use to check if the device has allowed the app to use the locaiton software inbuilt to it
     * Prompts the user to turn on location services if not already given permission to use
     */
    public boolean locationPermissionCheck() {
        //Checks if the User already has the permissions granted
        //Asks the user to give the app permission to use locaitonal services
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            return true;}
        else {
            return true;
        }

    }

    /**
     * This is called by the request permission and checks that it has been allowed if it has it returns and
     * allows the program to continue, else it asks the user to grant permission again.
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                return;
            } else {
                searchedLocationEditTextField.setText("");
                shouldGetLocationFromLocationEditText = true;
                shouldGetLocationFromUserData = false;


                Toast toast = Toast.makeText(getApplicationContext(), "Location Services Disabled", Toast.LENGTH_SHORT);
                toast.show();

                return;
            }
        }

    }
}
