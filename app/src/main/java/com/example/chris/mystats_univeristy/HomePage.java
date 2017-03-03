package com.example.chris.mystats_univeristy;


import android.os.Bundle;
import android.util.Log;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.StringTokenizer;

public class HomePage extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

// I am a dummy method to demonstrate a basic grabbing of information from the database :)
    private void dummyMethod(){

        Log.d("dummy method ", "I am a dummy method and I grab information about a course");
        database.child("fulltimecourses").child("VV43").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> obs = dataSnapshot.getChildren().iterator();
                        while(obs.hasNext()){
                            Log.d("From the datbase" , obs.next().toString());
                        }
                        Log.d("Dummy method ", "I am now leaving the dummy method");
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        dummyMethod();



    }
}
