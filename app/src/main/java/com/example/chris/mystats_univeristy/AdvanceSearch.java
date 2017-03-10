package com.example.chris.mystats_univeristy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdvanceSearch extends MenuViewActivity implements AdapterView.OnItemSelectedListener {

    private Button search;

    private Spinner spinner;
    private static final String[] paths = {"25", "50", "75", "100", "200", "200+"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_search);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AdvanceSearch.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        search = (Button) findViewById(R.id.searchAdv);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent intent = new Intent(view.getContext(), SearchResults.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String temp = String.valueOf(spinner.getSelectedItem());
        TextView t = (TextView) findViewById(R.id.radiusText);
        t.setText("Radius (mi): " + temp);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
