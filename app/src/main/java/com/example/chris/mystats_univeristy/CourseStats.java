package com.example.chris.mystats_univeristy;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import Data.Course;
import Adapters.PagerAdapter;
import Data.RSDBhandler;

public class CourseStats extends MenuViewActivity  {

    TabLayout tb;

    /**
     * course stats sets up the view pager using the pager adapter.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_stats);

        //This is the course object
        Course course = getIntent().getParcelableExtra("chosenCourse");
        //Passes this selection to the
        RSDBhandler dh = new RSDBhandler(this);
        try {
            dh.addEntry(course);
        }catch(Exception e){
            Log.d("Database","Couldn't add entry");
        }
        //Sets up the fonts, needs to be refactored out
        Typeface retroFont = Typeface.createFromAsset(this.getAssets(), "fonts/Market_Deco.ttf");
        Typeface vintage = Typeface.createFromAsset(this.getAssets(), "fonts/octin vintage b rg.ttf");

        ViewPager vp = (ViewPager) findViewById(R.id.viewPager);
        vp.setAdapter(new PagerAdapter(getSupportFragmentManager(), this, course));

        tb = (TabLayout) findViewById(R.id.tabLayout);
        tb.setupWithViewPager(vp);

        //Sets up the pervasive bar to the top of the page across all fragments
        TextView courseNamePervasive = (TextView) findViewById(R.id.courseNamePervasive);
        TextView uniNamePervasive = (TextView) findViewById(R.id.universityNamePervasive);
        courseNamePervasive.setText(course.getFullCourseName());
        courseNamePervasive.setTypeface(retroFont);
        uniNamePervasive.setText(course.getUniversityWhereCourseIsTaught());
        uniNamePervasive.setTypeface(vintage);
    }
}
