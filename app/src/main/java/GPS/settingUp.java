package GPS;

import android.util.Log;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;


/**
 * Created by chris on 11/03/17.
 *
 * This class was used to set up the geofire database with current location data. This should not be run again since
 * it will no longer point to the correct data
 */

public  class settingUp {

    static DatabaseReference ref = FirebaseDatabase.getInstance().getReference("locationlookUp");

    static GeoFire geo = new GeoFire(ref);


    private static void collectLocations(DataSnapshot dataSnapshot){
        Iterator<DataSnapshot> data = dataSnapshot.getChildren().iterator();
        Log.d("pushing data ", "pushing");
        while(data.hasNext()){
            DataSnapshot next = data.next();
            Log.d("pushing data ", "pushing");
            //geo.removeLocation(next.getKey());
            geo.setLocation(next.getKey(), new GeoLocation(Double.valueOf((String) next.child("LATITUDE").getValue()), Double.valueOf((String) next.child("LONGITUDE").getValue())));
        }
    }

    public static void setUp(){

       Query query =  ref;

        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                collectLocations(dataSnapshot);
                Log.d("iam " , "in hereeee");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
