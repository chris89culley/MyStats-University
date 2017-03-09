package com.example.chris.mystats_univeristy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by c077ing on 08/03/2017.
 */

public class FragmentSelector extends Fragment {

    private int pos;

    public FragmentSelector(int position) {
        this.pos = position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        switch(pos) {
            case 0:

                return inflater.inflate(R.layout.fragment_overview, container, false);
            case 1:
                return inflater.inflate(R.layout.fragment_study_info, container, false);
            case 2:
                return inflater.inflate(R.layout.fragment_entry_info, container, false);
            case 3:
                return inflater.inflate(R.layout.fragment_employ_stats, container, false);
            case 4:
                return inflater.inflate(R.layout.fragment_cost_stats, container, false);
            case 5:
                return inflater.inflate(R.layout.fragment_satisfaction_stats, container, false);
            case 6:
                return inflater.inflate(R.layout.fragment_user_rating, container, false);
            default:
                return inflater.inflate(R.layout.fragment_overview, container, false);
        }
    }
}
