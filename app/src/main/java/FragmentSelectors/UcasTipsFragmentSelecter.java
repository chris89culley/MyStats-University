package FragmentSelectors;

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

        //Cycles through the fragments choosing which one to inflate
        try {
            switch (pos){
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
                    editUcasTipsFragmentInterview();
                    return view;
            }

        }catch(Exception IO){
            return view = inflater.inflate(R.layout.fragment_error, container, false);

        }


        return view;
    }

    /**
     * Sets the font type of the Interview Fragment
     */
    private void editUcasTipsFragmentInterview() {
        TextView ucasTipsInterviewIntro = (TextView) view.findViewById(R.id.ucas_tips_interview_intro);
        ucasTipsInterviewIntro.setTypeface(retroFont);

        TextView ucasTipsChecking = (TextView) view.findViewById(R.id.ucas_tips_checking);
        ucasTipsChecking.setTypeface(retroFont);

        TextView ucasTipsTimes = (TextView) view.findViewById(R.id.ucas_tips_times);
        ucasTipsTimes.setTypeface(retroFont);

        TextView ucasTipsPreparing = (TextView) view.findViewById(R.id.ucas_tips_preparing);
        ucasTipsPreparing.setTypeface(retroFont);

        TextView ucasTipsPlanYourTravel = (TextView) view.findViewById(R.id.ucas_tips_plan_your_travel);
        ucasTipsPlanYourTravel.setTypeface(retroFont);

        TextView ucasTipsDress = (TextView) view.findViewById(R.id.ucas_tips_dress);
        ucasTipsDress.setTypeface(retroFont);

        TextView ucasTipsBeYourSelf = (TextView) view.findViewById(R.id.ucas_tips_be_yourself);
        ucasTipsBeYourSelf.setTypeface(retroFont);

        TextView ucasTipsPractice = (TextView) view.findViewById(R.id.ucas_tips_practice);
        ucasTipsPractice.setTypeface(retroFont);

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
