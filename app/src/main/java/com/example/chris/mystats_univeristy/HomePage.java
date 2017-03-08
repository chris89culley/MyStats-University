package com.example.chris.mystats_univeristy;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import Data.CourseDetail;
import Data.CourseTypes;
import Data.DatabaseInformationQuerier;



public class HomePage extends MenuViewActivity  {


    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //This creates the database querier with the database, this will need to be passed to other activities that require
        // access to the database information

         DatabaseInformationQuerier databaseInfomationQuerier = new DatabaseInformationQuerier(database);

        //This is an example of how to get all courses by course name and course type
        databaseInfomationQuerier.getAllCoursesByCourseName("Computing" , CourseTypes.FULL_TIME);
       // databaseInfomationQuerier.getACourseByCoursenameAndUniversityName("History", "Teesside University", CourseTypes.FULL_TIME);





    }

}
