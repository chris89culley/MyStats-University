package Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import FragmentSelectors.UcasTipsFragmentSelector;

/**
 * Created by Terence Lawson on 18/04/2017.
 */

public class UcasTipsAdapter extends FragmentPagerAdapter{
    private String[] title = new String[]{"Choosing your Course","Personal Statement", "Interview" };
    private int pageCount = 3;

    public UcasTipsAdapter(FragmentManager fm) {
        super(fm);
    }
    /**
     * sends course data and position to the fragment selector.
     * @param position the position of the fragment being viewed
     * @return returns the correct fragment to be inflated
     */
    @Override
    public Fragment getItem(int position) {
        UcasTipsFragmentSelector frag = new UcasTipsFragmentSelector(position);
        return frag;    }

    /**
     * Returns the amount of fragments currently available
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
    }}

