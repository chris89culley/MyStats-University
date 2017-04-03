package Utilities;

import android.graphics.Color;

/**
 * Created by chris on 22/03/17.
 */

public enum Colours {

    TURTLE_GREEN("#88B04B"), TEAL_DEER("#94E8B4"), GREEN_SHEEN("#72BDA3") , MUMMYS_TOMB("#839788"), OLD_BURGUNDY("#3B322C");

    private int color;
    Colours(String hex){
        color = Color.parseColor(hex);

    }

    public int getColor(){
        return color;
    }
}
