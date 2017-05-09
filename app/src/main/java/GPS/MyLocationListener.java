package GPS;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;


public class MyLocationListener implements LocationListener {
       private double longat;
       private double lat;


    /**
     * Called when the location has changed.
     * Is called when the location is changed can be used to be called seperately to get current location information as needed
     * @param location the current location as a location object allowing you to then find out current longitude and latitude
     */
        @Override
        public void onLocationChanged(Location location) {

        }

    /**
     * Called if the status is changed including if permissions are changed or if the
     * @param provider The name of the location provider associated with the update
     * @param status Tells whether the provider is available for service
     * @param extras an optional Bundle which will contain provider specific status variables
     */
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

    /**
     * Called when the provider is enabled by the user.
     * @param provider  the name of the location provider associated with this update.
     */
        @Override
        public void onProviderEnabled(String provider) {

        }

    /**
     * Called when the provider is disabled, if you requestLocationUpdates while the provider is
     * disabled then this is called immediately
     * @param provider the name of the location provider associated with this update.
     */
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
