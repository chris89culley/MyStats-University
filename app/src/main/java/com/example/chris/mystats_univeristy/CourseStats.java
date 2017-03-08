package com.example.chris.mystats_univeristy;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

public class CourseStats extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLay;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_stats);

        content = (TextView) findViewById(R.id.viewStat);
        tabLay = (TabLayout) findViewById(R.id.tabLayout);

        tabLay.addTab(
                tabLay.newTab().setText("Study Info")
        );

        tabLay.addTab(
                tabLay.newTab().setText("Entry Info")
        );

        tabLay.addTab(
                tabLay.newTab().setText("Employment Stats")
        );

        tabLay.addTab(
                tabLay.newTab().setText("Costs Stats")
        );

        tabLay.addTab(
                tabLay.newTab().setText("Statifaction Stats")
        );

        tabLay.addTab(
                tabLay.newTab().setText("User Ratings")
        );


        tabLay.addOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        content.setText(tab.getText());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
