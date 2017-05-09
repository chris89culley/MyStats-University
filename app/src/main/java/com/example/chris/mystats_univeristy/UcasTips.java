package com.example.chris.mystats_univeristy;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import Adapters.UcasTipsAdapter;

/**
 *  Ucas tips is an activity that displays a list view of tips to the user
 */

public class UcasTips extends MenuViewActivity {

    TabLayout tb;

    /**
     * onCreate method is called when the activity is started and sets up the view pager and tab layout of
     * fragments
     * @param savedInstanceState - The saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucas_tips);
        setTitle("UCAS Tips");
        ViewPager vp = (ViewPager) findViewById(R.id.UcasTipsViewPager);
        vp.setAdapter(new UcasTipsAdapter(getSupportFragmentManager()));
        tb = (TabLayout) findViewById(R.id.UcasTipsTabLayout);
        tb.setupWithViewPager(vp);

    }
}
