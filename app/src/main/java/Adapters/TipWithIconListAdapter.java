package Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.chris.mystats_univeristy.CourseStats;
import com.example.chris.mystats_univeristy.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Data.Course;
import Data.TipEntry;
import Utilities.Colours;

/**
 * Created by chris on 09/03/17.
 */

public class TipWithIconListAdapter extends ArrayAdapter<TipEntry>{

    private Activity activity;
    private ArrayList<TipEntry> tips;
    private static LayoutInflater inflater = null;
    private static int[] colours = {Colours.GREEN_SHEEN.getColor(),
            Colours.BACKGROUND_GREEN.getColor()
    };

    public TipWithIconListAdapter(Activity activity, int textViewResourceId , int header_id , ArrayList<TipEntry> tips) {
        super(activity, textViewResourceId, header_id, tips);
        this.activity = activity;
        this.tips = tips;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    /**
     * This method handles the creation of the content for each row
     * @param position - The position in the list view we are at

     * @param parent - The parent to this row
     * @return - The row with content
     */
    public View getView(int position, final View convertView, ViewGroup parent){

        final View rowView = inflater.inflate(R.layout.tip_list_row, parent , false);
        TextView tipTitle = (TextView) rowView.findViewById(R.id.tip_title);


        String tipTitleString = activity.getString(tips.get(position).getTipTitleId());
        tipTitle.setText(tipTitleString);

        //Stops an error being thrown when we get to the bottom of the list view
        if(position > tips.size()-1){
            return rowView;
        }


        //Sets the rows colour (alternates between all the colours)
        int colour = (colours[position % colours.length]);
        rowView.findViewById(R.id.row).setBackgroundColor(colour);



        //Sets the fonts of the content
        //tipTitle.setTypeface(retroFont);

        return rowView;
    }


}
