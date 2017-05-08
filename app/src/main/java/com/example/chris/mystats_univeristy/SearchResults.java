package com.example.chris.mystats_univeristy;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.andexert.expandablelayout.library.ExpandableLayoutListView;
import java.util.ArrayList;
import Data.Course;
import Adapters.CourselistAdapter;


/**
 * Search results holds the logic for the search results activity creation showing a list of courses
 * and utilising an array adapter to show details of each course in a drop down
 */
public class SearchResults extends MenuViewActivity {

    /**
     * Sets the search word header text making sure that the first letter is a capital
     * @param searchHeader - The search header to be manipulated
     */
    private void setSearchWordText(TextView searchHeader){
        String searchedWord = this.getIntent().getStringExtra("searchedName");
        searchedWord = Character.toUpperCase(searchedWord.charAt(0)) + searchedWord.substring(1);
        searchHeader.setText(searchedWord);
    }

    /**
     * On the creation of the activity this method sets up the text views and the list view of all the
     * course search results
     * @param savedInstanceState - the saved instance bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        setTitle("Search Results");
        TextView searchHeader = (TextView) findViewById(R.id.searchResultsHeader);
        setSearchWordText(searchHeader);
        ExpandableLayoutListView listView = (ExpandableLayoutListView) findViewById(R.id.listing);
        ArrayList<Course> courses =  this.getIntent().getParcelableArrayListExtra("searchResults");

        if(!courses.isEmpty()){
            ArrayAdapter adapter = new CourselistAdapter( this, R.layout.search_row, R.layout.search_header, courses);
            listView.setAdapter(adapter);
        }

    }

}
