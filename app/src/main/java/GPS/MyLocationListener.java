package GPS;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by Terence Lawson on 08/03/2017.
 */

public class MyLocationListener implements LocationListener {
       private double longat;
       private double lat;

        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }


    /**
     * Requests the longitude
     * @return longitude as a double
     */
    public double getLongat() {
        return longat;
    }
    /**
     * Requests the latitude
     * @return latitude as a double
     */
    public double getLat() {
        return lat;
    }
}
