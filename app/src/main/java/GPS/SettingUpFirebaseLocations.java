package GPS;

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
 *
 * This class was used to set up the geofire database with current location data. This should not be run again since
 * it will no longer point to the correct data
 */

public  class SettingUpFirebaseLocations {

    static DatabaseReference ref = FirebaseDatabase.getInstance().getReference("locationlookUp");

    static GeoFire geo = new GeoFire(ref);


    /**
     * This method uses the data currently in the database to set up the geolocation table
     * @param dataSnapshot
     */
    private static void collectLocations(DataSnapshot dataSnapshot){
        Iterator<DataSnapshot> data = dataSnapshot.getChildren().iterator();
        while(data.hasNext()){
            DataSnapshot next = data.next();
            geo.setLocation(next.getKey(),
                    new GeoLocation(Double.valueOf((String) next.child("LATITUDE").getValue()),
                            Double.valueOf((String) next.child("LONGITUDE").getValue())));
        }
    }

    /**
     * Main set up method collects all the locations from the database and creates a firebase table with them
     */
    public static void setUp(){

       Query query =  ref;

        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                collectLocations(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
