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

        TextView titleText = (TextView) view.findViewById(R.id.interview_intro_statement);
        titleText.setTypeface(retroFont);

        //Creates a list of tips that will be displayed in a list view
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
     * Creates the Personal Statement page fragment by creating an array list of tips, which contain the title, content and image
     * which is then sent to the tip icon adapter on the page
     */
    private void editUcasTipsFragmentPersonalStatement() {

        TipWithIconListAdapter adapter;
        ExpandableLayoutListView listView = (ExpandableLayoutListView) view.findViewById(R.id.personal_statement_tips_list);

        TextView titleText = (TextView) view.findViewById(R.id.personal_statement_intro_statement);
        titleText.setTypeface(retroFont);

        //Creates a list of tips that will be displayed in a list view

        ArrayList<TipEntry> tips = new ArrayList<>();
        tips.add(new TipEntry(R.string.ucas_tips_opening_paragraph_title, R.string.ucas_tips_opening_paragraph, R.drawable.opening_paragraph));
        tips.add(new TipEntry(R.string.ucas_tips_Reasons_to_study_the_course_title, R.string.ucas_tips_Reasons_to_study_the_course, R.drawable.reason_to_study));
        tips.add(new TipEntry(R.string.ucas_tips_the_course_title, R.string.ucas_tips_the_course, R.drawable.course));
        tips.add(new TipEntry(R.string.ucas_tips_extra_work_title, R.string.ucas_tips_extra_work, R.drawable.extra_work));
        tips.add(new TipEntry(R.string.ucas_tips_current_skills_title, R.string.ucas_tips_current_skills, R.drawable.skills));
        tips.add(new TipEntry(R.string.ucas_tips_independant_and_analytical_work_title, R.string.ucas_tips_independant_and_analytical_work, R.drawable.independant_analytical));
        tips.add(new TipEntry(R.string.ucas_tips_your_expectations_title, R.string.ucas_tips_your_expectations, R.drawable.expectations));
        tips.add(new TipEntry(R.string.ucas_tips_take_your_time_title, R.string.ucas_tips_take_your_time, R.drawable.take_your_time));
        tips.add(new TipEntry(R.string.ucas_tips_be_excited_title, R.string.ucas_tips_be_excited, R.drawable.be_excited));
        tips.add(new TipEntry(R.string.ucas_tips_your_grammer_title, R.string.ucas_tips_your_grammer, R.drawable.grammer));
        tips.add(new TipEntry(R.string.ucas_tips_the_thesaurus_title, R.string.ucas_tips_the_thesaurus, R.drawable.thesaurus));

        if(!tips.isEmpty()){
            adapter = new TipWithIconListAdapter(getActivity(), R.layout.tip_list_row, R.layout.tip_list_header, tips);
            listView.setAdapter(adapter);
        }





    }

    /**
     * Creates the Choosing the right course page fragment by creating an array list of tips, which contain the title, content and image
     * which is then sent to the tip icon adapter on the page
     */
    private void editUcasTipsFragmentChoosingTheRightCourse() {
        TextView ucasTipsChoosingCourseIntro = (TextView) view.findViewById(R.id.choosing_intro_statement);
        ucasTipsChoosingCourseIntro.setTypeface(retroFont);

        TipWithIconListAdapter adapter;
        ExpandableLayoutListView listView = (ExpandableLayoutListView) view.findViewById(R.id.choosing_course_list);

        ArrayList<TipEntry> tips = new ArrayList<>();
        tips.add(new TipEntry(R.string.ucas_tips_hobby_title, R.string.ucas_tips_hobby,  R.drawable.bowling));
        tips.add(new TipEntry(R.string.ucas_tips_entry_requirments_title, R.string.ucas_tips_entry_requirments, R.drawable.microscope));
        tips.add(new TipEntry(R.string.ucas_tips_location_title, R.string.ucas_tips_location, R.drawable.house));
        tips.add(new TipEntry(R.string.ucas_tips_goals_title, R.string.ucas_tips_goals, R.drawable.goals_icon4));
        tips.add(new TipEntry(R.string.ucas_tips_research_title, R.string.ucas_tips_research,  R.drawable.targets_icon2));
        tips.add(new TipEntry(R.string.ucas_tips_talk_title, R.string.ucas_tips_talk, R.drawable.chat_icon2));

        if(!tips.isEmpty()){
            adapter = new TipWithIconListAdapter(getActivity(), R.layout.tip_list_row, R.layout.tip_list_header, tips);
            listView.setAdapter(adapter);
        }

    }

}
