package com.example.chris.mystats_univeristy;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import Data.Course;
import Data.CourseTypes;
import Data.DatabaseInformationQuerier;



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
        Geocoder test = new Geocoder(this, Locale.ENGLISH);
        Log.d("working " , String.valueOf(Geocoder.isPresent()));
        Log.d("tseting " , String.valueOf(test.isPresent()));
        List<Address> addresses = new ArrayList<>();
        try {
            while(addresses.size() == 0){
                addresses = test.getFromLocation(54.569771 , -1.235611 , 1);
            }
            Log.d("i am a test", addresses.get(0).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        sim.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), SearchResults.class);
                databaseInfomationQuerier.setIntent(intent);
                databaseInfomationQuerier.setCurrent(current);
                databaseInfomationQuerier.getAllCoursesByCourseName(searchedCourse.getText().toString(), CourseTypes.FULL_TIME); //need to add an option to select the course type

            }
        });




    }

}
