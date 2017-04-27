package Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.chris.mystats_univeristy.CourseStats;
import com.example.chris.mystats_univeristy.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Data.Course;
import Utilities.Colours;
import Utilities.FontGrabber;

/**
 * Created by chris on 09/03/17.
 */

public class CourselistAdapter extends ArrayAdapter<Course>{

    private Activity activity;
    private ArrayList<Course> courses;
    private static LayoutInflater inflater = null;
    private static Set<String> added = new HashSet<>();
    private static int[] colours = {Colours.GREEN_SHEEN.getColor(),
            Colours.BACKGROUND_GREEN.getColor()
    };

    public CourselistAdapter(Activity activity, int textViewResourceId , int header_id , ArrayList<Course> courses) {
        super(activity, textViewResourceId, header_id, courses);
        this.activity = activity;
        this.courses = courses;
        added.clear();
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        TextView mode = (TextView) rowView.findViewById(R.id.mode);
        TextView averageSalaryAfter6 = (TextView) rowView.findViewById(R.id.salaryAfter6);
        TextView percentThatGoOnToWork = (TextView) rowView.findViewById(R.id.percentThatGoToWork);
        TextView averageSatisfaction = (TextView) rowView.findViewById(R.id.overallSatisafaction);
        Typeface retroFont = Typeface.createFromAsset(activity.getAssets(), "fonts/Josefin_Sans/JosefinSans-SemiBold.ttf");
        Typeface vintage = Typeface.createFromAsset(activity.getAssets(), "fonts/octin vintage b rg.ttf");
        Button moreStatsButton = (Button) rowView.findViewById(R.id.moreStatsButton);


        //Stops an error being thrown when we get to the bottom of the list view
        if(position > courses.size()-1){
            return rowView;
        }

        final Course course = courses.get(position);

        //Sets the rows colour (alternates between all the colours)
        int colour = (colours[position % colours.length]);
        rowView.findViewById(R.id.row).setBackgroundColor(colour);



        //Sets the fonts of the content
        mode.setTypeface(retroFont);
        averageSalaryAfter6.setTypeface(retroFont);
        percentThatGoOnToWork.setTypeface(retroFont);
        averageSatisfaction.setTypeface(retroFont);
        courseName.setTypeface(retroFont);
        universityname.setTypeface(vintage);
        moreStatsButton.setTypeface(retroFont);



        //Sets the row text
        mode.setText("Study mode : " + course.getModeText());
        averageSalaryAfter6.setText("Average salary after 6 months : " + course.getAverageSalaryAfter6MonthsText());
        percentThatGoOnToWork.setText("Percent that go on to work or study : " + course.getPercentageTheWorkOrStudyText());
        averageSatisfaction.setText("Percentage satisfied with the course : " + course.getPercentageThatAreSatisfiedText());
        courseName.setText(course.getFullCourseName());
        universityname.setText(courses.get(position).getUniversityWhereCourseIsTaught());
        universityname.setAllCaps(true);


        //If the search course button is pressed then we move to the more stats page
        moreStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rowView.getContext(), CourseStats.class);
               intent.putExtra("chosenCourse", (Parcelable) course);
               activity.startActivity(intent);
            }
        });

        Animation shake = AnimationUtils.loadAnimation(this.getContext(), R.anim.shake);
        shake.setDuration(1500);
        shake.setInterpolator(new CycleInterpolator(10));
        moreStatsButton.setAnimation(shake);

        return rowView;
    }


}
