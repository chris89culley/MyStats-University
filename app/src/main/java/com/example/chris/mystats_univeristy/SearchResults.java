package com.example.chris.mystats_univeristy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import Data.Course;

public class SearchResults extends MenuViewActivity {

    private Button sim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        sim = (Button) findViewById(R.id.simSelection);

        Bundle b = this.getIntent().getExtras();
        if(b != null){
            courses = b.getParcelableArrayList("searchResults");
        }

        ArrayList<Course> courses =  (ArrayList<Course>) getIntent().getBundleExtra("searchResults");

        if(courses.isEmpty()){
            Thread.sleep(1000);
        }

        Log.d("i am not empty " , String.valueOf(courses.size()));


        sim.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), CourseStats.class);
                startActivity(intent);
            }
        });
    }
}
