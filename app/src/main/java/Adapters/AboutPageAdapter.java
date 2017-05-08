package Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import FragmentSelectors.AboutFragmentSelector;

/**
 * This class provides an adapter for the about page
 */

public class AboutPageAdapter extends FragmentPagerAdapter {

    private String[] title = new String[]{"The App", "Meet the Team", "The Data", "CopyRight details", };
    private Context context;
    private int pageCount = 4;


    /**
     * Constructor sets the context and passes the fragment manager to the super FragmentPagerAdapter
     * @param fm - The fragment manager for the page
     * @param context - The context of the page
     */
    public AboutPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    /**
     * Sends  position to the fragment selector.
     * @param - The position of the fragment
     * @return - The fragment at the particular position
     */
    @Override
    public Fragment getItem(int position) {
        AboutFragmentSelector frag = new AboutFragmentSelector(position);
        return frag;
    }

    /**
     * Returns the number of fragments currently available
     */
    @Override
    public int getCount() {
        return pageCount;
    }

    /**
     * Returns the name of the Fragment at the corrent position selected
     * @param pos -  the position of the Fragment
     * @return The page title at the particular fragment
     */
    @Override
    public CharSequence getPageTitle(int pos){
        return title[pos];
}}
