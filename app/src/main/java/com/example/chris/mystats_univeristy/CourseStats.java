package com.example.chris.mystats_univeristy;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;

import Data.Course;
import MPChart.UniversityStatsChartMaker;
import Utilities.CourseListAdapter;

public class CourseStats extends MenuViewActivity  {

    TabLayout tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_stats);

        //This is the course object
        Course course = getIntent().getParcelableExtra("chosenCourse");

        //Sets up the fonts, needs to be refactored out
        Typeface retroFont = Typeface.createFromAsset(this.getAssets(), "fonts/Market_Deco.ttf");
        Typeface vintage = Typeface.createFromAsset(this.getAssets(), "fonts/octin vintage b rg.ttf");


        ViewPager vp = (ViewPager) findViewById(R.id.viewPager);
        vp.setAdapter(new Adapter(getSupportFragmentManager(), this, course));
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
