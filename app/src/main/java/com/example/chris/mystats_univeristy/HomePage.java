package com.example.chris.mystats_univeristy;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.IOException;
import java.util.List;
import Data.CourseTypes;
import Data.DatabaseInformationQuerier;
import GPS.RadiusChecker;


public class HomePage extends MenuViewActivity  {

    private Button sim;
    private EditText searchedCourse;
    private EditText searchedLocation;
    private MenuViewActivity current = this;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //This creates the database querier with the database, this will need to be passed to other activities that require
         final DatabaseInformationQuerier databaseInfomationQuerier = new DatabaseInformationQuerier(database);

        sim = (Button) findViewById(R.id.simSearch);
        searchedCourse = (EditText) findViewById(R.id.courseNameEntered);
        searchedLocation = (EditText) findViewById(R.id.locationNameEntered);
        final Geocoder loc = new Geocoder(this);


        Log.d("i should be true " , String.valueOf(loc.isPresent()));
        sim.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), SearchResults.class);
                databaseInfomationQuerier.setIntent(intent);
                databaseInfomationQuerier.setCurrent(current);
                List<Address> addresses = new ArrayList<>();
                try {
                    addresses = loc.getFromLocationName(searchedLocation.getText().toString(), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(addresses.size() >0){
                    RadiusChecker.getHitsAroundLocation(50, addresses.get(0).getLongitude()
                                                            , addresses.get(0).getLatitude()
                                                            , searchedCourse.getText().toString(),
                                                            databaseInfomationQuerier, CourseTypes.FULL_TIME);
                }
                else {
                    databaseInfomationQuerier.getAllCoursesByCourseName(searchedCourse.getText().toString(), CourseTypes.FULL_TIME); //need to add an option to select the course type

                }
            }
        });




    }

}
