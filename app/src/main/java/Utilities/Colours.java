package Utilities;

import android.graphics.Color;

/**
 * Created by chris on 22/03/17.
 */

public enum Colours {


    TURTLE_GREEN("#88B04B"),
    TEAL_DEER("#94E8B4"),
    GREEN_SHEEN("#72BDA3") ,
    MUMMYS_TOMB("#839788"),
    OLD_BURGUNDY("#3B322C"),
    LIGHT_YELLOW("#EEB96D"),
    LIGHT_BROWN("#EE976D"),
    PURPLE("#8581C2"),
    BACKGROUND_GREEN("#A3DAC7"),
    BLUE("#4D749A") ,
    DAN_PINK("#DF5061");

    private int color;
    Colours(String hex){
        color = Color.parseColor(hex);

    }

    public int getColor(){
        return color;
    }
}
