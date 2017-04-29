package Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;


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

    public UcasTipsAdapter(FragmentManager fm, UcasTips ucasTips) {
        super(fm);
    }
    /**
     * sends  position to the fragment selector.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        UcasTipsFragmentSelecter frag = new UcasTipsFragmentSelecter(position, ucasTips);
        return frag;    }

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

