package com.example.chris.mystats_univeristy;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Data.Course;
import Utilities.CourseListAdapter;


public class SearchResults extends MenuViewActivity {

    ListView listView;

    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        listView = (ListView) findViewById(R.id.listing);


        //This is array list of matches resulting from a search, you'll need to iterate them, displaying the relevant
        //bits. Because at the moment I haven't set a limit on successful results you might have to do so ; ie only displaying 10
        //in the list view (presuming you use a list view)
        ArrayList<Course> courses =  this.getIntent().getParcelableArrayListExtra("searchResults");


        if(!courses.isEmpty()){
            adapter = new CourseListAdapter( this, R.layout.activity_search_results, courses);
            listView.setAdapter(adapter);
        }
        //Else we need to show no search results found.


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), CourseStats.class);
                intent.putExtra("chosenCourse", (Parcelable) parent.getAdapter().getItem(position));
                startActivity(intent);
            }
        });
    }
}
