package FragmentSelectors;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.chris.mystats_univeristy.R;
import com.ogaclejapan.arclayout.ArcLayout;

import java.util.ArrayList;

import Adapters.CoverFlowAdapter;
import Animations.AnimatorUtils;
import Data.Person;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

import static com.example.chris.mystats_univeristy.R.string.copy_page_intro;

/**
 * Created by Terence Lawson on 17/04/2017.
 */

public class AboutFragmentSelecter extends Fragment {

        private View view;
        private int pos;
        private Typeface retroFont;
        private ArcLayout arcLayout;
    private FeatureCoverFlow mCoverFlow;

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
            retroFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Josefin_Sans/JosefinSans-SemiBold.ttf");

            //Cycles through the fragments choosing which one to inflate
            try {
                switch (pos){
                    case 0:
                        view = inflater.inflate(R.layout.about_fragment_app, container, false);
                        editAboutTheApp();
                        return view;
                    case 1:
                        view = inflater.inflate(R.layout.about_fragment_creaters, container, false);
                        editCreatersFragment();
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
        TextView dataIntro = (TextView) view.findViewById((R.id.data_intro));
        dataIntro.setTypeface(retroFont);
        TextView hefa = (TextView) view.findViewById((R.id.hefa));
        hefa.setTypeface(retroFont);
        TextView nss = (TextView) view.findViewById((R.id.nss));
        nss.setTypeface(retroFont);
        TextView dlhe = (TextView) view.findViewById((R.id.dlhe));
        dlhe.setTypeface(retroFont);
    }

    private  void animateToLocation(final Button object, final float right, final float down){

        final float startX = object.getX();
        final float startY = object.getY();
        Point center = arcLayout.getOrigin();

        TranslateAnimation anim = new TranslateAnimation(0, right, 0 , down );
        anim.setDuration(1000);
        anim.setAnimationListener(new TranslateAnimation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                object.setX(startX + right);
                object.setY(startY + down);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        object.startAnimation(anim);


    }
    /**
     * Animates the search arm by rotating it slightly and translating across a bit of the x axis
     */
    private void animateToCenter(final Button object){

        final float startX = object.getX();
        final float startY = object.getY();
        Point center = arcLayout.getOrigin();

        final float right = center.x - startX - 100;
        final float down = center.y - startY - 200;
        TranslateAnimation anim = new TranslateAnimation(0, right, 0 , down );
        anim.setDuration(1000);
        anim.setAnimationListener(new TranslateAnimation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                object.setX(startX + right);
                object.setY(startY + down);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        object.startAnimation(anim);
    }


    /**
     * Sets the font type of the Meet the team Fragment
     */
    private void editCreatersFragment(){
        mCoverFlow = (FeatureCoverFlow) view.findViewById(R.id.coverflow);
        final ArrayList<Person> team = new ArrayList<>();

        team.add(new Person(R.drawable.chris, R.string.chris_personal));
        team.add(new Person(R.drawable.jack, R.string.jack_personal));
        team.add(new Person(R.drawable.tel, R.string.tel_personal));
        team.add(new Person(R.drawable.dan, R.string.jack_personal));
        CoverFlowAdapter mAdapter = new CoverFlowAdapter(this.getContext());
        mAdapter.setData(team);
        mCoverFlow.setAdapter(mAdapter);
        final TextSwitcher mTitle = (TextSwitcher) view.findViewById(R.id.title);
        Animation in = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_out_bottom);
        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);

        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(AboutFragmentSelecter.this.getContext());
                TextView textView = (TextView) inflater.inflate(R.layout.item_title, null);
                return textView;
            }
        });

        mCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(AboutFragmentSelecter.this.getContext(),
                        getResources().getString(team.get(position).titleResId),
                        Toast.LENGTH_SHORT).show();
            }
        });

        mCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText(getResources().getString(team.get(position).titleResId));
            }

            @Override
            public void onScrolling() {
                mTitle.setText("");
            }
        });


        /**
        arcLayout = (ArcLayout) view.findViewById(R.id.arcAboutTeamPage);
        final TextView personDescription = (TextView) view.findViewById(R.id.personDescriptionAboutUs);

        final Button chrisButton = (Button) view.findViewById(R.id.chrisButton);
        final Button danButton = (Button) view.findViewById(R.id.danButton);
        final Button jackButton = (Button) view.findViewById(R.id.jackButton);
        final Button telButton = (Button) view.findViewById(R.id.telButton);
        chrisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personDescription.setText(getString(R.string.chris_personal));
                animateToCenter(chrisButton);
            }
        });

        danButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personDescription.setText(getString(R.string.dan_personal));
                animateToCenter(danButton);


            }
        });

        jackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personDescription.setText(getString(R.string.jack_personal));
                animateToCenter(jackButton);

            }
        });

        telButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personDescription.setText(getString(R.string.tel_personal));
                animateToCenter(telButton);

            }
        });



        //TextView meetTheTeamIntro = (TextView) view.findViewById(R.id.meet_the_team_intro);
        //meetTheTeamIntro.setTypeface(retroFont);
        //TextView chrisDetails = (TextView) view.findViewById(R.id.chris);
        //chrisDetails.setTypeface(retroFont);
        //TextView telDetails = (TextView) view.findViewById(R.id.terry);
        //telDetails.setTypeface(retroFont);
        //TextView danDetails = (TextView) view.findViewById(R.id.dan);
        //danDetails.setTypeface(retroFont);
        //TextView jackDetails = (TextView) view.findViewById(R.id.jack);
        //jackDetails.setTypeface(retroFont);
         */
    }



}
