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

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Data.CourseTypes;
import Data.DatabaseInformationQuerier;
import GPS.RadiusChecker;


public class HomePage extends MenuViewActivity  {

    private Button sim;
    private EditText searchedCourse;
    private EditText searchedLocation;
    private MenuViewActivity current = this;
    private SeekBar radiusBar;
    private TextView radiusDisplay;
    private double longitude;
    private double latitude;
    int sizeOfRadius = 0;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    private void updateRadius(int progress){
        sizeOfRadius = progress*5;
        radiusDisplay.setText(String.valueOf(sizeOfRadius) + " km");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //This creates the database querier with the database, this will need to be passed to other activities that require
         final DatabaseInformationQuerier databaseInfomationQuerier = new DatabaseInformationQuerier(database);

        radiusBar = (SeekBar) findViewById(R.id.radiusBar);
        sizeOfRadius = radiusBar.getProgress();
        radiusDisplay = (TextView) findViewById(R.id.radiusText);
        radiusBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateRadius(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sim = (Button) findViewById(R.id.simSearch);
        searchedCourse = (EditText) findViewById(R.id.courseNameEntered);
        searchedLocation = (EditText) findViewById(R.id.locationNameEntered);
        final RadioGroup courseTypeButton = (RadioGroup) findViewById(R.id.coursetype);
        final Geocoder loc = new Geocoder(this);


        Log.d("i should be true " , String.valueOf(loc.isPresent()));
        sim.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                int selectedid = courseTypeButton.getCheckedRadioButtonId();
                RadioButton button = (RadioButton) (findViewById(selectedid));
                CourseTypes courseType = button.getText().equals("full time courses") ? CourseTypes.FULL_TIME : CourseTypes.PART_TIME;

                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), SearchResults.class);
                databaseInfomationQuerier.setIntent(intent);
                databaseInfomationQuerier.setCurrent(current);
                if(!searchedLocation.getText().equals("My location")){
                try {
                    List<Address> addresses = loc.getFromLocationName(searchedLocation.getText().toString(), 1);
                    longitude = addresses.get(0).getLongitude();
                    latitude = addresses.get(0).getLatitude();
                } catch (IOException e) {
                    e.printStackTrace();
                }}
                if(longitude != 0.0 && latitude != 0.0){
                    Log.d(String.valueOf(longitude) , String.valueOf(latitude));
                    RadiusChecker.getHitsAroundLocation(sizeOfRadius, longitude
                                                            , latitude
                                                            , searchedCourse.getText().toString(),
                                                            databaseInfomationQuerier,courseType);
                }
                else {
                    databaseInfomationQuerier.getAllCoursesByCourseName(searchedCourse.getText().toString(), courseType); //need to add an option to select the course type

                }
            }
        });




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
