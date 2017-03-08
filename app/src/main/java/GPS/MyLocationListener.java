package GPS;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Terence Lawson on 08/03/2017.
 */

public class MyLocationListener implements LocationListener {


    @Override
    public void onLocationChanged(Location location) {
        if(location != null)
        {
            Log.e("Latitiude :", "" + location.getLatitude());
            Log.e("Logitude :", "" + location.getLongitude());
        }
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
}
