package Utilities;

import android.graphics.Color;

/**
 * Created by chris on 22/03/17.
 */

public enum Colours {

    LIGHT_BLUE_SEARCH_PAGE("#8DA6B3"), MED_BLUE_SEARCH_PAGE("#5E8091"), DARK_BLUE_SEARCH_PAGE("#254F63");

    private int color;
    Colours(String hex){
        color = Color.parseColor(hex);

    }

    public int getColor(){
        return color;
    }
}
