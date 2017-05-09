package com.example.chris.mystats_univeristy;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import Data.Course;
import Adapters.StatsPageAdapter;
import Data.RSDBhandler;

/**
 * CourseStats is a page that shows course stats for a passed in course in a tab layout with fragments
 * displaying different intricacies of the course
 */
public class CourseStats extends MenuViewActivity  {


    /**
     *  Sets up a pervasive bar showing the name of the course and the university so that the user
     *  can have reference to what they have searched
     * @param course - The course the user has searched
     */
    private void setUpPervasiveBar(Course course){
        Typeface retroFont = Typeface.createFromAsset(this.getAssets(), "fonts/Josefin_Sans/JosefinSans-SemiBold.ttf");
        Typeface vintage = Typeface.createFromAsset(this.getAssets(), "fonts/octin vintage b rg.ttf");

        TextView courseNamePervasive = (TextView) findViewById(R.id.courseNamePervasive);
        TextView uniNamePervasive = (TextView) findViewById(R.id.universityNamePervasive);
        courseNamePervasive.setText(course.getFullCourseName());
        courseNamePervasive.setTypeface(retroFont);
        uniNamePervasive.setText(course.getUniversityWhereCourseIsTaught());
        uniNamePervasive.setTypeface(vintage);


    }

    /**
     * On create sets up the page viewer with a tab layout which supports the fragments which
     * hold all the course stats and passes to the fragment manager the course which has been chosen
     * by the user
     * @param savedInstanceState - The saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_stats);
        setTitle("Course Statistics");
        Course course = getIntent().getParcelableExtra("chosenCourse");
        RSDBhandler dh = new RSDBhandler(this);

        try {
            dh.addEntry(course);
        }catch(Exception e){
            Log.d("Database","Couldn't add entry");
        }
        ViewPager vp = (ViewPager) findViewById(R.id.viewPager);
        vp.setAdapter(new StatsPageAdapter(getSupportFragmentManager(), this, course));
        TabLayout tb = (TabLayout) findViewById(R.id.tabLayout);
        tb.setupWithViewPager(vp);
        setUpPervasiveBar(course);

    }
}
