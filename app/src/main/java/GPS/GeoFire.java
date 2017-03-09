package GPS;

/**
 * Created by Terence Lawson on 08/03/2017.
 */

public class GeoFire {
    DatabaseReference database = FirebaseDatabase.getInstance().getReference("path/to/geofire");
    GeoFire geoFire;

    GeoFire(DatabaseReference database){
      Final  geoFire = new GeoFire(this.database);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("path/to/geofire");
        GeoFire geoFire = new GeoFire(ref);
    }


}
