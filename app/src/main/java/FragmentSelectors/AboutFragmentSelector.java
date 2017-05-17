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
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.example.chris.mystats_univeristy.R;
import java.util.ArrayList;
import Adapters.CoverFlowAdapter;
import Data.Person;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

/**
 *
 * AboutFragmentSelector contains all the fragment information with respect to the about page
 *
 */

public class AboutFragmentSelector extends Fragment {

    private View view;
    private int pos;
    private Typeface retroFont;

    /**
     * sets the position of the fragment pager and the course data
     *
     * @param position
     */
    public AboutFragmentSelector(int position) {
        this.pos = position;
    }


    /**
     * on create view determines which fragment to inflate, pending on the position.
     *
     * @param inflater           - The layout inflator
     * @param container          - The container where the view is to be held
     * @param savedInstanceState - The current saved instance
     * @return The view containing the selected fragment
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        retroFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Josefin_Sans/JosefinSans-SemiBold.ttf");

        //Cycles through the fragments choosing which one to inflate
        try {
            switch (pos) {
                case 0:
                    view = inflater.inflate(R.layout.about_fragment_app, container, false);
                    createAboutAppFragment();
                    return view;
                case 1:
                    view = inflater.inflate(R.layout.about_fragment_creators, container, false);
                    createAboutTheTeamFragment();
                    return view;
                case 2:
                    view = inflater.inflate(R.layout.about_fragment_the_data, container, false);

                    createDataFragment();
                    return view;
                case 3:
                    view = inflater.inflate(R.layout.about_fragment_copyright, container, false);
                    createCopyrightFragment();
                    return view;
            }

        } catch (Exception IO) {
            return view = inflater.inflate(R.layout.fragment_error, container, false);

        }
        return view;
    }


    /**
     * Sets the font for the copyright page
     *
     * @param pageIntro          - The page introduction
     * @param copyUniStats       - The uni stats info text
     * @param copyHesaDisclaimer - The Hesa disclaimer text
     * @param furtherInfo        - Further info text
     * @param emailTxt           - Email link text
     * @param imageInfo          - Where the images come from text
     * @param freepikTxt         - Details of freepik text
     * @param logomakrTxt        - Details of logomaker text
     */
    private void setCopyrightFragementFonts(TextView pageIntro, TextView copyUniStats, TextView copyHesaDisclaimer,
                                            TextView furtherInfo, TextView emailTxt, TextView imageInfo,
                                            TextView freepikTxt, TextView logomakrTxt) {
        pageIntro.setTypeface(retroFont);
        copyUniStats.setTypeface(retroFont);
        copyHesaDisclaimer.setTypeface(retroFont);
        furtherInfo.setTypeface(retroFont);
        freepikTxt.setTypeface(retroFont);
        emailTxt.setTypeface(retroFont);
        imageInfo.setTypeface(retroFont);
        logomakrTxt.setTypeface(retroFont);


    }

    /**
     * Sets the font type of the Copyright Fragment
     */
    private void createCopyrightFragment() {

        TextView pageIntro = (TextView) view.findViewById(R.id.copy_intro);
        TextView copyUniStats = (TextView) view.findViewById(R.id.copy_unistats_disclaimer);
        TextView copyHesaDisclaimer = (TextView) view.findViewById(R.id.copu_hesa_disclaimer);
        TextView furtherInfo = (TextView) view.findViewById(R.id.further_info);
        TextView emailTxt = (TextView) view.findViewById(R.id.email_linked);
        TextView imageInfo = (TextView) view.findViewById(R.id.image_info);
        TextView freepikTxt = (TextView) view.findViewById(R.id.freepik_info);
        TextView logomakrTxt = (TextView) view.findViewById(R.id.logomakr_info);

        emailTxt.setMovementMethod(LinkMovementMethod.getInstance());
        freepikTxt.setMovementMethod(LinkMovementMethod.getInstance());
        logomakrTxt.setMovementMethod(LinkMovementMethod.getInstance());

        setCopyrightFragementFonts(pageIntro, copyUniStats,
                copyHesaDisclaimer, furtherInfo,
                emailTxt, imageInfo, freepikTxt,
                logomakrTxt);
    }

    /**
     * Sets the font type of the About the App Fragment
     */
    private void createAboutAppFragment() {
        TextView aboutTitle = (TextView) view.findViewById(R.id.aboutTitle);
        aboutTitle.setTypeface(retroFont);
        TextView basic_info = (TextView) view.findViewById(R.id.basic_intro);
        basic_info.setTypeface(retroFont);
    }

    /**
     * Sets the font type of the The Data Fragment
     */
    private void createDataFragment() {

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
     *
     * @return - The array of team members
     */
    private ArrayList<Person> createAnArrayListOfStaff() {
        final ArrayList<Person> team = new ArrayList<>();
        team.add(new Person(R.drawable.chris, R.string.chris_personal));
        team.add(new Person(R.drawable.jack, R.string.jack_personal));
        team.add(new Person(R.drawable.tel, R.string.tel_personal));
        team.add(new Person(R.drawable.dan, R.string.dan_personal));
        return team;
    }

    /**
     * Adds the animations to the description when they fade in and out (using the text switcher)
     *
     * @param description The description to add animations to
     */
    private void setAnimationsForDescription(TextSwitcher description) {
        Animation in = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_out_bottom);
        description.setInAnimation(in);
        description.setOutAnimation(out);
    }


    /**
     * Updates the description depending on the image carousel position
     *
     * @param description   - The team member description
     * @param imageCarousel - The carousel of images
     * @param team          - The list of team members
     */
    private void setUpCarouselPositionUpdate(final TextSwitcher description, FeatureCoverFlow imageCarousel, final ArrayList<Person> team) {

        imageCarousel.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                description.setText(getResources().getString(team.get(position).getTitleResId()));
            }

            @Override
            public void onScrolling() {
                description.setText("");
            }
        });


    }

    /**
     *
     * Sets up the carousel text switcher which will be able to change the displayed text
     * depending on which person is displayed
     *
     * @param description - The text switcher containing the description
     */
    private void setUpCarouselTextSwitcher(TextSwitcher description) {

        description.setVerticalScrollBarEnabled(true);
        setAnimationsForDescription(description);

        description.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(AboutFragmentSelector.this.getContext());
                TextView textView = (TextView) inflater.inflate(R.layout.item_title, null);
                textView.setTextSize(11.0f);
                textView.setTypeface(retroFont);
                return textView;
            }
        });


    }


    /**
     * Creates the about the team fragment which holds pictures of all the staff in a carousel style
     * and there descriptions
     */
    private void createAboutTheTeamFragment() {

        FeatureCoverFlow imageCarousel = (FeatureCoverFlow) view.findViewById(R.id.coverflow);
        CoverFlowAdapter mAdapter = new CoverFlowAdapter(this.getContext());
        final ArrayList<Person> team = createAnArrayListOfStaff();
        mAdapter.setData(team);
        imageCarousel.setAdapter(mAdapter);
        final TextSwitcher description = (TextSwitcher) view.findViewById(R.id.title);
        setUpCarouselTextSwitcher(description);
        setUpCarouselPositionUpdate(description, imageCarousel, team);

    }
}
