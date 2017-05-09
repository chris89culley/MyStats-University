package Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import Data.Course;
import FragmentSelectors.StatsPageSelector;

/**
 * Created by c077ing on 08/03/2017.
 */


/**
 * pager adapter sets the titles of the tablayout and returns the page count which helps select the fragment to inflate.
 */
public class StatsPageAdapter extends FragmentPagerAdapter {

    private String[] title = new String[]{"Cost Statistics","Employment Stats","Satisfaction Stats","Study Info",
            "Entry Info"};
    private int pageCount = 5;
    private Course course;

    /**
     * sets the field data when pager adapter is called.
     * @param fm The fragmentManager controlling the current collection of fragments that has been inflated
     * @param course The course that was selected in the stats chooser page
     */
    public StatsPageAdapter(FragmentManager fm, Course course) {
        super(fm);
        this.course = course;
    }

    /**
     * sends course data and position to the fragment selector.
     * @param position the position of the fragment being viewed
     * @return returns the correct fragment to be inflated
     */
    @Override
    public Fragment getItem(int position) {

        StatsPageSelector frag = new StatsPageSelector(position, course);
        return frag;
    }


    /**
     *  Returns the amount of fragments currently available
     * @return the the actual number of fragments
     */
    @Override
    public int getCount() {
        return pageCount;
    }

    /**
     * gets the page title for the tab.
     * @param pos Takes in the position you want to know the title of
     * @returnthe fragment name of the position requested
     */
    @Override
    public CharSequence getPageTitle(int pos){
        return title[pos];
    }


}
