package com.example.chris.mystats_univeristy;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.github.mikephil.charting.*;
import com.github.mikephil.charting.charts.BarChart;
import MPChart.Charts;

import java.util.Iterator;

import Data.CourseTypes;
import Data.DatabaseInformationQuerier;
import MPChart.Charts;

///i am a commetn
public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //This creates the database querier with the database, this will need to be passed to other activities that require
        // access to the database information
        DatabaseInformationQuerier databaseInfomationQuerier = new DatabaseInformationQuerier(FirebaseDatabase.getInstance().getReference());

        //This is an example of how to get all courses by course name and course type
        databaseInfomationQuerier.getAllCoursesByCourseName("Computer Science", CourseTypes.FULL_TIME);

        //New chart objexct being linked too the view
        BarChart chart = (BarChart) findViewById(R.id.chart);

        //Data to be used by the chart this is just an example
        float[] data = new float[4];
        data[0] = 4.0f;
        data[1] = 1.0f;
        data[2] = 2.0f;
        data[3] = 4.0f;
        String[] labels = new String[4];
        labels[0] = "One";
        labels[1] = "Two";
        labels[2] = "Three";
        labels[3] = "Four";

        //Using the returned values from the generic function to fill the chart
        chart.setData(Charts.constructBarChart(labels,data,chart));
        //Altering chart settings - not necessary
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);



    }

}
