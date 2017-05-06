package com.example.chris.mystats_univeristy;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.Window;

import com.example.chris.mystats_univeristy.MenuViewActivity;
import com.example.chris.mystats_univeristy.R;

import Adapters.AboutPageAdapter;

public class About extends MenuViewActivity {

    TabLayout tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ViewPager vp = (ViewPager) findViewById(R.id.aboutViewPager);
        vp.setAdapter(new AboutPageAdapter(getSupportFragmentManager(), this));



        tb = (TabLayout) findViewById(R.id.aboutTabLayout);
        tb.setupWithViewPager(vp);

        setTitle("About");

    }


}