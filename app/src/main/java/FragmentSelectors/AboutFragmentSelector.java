package FragmentSelectors;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.chris.mystats_univeristy.R;

import java.util.ArrayList;

import Adapters.CoverFlowAdapter;
import Data.Person;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

/**
 * Created by Terence Lawson on 17/04/2017.
 */

public class AboutFragmentSelector extends Fragment {

        private View view;
        private int pos;
        private Typeface retroFont;
        private FeatureCoverFlow imageCarousel;

        /**
         * sets the position of the fragment pager and the course data
         * @param position
         */
        public AboutFragmentSelector(int position) {
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
            retroFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Josefin_Sans/JosefinSans-SemiBold.ttf");

            //Cycles through the fragments choosing which one to inflate
            try {
                switch (pos){
                    case 0:
                        view = inflater.inflate(R.layout.about_fragment_app, container, false);
                        editAboutTheApp();
                        return view;
                    case 1:
                        view = inflater.inflate(R.layout.about_fragment_creators, container, false);
                        createAboutTheTeamFragment();
                        return view;
                    case 2:
                        view = inflater.inflate(R.layout.about_fragment_the_data, container, false);

                        editTheDataFragment();
                        return view;
                    case 3:
                        view = inflater.inflate(R.layout.about_fragment_copyright, container, false);
                        editTheCopyrightFragment();
                        return view;
                }

            }catch(Exception IO){
                return view = inflater.inflate(R.layout.fragment_error, container, false);

            }


            return view;
        }
    /**
     * Sets the font type of the Copyright Fragment
     */
    private void editTheCopyrightFragment() {
        TextView pageIntro = (TextView) view.findViewById(R.id.copy_intro);
        pageIntro.setTypeface(retroFont);

        TextView copyUniStats = (TextView) view.findViewById(R.id.copy_unistats_disclaimer);
        copyUniStats.setTypeface(retroFont);

        TextView copyHesaDisclaimer = (TextView) view.findViewById(R.id.copu_hesa_disclaimer);
        copyHesaDisclaimer.setTypeface(retroFont);

        TextView further_info = (TextView) view.findViewById(R.id.further_info);
        further_info.setTypeface(retroFont);

        TextView emailTxt = (TextView) view.findViewById(R.id.email_linked);
        emailTxt.setMovementMethod(LinkMovementMethod.getInstance());
        emailTxt.setTypeface(retroFont);

        TextView image_info = (TextView) view.findViewById(R.id.image_info);
        image_info.setTypeface(retroFont);

        TextView freepikTxt = (TextView) view.findViewById(R.id.freepik_info);
        freepikTxt.setMovementMethod(LinkMovementMethod.getInstance());
        freepikTxt.setTypeface(retroFont);

        TextView logomakrTxt = (TextView) view.findViewById(R.id.logomakr_info);
        logomakrTxt.setMovementMethod(LinkMovementMethod.getInstance());
        logomakrTxt.setTypeface(retroFont);

    }

    /**
     * Sets the font type of the About the App Fragment
     */
    private void editAboutTheApp() {
        TextView aboutTitle = (TextView) view.findViewById(R.id.aboutTitle);
        aboutTitle.setTypeface(retroFont);
        TextView basic_info = (TextView) view.findViewById(R.id.basic_intro);
        basic_info.setTypeface(retroFont);
    }

    /**
     * Sets the font type of the The Data Fragment
     */
    private void editTheDataFragment() {
        TextView fire = (TextView) view.findViewById((R.id.fire));
        fire.setTypeface(retroFont);
        TextView dataIntro = (TextView) view.findViewById((R.id.data_intro));
        dataIntro.setTypeface(retroFont);
        TextView hefa = (TextView) view.findViewById((R.id.hefa));
        hefa.setTypeface(retroFont);
        TextView nss = (TextView) view.findViewById((R.id.nss));
        nss.setTypeface(retroFont);
        TextView dlhe = (TextView) view.findViewById((R.id.dlhe));
        dlhe.setTypeface(retroFont);
    }


    /**
     * Creates an array list of team members with images and descriptions
     * @return - The array of team members
     */
    private ArrayList<Person> createAnArrayListOfStaff(){
        final ArrayList<Person> team = new ArrayList<>();

        team.add(new Person(R.drawable.chris, R.string.chris_personal));
        team.add(new Person(R.drawable.jack, R.string.jack_personal));
        team.add(new Person(R.drawable.tel, R.string.tel_personal));
        team.add(new Person(R.drawable.dan, R.string.dan_personal));
        return team;
    }

    /**
     * Adds the animations to the description when they fade in and out (using the text switcher)
     * @param description The description to add animations to
     */
    private void setAnimationsForDescription(TextSwitcher description){
        Animation in = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_out_bottom);
        description.setInAnimation(in);
        description.setOutAnimation(out);
    }

    /**
     * Creates the about the team fragment which holds pictures of all the staff in a carousel style
     * and there descriptions
     */
    private void createAboutTheTeamFragment(){
        imageCarousel = (FeatureCoverFlow) view.findViewById(R.id.coverflow);

        CoverFlowAdapter mAdapter = new CoverFlowAdapter(this.getContext());
        final ArrayList<Person> team = createAnArrayListOfStaff();
        mAdapter.setData(team);
        imageCarousel.setAdapter(mAdapter);
        final TextSwitcher description = (TextSwitcher) view.findViewById(R.id.title);
        setAnimationsForDescription(description);

        description.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(AboutFragmentSelector.this.getContext());
                TextView textView = (TextView) inflater.inflate(R.layout.item_title, null);
                textView.setTypeface(retroFont);
                return textView;
            }
        });


        imageCarousel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(AboutFragmentSelector.this.getContext(),
                        getResources().getString(team.get(position).titleResId),
                        Toast.LENGTH_SHORT).show();
            }
        });

        imageCarousel.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                description.setText(getResources().getString(team.get(position).titleResId));
            }

            @Override
            public void onScrolling() {
                description.setText("");
            }
        });
    }



}
