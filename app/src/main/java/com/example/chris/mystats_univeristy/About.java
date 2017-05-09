package com.example.chris.mystats_univeristy;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import Adapters.AboutPageAdapter;

/**
 * The about page shows the user information about the app, the team and the data used in the app
 */
public class About extends MenuViewActivity {

    /**
     * On create sets up the page viewer with a tab layout which supports the fragments which
     * show details about the app and the team
     *
     * @param savedInstanceState - The saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ViewPager vp = (ViewPager) findViewById(R.id.aboutViewPager);
        vp.setAdapter(new AboutPageAdapter(getSupportFragmentManager(), this));
        TabLayout tb = (TabLayout) findViewById(R.id.aboutTabLayout);
        tb.setupWithViewPager(vp);
        setTitle("About");

    }


}