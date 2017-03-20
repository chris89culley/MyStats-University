package com.example.chris.mystats_univeristy;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import Data.Course;

/**
 * Created by c077ing on 08/03/2017.
 */

public class Adapter extends FragmentPagerAdapter {

    private String[] title = new String[]{"Course Overview","Cost Stats","Employment Stats","Satisfaction Stats","Study Info",
            "Entry Info","User Ratings/Comments"};
    Context context;
    private int pageCount = 7;
    private Course course;

    public Adapter(FragmentManager fm, Context context, Course course) {
        super(fm);
        this.context = context;
        this.course = course;
    }

    @Override
    public Fragment getItem(int position) {
        FragmentSelector frag = new FragmentSelector(position, course);
        return frag;
    }


    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int pos){
        return title[pos];
    }
}
