package com.example.chris.mystats_univeristy;

/**
 * Created by Terence Lawson on 09/03/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LocationScreen extends MenuViewActivity {

    private Button check;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiuschecker);

        search = (Button) findViewById(R.id.searchAdv);

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), SearchResults.class);
                startActivity(intent);
            }
        });
    }
}
