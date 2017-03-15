package com.example.chris.mystats_univeristy;


import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;

import Data.CourseTypes;
import Data.DatabaseInformationQuerier;
import GPS.MyLocationListener;
import GPS.RadiusChecker;


public class HomePage extends MenuViewActivity  {

    private Button searchButton; //The button pressed to conduct a search
    private final int MAX_KM_RADIUS_SEARCH = 500; //The max radius a user is allowed to search
    private final int RADIUS_VALUE_MODIFIER = MAX_KM_RADIUS_SEARCH/100; //The modifier to the radius value (since the normal value only goes up to 100)
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
    private LocationManager locationManager;

    /**
     * This method updates the radius text view 'radiusDisplay' with the current selected search radius so that the
     * user can decide to increase or decrease
     * @param progress - The seekbars current percent across ie out of a 100
     */
    private void updateRadius(int progress){
        sizeOfRadius = progress*RADIUS_VALUE_MODIFIER;
        radiusDisplay.setText("within " +  String.valueOf(sizeOfRadius) + " km of");
    }


    /**
     * This method sets up the radius bar and initialised the radius the user searches around a location
     */
    private void setUpRadiusBar(){
        radiusBar = (SeekBar) findViewById(R.id.radiusBar);
        sizeOfRadius = radiusBar.getProgress();
        radiusBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateRadius(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    /**
     * Sets up the text displaying the current search radius to the user
     */
    private void setUpRadiusTextDisplay(){
        radiusDisplay = (TextView) findViewById(R.id.radiusText);
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
     * Sets up the text fields allowing the user to enter a course and location name
     */
    private void setUpSearchOptions(){
        searchedLocationEditTextField = (EditText) findViewById(R.id.locationNameEntered);
        searchedCourseEditTextField = (EditText) findViewById(R.id.courseNameEntered);
    }

    /**
     * Sets up a button which when pressed starts a search
     */
    private void setUpSearchButton(){
        searchButton = (Button) findViewById(R.id.simSearch);
    }

    /**
     * Sets up the button group allowing the user to select a type of course ie fulltime
     */
    private void setUpCourseTypeRadioButtons(){
        typeOfCourseSelector = (RadioGroup) findViewById(R.id.coursetype);
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
        return getTheCurrentSelectedRadioButtonCourseTypeText().equals("full time courses") ? CourseTypes.FULL_TIME : CourseTypes.PART_TIME;

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
    private boolean theLocationFieldIsntEmpty(){
        return getTheLocationFieldText().length()>0;
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

    /**
     * Get the users locational data
     */
    private void getUsersLocationalData() {
         Log.d("not yet implemented" , "not implemented");
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
                Intent intent = new Intent(view.getContext(), SearchResults.class);
                updateInfoQuerierWithIntentIntentions(intent);
                if(shouldGetLocationFromLocationEditText && theLocationFieldIsntEmpty()) {
                    updateLongAndLatWithLocationGiven(getTheLocationFieldText());
                } else if (shouldGetLocationFromUserData) {
                    getUsersLocationalData();
                }
                if(shouldGetLocationFromUserData || shouldGetLocationFromUserData) {
                   RadiusChecker.getHitsAroundLocation(sizeOfRadius,
                                                        longitude,
                                                        latitude,
                                                        getTheCourseToBeSearched(),
                                                        databaseInfomationQuerier,
                                                        getTheTypeOfCourseSelected());
                }
                else {
                    databaseInfomationQuerier.getAllCoursesByCourseName(getTheCourseToBeSearched(),
                                              getTheTypeOfCourseSelected());
                }
    }});}


    /**
     * Creates the home page and initialises the listeners
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setUpWidgetsOnHomePage();
        longLatGrabber = new Geocoder(this);
        watchForLocationTextToChange();
        handleSearchButtonPressed();
    }


    //The below has not yet been refactored since it is being changed by terry in another branch

    /**
     * This Method sets the latitude and logitude variable to the latitude and longitude of the devices current location
     */
    public void currentLocationSetter() {

        locationManagerInitialiser(0,0);
            //Assigns the last location got by the location listener and adds it into the location manager
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }

        }

    /**
     * /**
     * This method initilalisers the Location Manager and starts the Location listener and begins updating the current
     * location in conjunction to however many seconds or hte distance changed is.
     * @param mili
     * @param distance
     */
    public void locationManagerInitialiser(int mili, int distance) {
        //Checks if the
        if (!locationPermissionCheck() == true){
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

            return true;

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
                locationPermissionCheck();
            }
        }

    }
}
