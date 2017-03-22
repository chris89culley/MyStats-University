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
    private static String[] colours = {"#8DA6B3", "#5E8091" , "#254F63" };

    public CourseListAdapter(Activity activity, int textViewResourceId , int header_id , ArrayList<Course> courses) {
        super(activity, textViewResourceId, header_id, courses);
        this.activity = activity;
        this.courses = courses;
        added.clear();
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public View getView(int position, final View convertView, ViewGroup parent){
        final View rowView = inflater.inflate(R.layout.search_row, parent , false);

        if(position > courses.size()-1){
            return rowView;
        }
        final Course course = courses.get(position);

        String colour = (colours[position % colours.length]);

        rowView.findViewById(R.id.row).setBackgroundColor(Color.parseColor(colour));

        TextView universityname = (TextView) rowView.findViewById(R.id.universityName);
        TextView courseName = (TextView) rowView.findViewById(R.id.courseName);
        TextView mode = (TextView) rowView.findViewById(R.id.mode);
        TextView averageSalaryAfter6 = (TextView) rowView.findViewById(R.id.salaryAfter6);
        TextView percentThatGoOnToWork = (TextView) rowView.findViewById(R.id.percentThatGoToWork);
        TextView averageSatisfaction = (TextView) rowView.findViewById(R.id.overallSatisafaction);
        TextView poundSign = (TextView) rowView.findViewById(R.id.poundSign);

        if(colour.equals("#254F63")){
            universityname.setTextColor(Color.WHITE);
            courseName.setTextColor(Color.WHITE);
        }

        TableLayout tl = (TableLayout) rowView.findViewById(R.id.table);
        tl.setColumnShrinkable(1, true);
        Typeface retroFont = Typeface.createFromAsset(activity.getAssets(), "fonts/Market_Deco.ttf");




        Typeface vintage = Typeface.createFromAsset(activity.getAssets(), "fonts/octin vintage b rg.ttf");

        poundSign.setTypeface(vintage);
        mode.setTypeface(retroFont);
        averageSalaryAfter6.setTypeface(retroFont);
        percentThatGoOnToWork.setTypeface(retroFont);
        averageSatisfaction.setTypeface(retroFont);

        courseName.setTypeface(retroFont);
        universityname.setTypeface(vintage);
        mode.setText("Study mode : " + course.getModeText());
        averageSalaryAfter6.setText("Average salary after 6 months : " + course.getAverageSalaryAfter6MonthsText());
        percentThatGoOnToWork.setText("Percent that go on to work or study : " + course.getPercentageTheWorkOrStudyText());
        averageSatisfaction.setText("Percentage satisfied with the course : " + course.getPercentageThatAreSatisfiedText());
        courseName.setText(course.getFullCourseName());
        universityname.setText(courses.get(position).getUniversityWhereCourseIsTaught());
        universityname.setAllCaps(true);

        Button searchCourseButton = (Button) rowView.findViewById(R.id.testButton);
        searchCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rowView.getContext(), CourseStats.class);
               intent.putExtra("chosenCourse", (Parcelable) course);
               activity.startActivity(intent);
            }
        });
        String all = course.getCourseName() + course.getCourseTypeText() + course.getUniversityWhereCourseIsTaught();

        return rowView;
    }


}
