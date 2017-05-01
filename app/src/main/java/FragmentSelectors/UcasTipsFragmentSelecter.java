package FragmentSelectors;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andexert.expandablelayout.library.ExpandableLayoutListView;
import com.example.chris.mystats_univeristy.R;

import java.util.ArrayList;

import Adapters.TipWithIconListAdapter;
import Data.TipEntry;

/**
 * Created by Terence Lawson on 18/04/2017.
 */

public class UcasTipsFragmentSelecter extends Fragment {

    private View view;
    private int pos;
    private Activity activity;
    Typeface retroFont;


    /**
     * sets the position of the fragment pager and the course data
     * @param position
     */
    public UcasTipsFragmentSelecter(int position, Activity activity) {
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        retroFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Josefin_Sans/JosefinSans-SemiBold.ttf");
        //test Comment
        //Cycles through the fragments choosing which one to inflate
        switch (pos) {
            case 0:
                view = inflater.inflate(R.layout.ucas_tips_fragment_choosing_the_right_course, container, false);
                editUcasTipsFragmentChoosingTheRightCourse();
                return view;
            case 1:
                view = inflater.inflate(R.layout.ucas_tips_fragment_personal_statement, container, false);
                editUcasTipsFragmentPersonalStatement();
                return view;
            case 2:
                view = inflater.inflate(R.layout.ucas_tips_fragment_interview, container, false);
                createUcasTipsInterviewFragment();
                return view;

        }
        return view;
    }

    /**
     * Creates the interview page fragment by creating an array list of tips, which contain the title, content and image
     * which is then sent to the tip icon adapter on the page
     */
    private void createUcasTipsInterviewFragment() {

        TipWithIconListAdapter adapter;
        ExpandableLayoutListView listView = (ExpandableLayoutListView) view.findViewById(R.id.tips_list);

        ArrayList<TipEntry> tips = new ArrayList<>();
        tips.add(new TipEntry(R.string.ucas_tips_interview_checking_title, R.string.ucas_tips_checking, R.drawable.letter_box));
        tips.add(new TipEntry(R.string.ucas_tips_interview_preparing_title, R.string.ucas_tips_preparing, R.drawable.practice));
        tips.add(new TipEntry(R.string.ucas_tips_interview_times_title, R.string.ucas_tips_times, R.drawable.calendar));
        tips.add(new TipEntry(R.string.ucas_tips_interview_dress_title, R.string.ucas_tips_dress, R.drawable.tie));
        tips.add(new TipEntry(R.string.ucas_tips_interview_travel_plan, R.string.ucas_tips_plan_your_travel, R.drawable.car));
        tips.add(new TipEntry(R.string.ucas_tips_intrview_practice_title, R.string.ucas_tips_practice, R.drawable.mirror));
        tips.add(new TipEntry(R.string.ucas_tip_interview_be_yourself_title, R.string.ucas_tips_be_yourself, R.drawable.beyourself));

        if(!tips.isEmpty()){
            adapter = new TipWithIconListAdapter(getActivity(), R.layout.tip_list_row, R.layout.tip_list_header, tips);
            listView.setAdapter(adapter);
        }
    }

    /**
     * Sets the font type of the PersonalStatement Fragment
     */
    private void editUcasTipsFragmentPersonalStatement() {
        TextView ucasTipsPersonalStatementIntroduction = (TextView) view.findViewById(R.id.personal_statement_introduction);
        ucasTipsPersonalStatementIntroduction.setTypeface(retroFont);


        TextView ucasTipsTakeYourTime = (TextView) view.findViewById(R.id.ucas_tips_take_your_time);
        ucasTipsTakeYourTime.setTypeface(retroFont);

        TextView ucasTipsBeExcited = (TextView) view.findViewById(R.id.ucas_tips_be_excited);
        ucasTipsBeExcited.setTypeface(retroFont);



        TextView ucasTipsBePrepared = (TextView) view.findViewById(R.id.personal_statement_tips);
        ucasTipsBePrepared.setTypeface(retroFont);

        TextView ucasTipsSellYourself = (TextView) view.findViewById(R.id.ucas_tips_the_thesaurus);
        ucasTipsSellYourself.setTypeface(retroFont);

        TextView ucasTipsYourGrammer = (TextView) view.findViewById(R.id.ucas_tips_your_grammer);
        ucasTipsYourGrammer.setTypeface(retroFont);

    }

    /**
     * Sets the font type of the Choosing the Right Course Fragment
     */
    private void editUcasTipsFragmentChoosingTheRightCourse() {
        TextView ucasTipsChoosingCourseIntro = (TextView) view.findViewById(R.id.ucas_tips_choosing_course_intro);
        ucasTipsChoosingCourseIntro.setTypeface(retroFont);



        TextView ucasTipsHobby = (TextView) view.findViewById(R.id.ucas_tips_hobby);
        ucasTipsHobby.setTypeface(retroFont);

        TextView ucasTipsEntryRequirements = (TextView) view.findViewById(R.id.ucas_tips_entry_requirments);
        ucasTipsEntryRequirements.setTypeface(retroFont);

        TextView ucasTipsLocation = (TextView) view.findViewById(R.id.ucas_tips_location);
        ucasTipsLocation.setTypeface(retroFont);

        TextView ucasTipsLocationQ1 = (TextView) view.findViewById(R.id.ucas_tips_location_q1);
        ucasTipsLocationQ1.setTypeface(retroFont);

        TextView ucasTipsLocationA1 = (TextView) view.findViewById(R.id.ucas_tips_location_a1);
        ucasTipsLocationA1.setTypeface(retroFont);

        TextView ucasTipsLocationQ2 = (TextView) view.findViewById(R.id.ucas_tips_location_q2);
        ucasTipsLocationQ2.setTypeface(retroFont);

        TextView ucasTipsLocationA2 = (TextView) view.findViewById(R.id.ucas_tips_location_a2);
        ucasTipsLocationA2.setTypeface(retroFont);

        TextView ucasTipsLocationQ3 = (TextView) view.findViewById(R.id.ucas_tips_location_q3);
        ucasTipsLocationQ3.setTypeface(retroFont);

        TextView ucasTipsLocationA3 = (TextView) view.findViewById(R.id.ucas_tips_location_a3);
        ucasTipsLocationA3.setTypeface(retroFont);

        TextView ucasTipsLocationQ4 = (TextView) view.findViewById(R.id.ucas_tips_location_q4);
        ucasTipsLocationQ4.setTypeface(retroFont);

        TextView ucasTipsLocationA4 = (TextView) view.findViewById(R.id.ucas_tips_location_a4);
        ucasTipsLocationA4.setTypeface(retroFont);


    }

}
