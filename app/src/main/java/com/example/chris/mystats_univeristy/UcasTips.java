package com.example.chris.mystats_univeristy;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UcasTips extends MenuViewActivity {

    // inside Activity
    TextView txt_help_gest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucas_tips);
        txt_help_gest = (TextView) findViewById(R.id.txt_help_gest);
            // hide until its title is clicked
            txt_help_gest.setVisibility(View.GONE);
        }

/**
 * onClick handler
 */
        public void toggle_contents(View v){
            txt_help_gest.setVisibility( txt_help_gest.isShown()
                    ? View.GONE
                    : View.VISIBLE );
        }
    }

