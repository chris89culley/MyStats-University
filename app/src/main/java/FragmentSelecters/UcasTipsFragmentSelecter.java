package FragmentSelecters;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chris.mystats_univeristy.R;

/**
 * Created by Terence Lawson on 18/04/2017.
 */

public class UcasTipsFragmentSelecter extends Fragment {

        private View view;
    private int pos;
    Typeface retroFont;


    /**
     * sets the position of the fragment pager and the course data
     * @param position
     */
    public UcasTipsFragmentSelecter(int position) {
        this.pos = position;
    }


    /**
     * on create view determines which fragment to inflate, pending on the position.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        retroFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Market_Deco.ttf");


        try {
            switch (pos){
                case 0:
                    view = inflater.inflate(R.layout.ucas_tips_fragment_intro, container, false);
                    return view;
                case 1:
                    view = inflater.inflate(R.layout.ucas_tips_fragment_getting_started, container, false);
                    return view;
                case 2:
                    view = inflater.inflate(R.layout.ucas_tips_fragment_personal_statement, container, false);
                    return view;
            }

        }catch(Exception IO){
            return view = inflater.inflate(R.layout.fragment_error, container, false);

        }


        return view;
    }
}
