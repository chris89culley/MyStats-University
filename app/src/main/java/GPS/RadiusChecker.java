package GPS;

import android.util.Log;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Data.CourseTypes;
import Data.DatabaseInformationQuerier;

/**
 * Created by Terence Lawson on 09/03/2017.
 *
 *  This class makes a search around a specified area for matching hits in the database
 */

public class RadiusChecker{
    private static GeoFire geoFire;

    //The set of hits found
    private  static Set<String> keys = new HashSet<>();

    /**
     * This method makes the search by calling the databasequery class
     * @param query - The query object which contains the searching functions
     * @param coursename - The name of the course being searched
     * @param type - The type of the course being searched
     */
        private static void makeSearch(DatabaseInformationQuerier query, String coursename, CourseTypes type){
            query.searchByCourseLocation(coursename, keys, type);
        }

    /**
     * This runs through the database pulling out any matches around the location provided
     * @param radius - The radius of the search
     * @param lang - The longitude to be searched around
     * @param lat - The latitude to be searched around
     * @param coursename - The name of the course being searched
     * @param query - The query class
     * @param type - The type of course being searched
     */
        public static void getHitsAroundLocation(double radius, double lang, double lat, final String coursename, final DatabaseInformationQuerier query, final CourseTypes type) {
             keys.clear();
            DatabaseReference database = FirebaseDatabase.getInstance().getReference("locationlookUp");
            geoFire = new GeoFire(database);
            GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(lat , lang), radius);
            geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
                @Override
                public void onKeyEntered(String keyref, GeoLocation location) {
                    keys.add(keyref);
                }

                @Override
                public void onKeyExited(String key) {

                }

                @Override
                public void onKeyMoved(String key, GeoLocation location) {

                }

                @Override
                public void onGeoQueryReady() {
                    makeSearch(query, coursename, type);
                }

                @Override
                public void onGeoQueryError(DatabaseError error) {

                }
            });
        }

}
