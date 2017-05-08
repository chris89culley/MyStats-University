package Adapters;

/**
 *
 * This adapter is used for the carousel of images
 *
 * This  code is largely taken from https://android-arsenal.com/details/1/1481 with slight modifications for the person datatype
 *
 */


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.chris.mystats_univeristy.R;
import java.util.ArrayList;
import Data.Person;

public class CoverFlowAdapter extends BaseAdapter {

    private ArrayList<Person> mData = new ArrayList<>(0);
    private Context mContext;
    private Typeface retroFont;
    public CoverFlowAdapter(Context context) {
        mContext = context;
        retroFont = Typeface.createFromAsset(context.getAssets(), "fonts/Josefin_Sans/JosefinSans-SemiBold.ttf");
    }

    /**
     * Sets the data up
     * @param data
     */
    public void setData(ArrayList<Person> data) {
        mData = data;
    }

    /**
     * Gets the number of items of data
     * @return - The number of items of data
     */
    @Override
    public int getCount() {
        return mData.size();
    }

    /**
     * Gets a particular item at an index
     * @param pos - The index of the item
     * @return - The item at the index
     */
    @Override
    public Object getItem(int pos) {
        return mData.get(pos);
    }


    /**
     * Gets the position passed in
     * @param pos - The position passed in
     * @return - The position passed in
     */
    @Override
    public long getItemId(int pos) {
        return pos;
    }

    /**
     * Gets the view to be displayed based on the index of the item
     * @param position - The index of the item
     * @param convertView - The view
     * @param parent - The parent to the view
     * @return The view containing the item image and descriptions
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_coverflow, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.label);
            viewHolder.image = (ImageView) rowView.findViewById(R.id.image);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.text.setText(mData.get(position).getTitleResId());
        holder.image.setImageResource(mData.get(position).getImageResId());
        return rowView;
    }


    /**
     * A static classed as a place holder for text/image combos
     */
    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }
}
