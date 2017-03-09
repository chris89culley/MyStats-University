package GPS;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Terence Lawson on 09/03/2017.
 */

public class RadiusChecker{
    double lang;
    double lat;
    MyLocationListener loclis;
    GeoFire geoFire;

    public RadiusChecker(){
        loclis = new MyLocationListener();
        lang = loclis.getLongat();
        lat = loclis.getLat();
    }


        public void getUniAtLocation(double radious) {
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            geoFire = new GeoFire(database.child("university_locations"));

            GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(lang, lat), radious);

            geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
                @Override
                public void onKeyEntered(String keyref, GeoLocation location) {
                //use the result to get the data we want to send to the view




                }

                @Override
                public void onKeyExited(String key) {

                }

                @Override
                public void onKeyMoved(String key, GeoLocation location) {

                }

                @Override
                public void onGeoQueryReady() {

                }

                @Override
                public void onGeoQueryError(DatabaseError error) {

                }
            });
        }

}
