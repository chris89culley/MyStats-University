package Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import AboutFragments.AboutFragmentSelecter;

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

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int pos){
        return title[pos];
}}
