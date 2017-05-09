package Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import Data.Course;
import FragmentSelectors.StatsPageSelecter;

/**
 * Created by c077ing on 08/03/2017.
 */


/**
 * pager adapter sets the titles of the tablayout and returns the page count which helps select the fragment to inflate.
 */
public class StatsPageAdapter extends FragmentPagerAdapter {
    private String[] title = new String[]{"Cost Statistics","Employment Stats","Satisfaction Stats","Study Info",
            "Entry Info"};
    Context context;
    private int pageCount = 5;
    private Course course;

    /**
     * sets the field data when pager adapter is called.
     * @param fm The current fragmentManager
     * @param context The context being worked in
     * @param course The course chosen from the database
     */
    public StatsPageAdapter(FragmentManager fm, Context context, Course course) {
        super(fm);
        this.context = context;
        this.course = course;
    }

    /**
     * sends course data and position to the fragment selector.
     * @param position The position of the string array to choose the correct fragment
     * @return returns the fragment to be inflated
     */
    @Override
    public Fragment getItem(int position) {
        StatsPageSelecter frag = new StatsPageSelecter(position, course);
        return frag;
    }


    /**
     * A method to find out how many Fragment pages the fragment manager is using
     * @return the amount of fragments currently available
     */
    @Override
    public int getCount() {
        return pageCount;
    }

    /**
     * gets the page title for the tab.
     * @param pos The position of the string array to choose the correct fragment
     * @return the title of the fragment
     */
    @Override
    public CharSequence getPageTitle(int pos){
        return title[pos];
    }


}
