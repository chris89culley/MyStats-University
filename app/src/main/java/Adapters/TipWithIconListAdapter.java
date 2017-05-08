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
 * Adapter used in an expandable list view and matches an item with an image and content
 */

public class TipWithIconListAdapter extends ArrayAdapter<TipEntry>{

    private Activity activity;
    private ArrayList<TipEntry> tips;
    private static LayoutInflater inflater = null;
    private static int[] colours = {Colours.GREEN_SHEEN.getColor(),
            Colours.BACKGROUND_GREEN.getColor()
    };
    private Typeface retroFont;

    /**
     * Constructor sets up the activity,fonts, the header resource and the list of tips
     * @param activity - The activity where the adapter will be utilised
     * @param textViewResourceId - The text view resource
     * @param header_id - The resource id for the header
     * @param tips - A list of tips that will be displayed
     */
    public TipWithIconListAdapter(Activity activity, int textViewResourceId , int header_id , ArrayList<TipEntry> tips) {
        super(activity, textViewResourceId, header_id, tips);
        this.activity = activity;
        this.tips = tips;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        retroFont = Typeface.createFromAsset(activity.getAssets(), "fonts/Josefin_Sans/JosefinSans-SemiBold.ttf");
    }

    /**
     * Sets up the tip title
     * @param tipTitle - The title to be set up
     * @param titleText - The text to go in the title
     * @param titleImage - The image linked to the title
     */
    private void setUpTipTitle(TextView tipTitle, String titleText, int titleImage){
        tipTitle.setText(titleText);
        tipTitle.setTypeface(retroFont);
        tipTitle.setCompoundDrawablesWithIntrinsicBounds(titleImage, 0, 0, 0);

    }

    /**
     * Sets up the tip description
     * @param tipDescription -  The tip description resource
     * @param descriptionText - The text to go into the description
     */
    private void setUpTipDescription(TextView tipDescription, String descriptionText){
        tipDescription.setText(descriptionText);
        tipDescription.setTypeface(retroFont);
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

        setUpTipTitle(tipTitle, activity.getString(tip.getTipTitleId()) , tip.getTipIconId());
        setUpTipDescription(tipDescription, activity.getString(tip.getTipDescriptionId()));

        //Stops an error being thrown when we get to the bottom of the list view
        if(position > tips.size()-1){
            return rowView;
        }

        //Sets the rows colour (alternates between all the colours)
        rowView.findViewById(R.id.row).setBackgroundColor(colours[position % colours.length]);

        return rowView;
    }


}
