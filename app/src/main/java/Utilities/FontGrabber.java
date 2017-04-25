package Utilities;

import android.app.Activity;
import android.graphics.Typeface;

/**
 * Created by chris on 29/03/17.
 */

public class FontGrabber {

    public static  Typeface getVintageFont(Activity activity){
        return Typeface.createFromAsset(activity.getAssets(), "fonts/octin vintage b rg.ttf");
    }

    public  static  Typeface getMarketDecoFont(Activity activity){
        return  Typeface.createFromAsset(activity.getAssets(), "fonts/Market_Deco.ttf");
    }

    public  static  Typeface getJosefinSanFont(Activity activity){
        return  Typeface.createFromAsset(activity.getAssets(), "fonts/Josefin_Sans/JosefinSans-SemiBold.ttf");
    }
}
