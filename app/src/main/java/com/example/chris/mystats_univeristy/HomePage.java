package com.example.chris.mystats_univeristy;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import Data.DatabaseInformationQuerier;


public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //This creates the database querier with the database, this will need to be passed to other activities that require
        // access to the database information
        DatabaseInformationQuerier databaseInfomationQuerier = new DatabaseInformationQuerier(FirebaseDatabase.getInstance().getReference());


    }
}
