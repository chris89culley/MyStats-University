package com.example.chris.mystats_univeristy;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
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

    /**
     * This method updates the radius text view 'radiusDisplay' with the current selected search radius so that the
     * user can decide to increase or decrease
     * @param progress - The seekbars current percent across ie out of a 100
     */
    private void updateRadius(int progress){
        sizeOfRadius = progress*RADIUS_VALUE_MODIFIER;
        radiusDisplay.setText("within " +  String.valueOf(sizeOfRadius) + " km of");
    }


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

    private void setUpRadiusTextDisplay(){
        radiusDisplay = (TextView) findViewById(R.id.radiusText);
    }

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
    private void setUpSearchOptions(){
        searchedLocationEditTextField = (EditText) findViewById(R.id.locationNameEntered);

        searchedCourseEditTextField = (EditText) findViewById(R.id.courseNameEntered);
    }

    private void setUpSearchButton(){
        searchButton = (Button) findViewById(R.id.simSearch);
    }

    private void setUpCourseTypeRadioButtons(){
        typeOfCourseSelector = (RadioGroup) findViewById(R.id.coursetype);
    }

    private String getTheCurrentSelectedRadioButtonCourseTypeText(){
        return ((RadioButton) findViewById(typeOfCourseSelector.getCheckedRadioButtonId())).getText().toString();
    }

    private CourseTypes getTheTypeOfCourseSelected(){
        return getTheCurrentSelectedRadioButtonCourseTypeText().equals("full time courses") ? CourseTypes.FULL_TIME : CourseTypes.PART_TIME;

    }
    private void setUpWidgetsOnHomePage(){
        setUpRadiusBar();
        setUpRadiusTextDisplay();
        setUpSearchOptions();
        setUpSearchButton();
        setUpCourseTypeRadioButtons();

    }

    private String getTheLocationFieldText(){
        return searchedLocationEditTextField.getText().toString();
    }

    private boolean theLocationFieldIsntEmpty(){
        return getTheLocationFieldText().length()>0;
    }

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

    private void updateInfoQuerierWithIntentIntentions(Intent intent){
        databaseInfomationQuerier.setIntent(intent);
        databaseInfomationQuerier.setCurrent(currentActivity);

    }

    private void getUsersLocationalData() {
         Log.d("not yet implemented" , "not implemented");
    }

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
                            searchedCourseEditTextField.getText().toString(),
                            databaseInfomationQuerier
                            ,getTheTypeOfCourseSelected());
                }
                else {
                    databaseInfomationQuerier.getAllCoursesByCourseName(searchedCourseEditTextField.getText().toString(),
                            getTheTypeOfCourseSelected()); //need to add an option to select the course type
                }
    }});}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setUpWidgetsOnHomePage();
       longLatGrabber = new Geocoder(this);
        watchForLocationTextToChange();
        handleSearchButtonPressed();
    }


    public void areaSearch(){
// Or, use GPS location data:
// String locationProvider = LocationManager.GPS_PROVIDER;
        String locationProvider = LocationManager.NETWORK_PROVIDER;

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            double latitude=location.getLatitude();
            double  longitude=location.getLongitude();
        }
    }


}
