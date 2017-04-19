package FragmentSelecters;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
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


        try {
            switch (pos){
                case 0:
                    view = inflater.inflate(R.layout.ucas_tips_fragment_intro, container, false);
                    editUcasTipsFragmentIntro();
                    return view;
                case 1:
                    view = inflater.inflate(R.layout.ucas_tips_fragment_choosing_the_right_course, container, false);
                    editUcasTipsFragmentChoosingTheRightCourse();
                    return view;
                case 2:
                    view = inflater.inflate(R.layout.ucas_tips_fragment_personal_statement, container, false);
                    editUcasTipsFragmentPersonalStatement();
                    return view;
                case 3:
                    view = inflater.inflate(R.layout.ucas_tips_fragment_interview, container, false);
                    editUcasTipsFragmentInterview();
                    return view;
            }

        }catch(Exception IO){
            return view = inflater.inflate(R.layout.fragment_error, container, false);

        }


        return view;
    }

    private void editUcasTipsFragmentInterview() {
    }

    private void editUcasTipsFragmentPersonalStatement() {
        TextView ucasTipsPersonalStatementIntroduction = (TextView) view.findViewById(R.id.personal_statement_introduction);
        ucasTipsPersonalStatementIntroduction.setTypeface(retroFont);

        TextView ucasTipsOpeningParagraph = (TextView) view.findViewById(R.id.ucas_tips_opening_paragraph);
        ucasTipsOpeningParagraph.setTypeface(retroFont);

        TextView ucasTipsTakeYourTime = (TextView) view.findViewById(R.id.ucas_tips_take_your_time);
        ucasTipsTakeYourTime.setTypeface(retroFont);

        TextView ucasTipsBeExcited = (TextView) view.findViewById(R.id.ucas_tips_be_excited);
        ucasTipsBeExcited.setTypeface(retroFont);

        TextView ucasTipsBePositive = (TextView) view.findViewById(R.id.ucas_tips_be_positive);
        ucasTipsBePositive.setTypeface(retroFont);

        TextView ucasTipsBePrepared = (TextView) view.findViewById(R.id.ucas_tips_be_prepared);
        ucasTipsBePrepared.setTypeface(retroFont);

        TextView ucasTipsSellYourself = (TextView) view.findViewById(R.id.ucas_tips_sell_yourself);
        ucasTipsSellYourself.setTypeface(retroFont);

        TextView ucasTipsYourExpectations = (TextView) view.findViewById(R.id.ucas_tips_your_expectations);
        ucasTipsYourExpectations.setTypeface(retroFont);

        TextView ucasTipsYourGrammer = (TextView) view.findViewById(R.id.ucas_tips_your_grammer);
        ucasTipsYourGrammer.setTypeface(retroFont);

    }

    private void editUcasTipsFragmentChoosingTheRightCourse() {
        TextView ucasTipsChoosingCourseIntro = (TextView) view.findViewById(R.id.ucas_tips_choosing_course_intro);
        ucasTipsChoosingCourseIntro.setTypeface(retroFont);

        TextView ucasTipsCareer = (TextView) view.findViewById(R.id.ucas_tips_career);
        ucasTipsCareer.setTypeface(retroFont);

        TextView ucasTipsHobby = (TextView) view.findViewById(R.id.ucas_tips_hobby);
        ucasTipsHobby.setTypeface(retroFont);

        TextView ucasTipsEntryRequirements = (TextView) view.findViewById(R.id.ucas_tips_entry_requirments);
        ucasTipsEntryRequirements.setTypeface(retroFont);

        TextView ucasTipsLocation = (TextView) view.findViewById(R.id.ucas_tips_location);
        ucasTipsLocation.setTypeface(retroFont);

        TextView ucasTipsModules = (TextView) view.findViewById(R.id.ucas_tips_modules);
        ucasTipsModules.setTypeface(retroFont);

        TextView ucasTipsOpenDays = (TextView) view.findViewById(R.id.ucas_tips_open_days);
        ucasTipsOpenDays.setTypeface(retroFont);
    }

    private void editUcasTipsFragmentIntro() {
        TextView introText = (TextView) view.findViewById(R.id.ucastips_introduction);
        introText.setTypeface(retroFont);
    }
}
