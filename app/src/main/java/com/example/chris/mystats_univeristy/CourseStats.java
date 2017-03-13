package com.example.chris.mystats_univeristy;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TextView;

import Data.Course;

public class CourseStats extends AppCompatActivity  {

    TabLayout tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_stats);

        //This is the course object
        Course course = getIntent().getParcelableExtra("chosenCourse");

        ViewPager vp = (ViewPager) findViewById(R.id.viewPager);
        vp.setAdapter(new Adapter(getSupportFragmentManager(), this));

        tb = (TabLayout) findViewById(R.id.tabLayout);
        tb.setupWithViewPager(vp);

    }
}
