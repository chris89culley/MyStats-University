package com.example.chris.mystats_univeristy;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ogaclejapan.arclayout.ArcLayout;

public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Typeface retroFont = Typeface.createFromAsset(this.getAssets(), "fonts/Market_Deco.ttf");

        //Will need to come back to this and implement it properly in the future
        ArcLayout layout = (ArcLayout) findViewById(R.id.arc) ;
        for (int i = 0; i<= 360; i+=10){
            layout.animate().rotation(i).withLayer();
        }

        //Sets up all the buttons
        Button quickSearchButton  = (Button) findViewById(R.id.quicksearchbutton);
        Button accountButton = (Button) findViewById(R.id.accountbutton);
        Button advancedSearchButton = (Button) findViewById(R.id.advancedsearchbutton);
        Button ucasTipsButton = (Button) findViewById(R.id.ucastipbutton);
        Button aboutButton = (Button) findViewById(R.id.aboutbutton);

        //Sets the fonts of all the buttons
        quickSearchButton.setTypeface(retroFont);
        accountButton.setTypeface(retroFont);
        advancedSearchButton.setTypeface(retroFont);
        ucasTipsButton.setTypeface(retroFont);
        aboutButton.setTypeface(retroFont);

        //Sets the listeners to navigate to the correct pages
        quickSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchPage.class);
                startActivity(intent);
            }
        });

        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Account.class);
                startActivity(intent);
            }
        });

        advancedSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AdvanceSearch.class);
                startActivity(intent);
            }
        });


        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), About.class);
                startActivity(intent);
            }
        });
    }
}
