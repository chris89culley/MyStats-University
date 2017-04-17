package aboutFragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chris.mystats_univeristy.R;

/**
 * Created by Terence Lawson on 17/04/2017.
 */

public class AboutFragmentSelecter extends Fragment {

        private View view;
        private int pos;
        Typeface retroFont;


        /**
         * sets the position of the fragment pager and the course data
         * @param position
         */
        public AboutFragmentSelecter(int position) {
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
                        view = inflater.inflate(R.layout.about_fragment_app, container, false);
                        return view;
                    case 1:
                        view = inflater.inflate(R.layout.about_fragment_creaters, container, false);
                        editCreatersFragment();
                        return view;
                    case 2:
                        view = inflater.inflate(R.layout.about_fragment_copyright, container, false);
                        return view;
                }

            }catch(Exception IO){
                return view = inflater.inflate(R.layout.fragment_error, container, false);

            }


            return view;
        }

    private void editCreatersFragment(){
        TextView chrisDetails = (TextView) view.findViewById(R.id.chris);
        chrisDetails.setTypeface(retroFont);
        TextView telDetails = (TextView) view.findViewById(R.id.terry);
        telDetails.setTypeface(retroFont);
        TextView danDetails = (TextView) view.findViewById(R.id.dan);
        danDetails.setTypeface(retroFont);
        TextView jackDetails = (TextView) view.findViewById(R.id.jack);
        jackDetails.setTypeface(retroFont);
    }

}
