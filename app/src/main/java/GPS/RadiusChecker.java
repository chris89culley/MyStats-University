package GPS;

/**
 * Created by Terence Lawson on 09/03/2017.
 */

public class RadiusChecker {
    double lang;
    double lat;
    MyLocationListener loclis;

    RadiusChecker(){
        loclis = new MyLocationListener();
        lang = loclis.getLongat();
        lat = loclis.getLat();
    }

    geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
        @Override
        public void onKeyEntered(String key, GeoLocation location) {
            System.out.println(String.format("Key %s entered the search area at [%f,%f]", key, location.latitude, location.longitude));
            mDatabase.child("restaurants").child(key).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Restaurant res= snapshot.getValue(Restaurant.class);
                }
                @Override
                public void onCancelled(DatabaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }

                // ...
            });
        }

        @Override
        public void onKeyExited(String key) {
            System.out.println(String.format("Key %s is no longer in the search area", key));
        }

        @Override
        public void onKeyMoved(String key, GeoLocation location) {
            System.out.println(String.format("Key %s moved within the search area to [%f,%f]", key, location.latitude, location.longitude));
        }

        @Override
        public void onGeoQueryReady() {
            System.out.println("All initial data has been loaded and events have been fired!");
        }

        @Override
        public void onGeoQueryError(DatabaseError error) {
            System.err.println("There was an error with this query: " + error);
        }
    });

}
