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
     * @param fm
     * @param context
     * @param course
     */
    public StatsPageAdapter(FragmentManager fm, Context context, Course course) {
        super(fm);
        this.context = context;
        this.course = course;
    }

    /**
     * sends course data and position to the fragment selector.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        StatsPageSelecter frag = new StatsPageSelecter(position, course);
        return frag;}


    /**
     * returns page count.
     * @return
     */
    @Override
    public int getCount() {
        return pageCount;
    }

    /**
     * gets the page title for the tab.
     * @param pos
     * @return
     */
    @Override
    public CharSequence getPageTitle(int pos){
        return title[pos];
    }


}
