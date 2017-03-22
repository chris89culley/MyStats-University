package Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by chris on 09/03/17.
 */

public class CourseListAdapter extends ArrayAdapter<Course>{

    private Activity activity;
    private ArrayList<Course> courses;
    private static LayoutInflater inflater = null;
    private static Set<String> added = new HashSet<>();
    private static int[] colours = {Colours.LIGHT_BLUE_SEARCH_PAGE.getColor(),
                                        Colours.MED_BLUE_SEARCH_PAGE.getColor(),
                                        Colours.DARK_BLUE_SEARCH_PAGE.getColor()};

    public CourseListAdapter(Activity activity, int textViewResourceId , int header_id , ArrayList<Course> courses) {
        super(activity, textViewResourceId, header_id, courses);
        this.activity = activity;
        this.courses = courses;
        added.clear();
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    /**
     * This method handles the creation of the content for each row
     * @param position - The position in the list view we are at
     * @param convertView - The main view
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
        TextView poundSign = (TextView) rowView.findViewById(R.id.poundSign);
        Typeface retroFont = Typeface.createFromAsset(activity.getAssets(), "fonts/Market_Deco.ttf");
        Typeface vintage = Typeface.createFromAsset(activity.getAssets(), "fonts/octin vintage b rg.ttf");


        //Stops an error being thrown when we get to the bottom of the list view
        if(position > courses.size()-1){
            return rowView;
        }

        final Course course = courses.get(position);

        //Sets the rows colour (alternates between all the colours)
        int colour = (colours[position % colours.length]);
        rowView.findViewById(R.id.row).setBackgroundColor(colour);

        //If the colour is a dark one then we set the text to be white so it can be seen
        if(colour == (Colours.DARK_BLUE_SEARCH_PAGE.getColor())){
            universityname.setTextColor(Color.WHITE);
            courseName.setTextColor(Color.WHITE);
        }

        //Allows the table to be able to shrink depending on the resolution
        TableLayout tl = (TableLayout) rowView.findViewById(R.id.table);
        tl.setColumnShrinkable(1, true);


        //Sets the fonts of the content
        poundSign.setTypeface(vintage);
        mode.setTypeface(retroFont);
        averageSalaryAfter6.setTypeface(retroFont);
        percentThatGoOnToWork.setTypeface(retroFont);
        averageSatisfaction.setTypeface(retroFont);
        courseName.setTypeface(retroFont);
        universityname.setTypeface(vintage);



        //Sets the row text
        mode.setText("Study mode : " + course.getModeText());
        averageSalaryAfter6.setText("Average salary after 6 months : " + course.getAverageSalaryAfter6MonthsText());
        percentThatGoOnToWork.setText("Percent that go on to work or study : " + course.getPercentageTheWorkOrStudyText());
        averageSatisfaction.setText("Percentage satisfied with the course : " + course.getPercentageThatAreSatisfiedText());
        courseName.setText(course.getFullCourseName());
        universityname.setText(courses.get(position).getUniversityWhereCourseIsTaught());
        universityname.setAllCaps(true);


        //If the search course button is pressed then we move to the more stats page
        Button searchCourseButton = (Button) rowView.findViewById(R.id.testButton);
        searchCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rowView.getContext(), CourseStats.class);
               intent.putExtra("chosenCourse", (Parcelable) course);
               activity.startActivity(intent);
            }
        });

        return rowView;
    }


}
