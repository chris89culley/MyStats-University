package Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chris.mystats_univeristy.R;

import java.util.ArrayList;

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
    private Typeface retroFont;

    public TipWithIconListAdapter(Activity activity, int textViewResourceId , int header_id , ArrayList<TipEntry> tips) {
        super(activity, textViewResourceId, header_id, tips);
        this.activity = activity;
        this.tips = tips;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        retroFont = Typeface.createFromAsset(activity.getAssets(), "fonts/Josefin_Sans/JosefinSans-SemiBold.ttf");
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
        TextView tipDescription = (TextView)  rowView.findViewById(R.id.tip_description);

        TipEntry tip = tips.get(position);

        String tipTitleString = activity.getString(tip.getTipTitleId());
        String tipDescriptionString = activity.getString(tip.getTipDescriptionId());

        tipTitle.setText(tipTitleString);
        tipTitle.setTypeface(retroFont);
        tipTitle.setCompoundDrawablesWithIntrinsicBounds(tip.getTipIconId(), 0, 0, 0);

        tipDescription.setText(tipDescriptionString);
        tipDescription.setTypeface(retroFont);

        //Stops an error being thrown when we get to the bottom of the list view
        if(position > tips.size()-1){
            return rowView;
        }


        //Sets the rows colour (alternates between all the colours)
        int colour = (colours[position % colours.length]);
        rowView.findViewById(R.id.row).setBackgroundColor(colour);

        return rowView;
    }


}
