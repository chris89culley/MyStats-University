package Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.chris.mystats_univeristy.CourseStats;
import com.example.chris.mystats_univeristy.R;
import java.util.ArrayList;
import Data.Course;
import Utilities.Colours;

/**
 * This class acts as an adapter for the course list view
 */

public class CourselistAdapter extends ArrayAdapter<Course>{

    private Activity activity;
    private ArrayList<Course> courses;
    private static LayoutInflater inflater = null;
    private static int[] colours = {Colours.GREEN_SHEEN.getColor(),
            Colours.BACKGROUND_GREEN.getColor()
    };
    private Typeface retroFont = Typeface.createFromAsset(activity.getAssets(), "fonts/Josefin_Sans/JosefinSans-SemiBold.ttf");
    private Typeface vintage = Typeface.createFromAsset(activity.getAssets(), "fonts/octin vintage b rg.ttf");

    /**
     * Constructor makes calls to the ArrayAdapter super class with the res id's of the layouts for the header
     * and content of each searched item
     * @param activity - The activity the adapter is related to
     * @param textViewResourceId - The id of the text view
     * @param header_id - The id of the header layout
     * @param courses - The data set which is to be displayed
     */
    public CourselistAdapter(Activity activity, int textViewResourceId , int header_id , ArrayList<Course> courses) {
        super(activity, textViewResourceId, header_id, courses);
        this.activity = activity;
        this.courses = courses;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    /**
     * Makes the more stats button shake to indicate to the user that the places they are currently
     * clicking are not valid and that they should in fact be clicking the more stats button
     * @param moreStatsButton - The button that should be clicked and will shake to indicate
     * @param averageSalaryAfter6 - A text view that can't be clicked
     * @param percentThatGoOnToWork - A text view that can't be clicked
     * @param averageSatisfaction - A text view that can't be clicked
     * @param mode - A text view that can't be clicked
     */
    private void makeThebuttonShakeWhenViewIsClicked(final Button moreStatsButton,
                                                     TextView averageSalaryAfter6,
                                                     TextView percentThatGoOnToWork,
                                                     TextView averageSatisfaction,
                                                     TextView mode ){

       View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Animation shake = AnimationUtils.loadAnimation(v.getContext(), R.anim.shake);
                shake.setDuration(1500);
                shake.setInterpolator(new CycleInterpolator(10));
                moreStatsButton.startAnimation(shake);

            }
        };
        averageSalaryAfter6.setOnClickListener(listener);
        percentThatGoOnToWork.setOnClickListener(listener);
        averageSatisfaction.setOnClickListener(listener);
        mode.setOnClickListener(listener);

    }

    /**
     * Sets the rows text based on the course
     * @param averageSalaryAfter6 - The text field relating to salary after 6 months
     * @param percentThatGoOnToWork - The text field relating to the percentage that go on to work or study
     * @param averageSatisfaction - The text field relating to the course satisfaction
     * @param mode - The text field relating to the course study mode
     * @param courseName - The text field relating to the course name
     * @param universityname - The text field relating to the university name
     * @param course - The  course the row is displaying
     */
    private void setTheRowText(TextView averageSalaryAfter6,
                               TextView percentThatGoOnToWork,
                               TextView averageSatisfaction,
                               TextView mode,
                               TextView courseName,
                               TextView universityname,
                               Course course
                               ){

        mode.setText("Study mode : " + course.getModeText());
        averageSalaryAfter6.setText("Average salary after 6 months : " + course.getAverageSalaryAfter6MonthsText());
        percentThatGoOnToWork.setText("Percent that go on to work or study : " + course.getPercentageTheWorkOrStudyText());
        averageSatisfaction.setText("Percentage satisfied with the course : " + course.getPercentageThatAreSatisfiedText());
        courseName.setText(course.getFullCourseName());
        universityname.setText(course.getUniversityWhereCourseIsTaught());
        universityname.setAllCaps(true);
    }

    /**
     * Sets the rows fonts
     *
     * @param averageSalaryAfter6 - The text field relating to salary after 6 months
     * @param percentThatGoOnToWork - The text field relating to the percentage that go on to work or study
     * @param averageSatisfaction - The text field relating to the course satisfaction
     * @param mode - The text field relating to the course study mode
     * @param courseName - The text field relating to the course name
     * @param universityname - The text field relating to the university name
     * @param moreStatsButton - The button which links to more stats
     */
    private void setTheRowFonts(TextView averageSalaryAfter6,
                                TextView percentThatGoOnToWork,
                                TextView averageSatisfaction,
                                TextView mode,
                                TextView courseName,
                                TextView universityname,
                                Button moreStatsButton
    ){
        mode.setTypeface(retroFont);
        averageSalaryAfter6.setTypeface(retroFont);
        percentThatGoOnToWork.setTypeface(retroFont);
        averageSatisfaction.setTypeface(retroFont);
        courseName.setTypeface(retroFont);
        universityname.setTypeface(vintage);
        moreStatsButton.setTypeface(retroFont);

    }

    /**
     * Sets up the more stats button such that when it is clicked the application moves to show more details
     * on the course stats page giving more details of the course selected
     * @param rowView - The view that contains the rows
     * @param moreStatsButton - The button that when clicked moves to the more stats page
     * @param course - The course selected
     */
    private void setTheOnClickForTheMoreStatsButton(View rowView, Button moreStatsButton, Course course){
        moreStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rowView.getContext(), CourseStats.class);
                intent.putExtra("chosenCourse", (Parcelable) course);
                activity.startActivity(intent);
            }
        });
    }

    /**
     * Sets the rows colour alternating colours from the list of colours based on the row position
     * @param rowView - The view where the row is displayed
     * @param position - The position of the row (has an impact on which colour to be displayed)
     */
    private void setTheRowColour(View rowView, int position){
        int colour = (colours[position % colours.length]);
        rowView.findViewById(R.id.row).setBackgroundColor(colour);
    }

    /**
     * This method handles the creation of the content for each row
     * @param position - The position in the list view we are at

     * @param parent - The parent to this row
     * @return - The row with content
     */
    public View getView(int position, final View convertView, ViewGroup parent){

        final View rowView = inflater.inflate(R.layout.search_row, parent , false);
        TextView universityname = (TextView) rowView.findViewById(R.id.universityName);
        TextView courseName = (TextView) rowView.findViewById(R.id.courseName);
        final TextView mode = (TextView) rowView.findViewById(R.id.mode);
        TextView averageSalaryAfter6 = (TextView) rowView.findViewById(R.id.salaryAfter6);
        TextView percentThatGoOnToWork = (TextView) rowView.findViewById(R.id.percentThatGoToWork);
        TextView averageSatisfaction = (TextView) rowView.findViewById(R.id.overallSatisafaction);
        final Course course = courses.get(position);
        final Button moreStatsButton = (Button) rowView.findViewById(R.id.moreStatsButton);

        //Stops an error being thrown when we get to the bottom of the list view
        if(position > courses.size()-1){
            return rowView;
        }

        setTheRowColour(rowView,position);
        makeThebuttonShakeWhenViewIsClicked(
                moreStatsButton,
                averageSalaryAfter6,
                percentThatGoOnToWork,
                averageSatisfaction,
                mode);

        setTheRowText(
                averageSalaryAfter6,
                percentThatGoOnToWork,
                averageSatisfaction,
                mode,
                courseName,
                universityname,
                course
                );

        setTheRowFonts(
                averageSalaryAfter6,
                percentThatGoOnToWork,
                averageSatisfaction,
                mode,
                courseName,
                universityname,
                moreStatsButton
        );

        setTheOnClickForTheMoreStatsButton(rowView, moreStatsButton, course);

        return rowView;
    }


}
