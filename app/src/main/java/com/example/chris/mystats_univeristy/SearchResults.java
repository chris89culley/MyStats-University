package com.example.chris.mystats_univeristy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.andexert.expandablelayout.library.ExpandableLayoutListView;

import java.util.ArrayList;

import Data.Course;
import Adapters.CourselistAdapter;


public class SearchResults extends MenuViewActivity {

    ListView listView;

    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        TextView searchHeader = (TextView) findViewById(R.id.searchResultsHeader);
        searchHeader.setText("Search Results - " + this.getIntent().getStringExtra("searchedName"));

       ExpandableLayoutListView listView = (ExpandableLayoutListView) findViewById(R.id.listing);


        //This is array list of matches resulting from a search, you'll need to iterate them, displaying the relevant
        //bits. Because at the moment I haven't set a limit on successful results you might have to do so ; ie only displaying 10
        //in the list view (presuming you use a list view)
        ArrayList<Course> courses =  this.getIntent().getParcelableArrayListExtra("searchResults");


        if(!courses.isEmpty()){
            adapter = new CourselistAdapter( this, R.layout.search_row, R.layout.search_header, courses);
            listView.setAdapter(adapter);
        }

    }

}
