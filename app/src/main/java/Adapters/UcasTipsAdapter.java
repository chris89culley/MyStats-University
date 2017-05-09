package Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.chris.mystats_univeristy.UcasTips;

import FragmentSelectors.UcasTipsFragmentSelecter;

/**
 * Created by Terence Lawson on 18/04/2017.
 */

public class UcasTipsAdapter extends FragmentPagerAdapter{
    private String[] title = new String[]{"Choosing your Course","Personal Statement", "Interview" };
    Context context;
    private UcasTips ucasTips;
    private int pageCount = 3;

    /**
     * sets the field data when pager adapter is called.
     * @param fm The current fragmentManager
     */
    public UcasTipsAdapter(FragmentManager fm) {
        super(fm);
    }
    /**
     * sends course data and position to the fragment selector.
     * @param position The position of the string array to choose the correct fragment
     * @return returns the fragment to be inflated
     */
    @Override
    public Fragment getItem(int position) {
        UcasTipsFragmentSelecter frag = new UcasTipsFragmentSelecter(position, ucasTips);
        return frag;    }

    /**
     * Collects the pageCount for the fragments.
     * @return the amount of fragments currently available
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

