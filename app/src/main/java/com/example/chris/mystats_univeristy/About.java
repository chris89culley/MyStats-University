package com.example.chris.mystats_univeristy;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class About extends MenuViewActivity {
    private View view;
    private TextView pageTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);



        Typeface retroFont = Typeface.createFromAsset(this.getAssets(), "fonts/Market_Deco.ttf");



        pageTitle = (TextView) findViewById(R.id.title);
        pageTitle.setTypeface(retroFont);
    }



}