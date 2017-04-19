package Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import FragmentSelecters.AboutFragmentSelecter;

/**
 * Created by Terence Lawson on 17/04/2017.
 */

public class AboutPageAdapter extends FragmentPagerAdapter {

    private String[] title = new String[]{"The App", "Meet the Team", "The Data", "CopyRight details", };
    Context context;
    private int pageCount = 4;


    public AboutPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    /**
     * sends  position to the fragment selector.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        AboutFragmentSelecter frag = new AboutFragmentSelecter(position);
        return frag;
    }

    /**
     * Returns the amount of fragments currently available
     */
    @Override
    public int getCount() {
        return pageCount;
    }

    /**
     * returns the name of the Fragment at the corrent position selected
     * @param pos the position of the Fragment
     * @return
     */
    @Override
    public CharSequence getPageTitle(int pos){
        return title[pos];
}}
