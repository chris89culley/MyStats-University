package com.example.chris.mystats_univeristy;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.chris.mystats_univeristy.MenuViewActivity;
import com.example.chris.mystats_univeristy.R;

import Adapters.UcasTipsAdapter;

/**
 * Created by Terence Lawson on 18/04/2017.
 */

public class UcasTips extends MenuViewActivity {


    TabLayout tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucas_tips);

        ViewPager vp = (ViewPager) findViewById(R.id.UcasTipsViewPager);
        vp.setAdapter(new UcasTipsAdapter(getSupportFragmentManager(), this));


        tb = (TabLayout) findViewById(R.id.UcasTipsTabLayout);
        tb.setupWithViewPager(vp);

    }
}
