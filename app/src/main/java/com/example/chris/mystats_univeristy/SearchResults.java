package com.example.chris.mystats_univeristy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import Data.Course;

public class SearchResults extends MenuViewActivity {

    private Button sim;
    private TextView one, two, three, four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        sim = (Button) findViewById(R.id.simSelection);

        //Please delete all this rubbish - I was just playing with things
        one = (TextView) findViewById(R.id.test1);
        two = (TextView) findViewById(R.id.test2);
        three = (TextView) findViewById(R.id.test3);
        four = (TextView) findViewById(R.id.test4);

        //This is array list of matches resulting from a search, you'll need to iterate them, displaying the relevant
        //bits. Because at the moment I haven't set a limit on successful results you might have to do so ; ie only displaying 10
        //in the list view (presuming you use a list view)
        ArrayList<Course> courses =  this.getIntent().getParcelableArrayListExtra("searchResults");


        if(!courses.isEmpty()){

            one.setText(courses.get(0).TITLE);
            two.setText(courses.get(0).NAME);
            three.setText(courses.get(1).TITLE);
            four.setText(courses.get(1).NAME);
        }


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
