package Utilities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chris.mystats_univeristy.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import Data.Course;

/**
 * Created by chris on 09/03/17.
 */

public class CourseListAdapter extends ArrayAdapter<Course>{

    private Activity activity;
    private ArrayList<Course> courses;
    private static LayoutInflater inflater = null;
    private static Set<String> added = new HashSet<>();

    public CourseListAdapter(Activity activity, int textViewResourceId , int header_id , ArrayList<Course> courses) {
        super(activity, textViewResourceId, header_id, courses);
        this.activity = activity;
        this.courses = courses;
        added.clear();
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public View getView(int position, View convertView, ViewGroup parent){
        View rowView = inflater.inflate(R.layout.search_row, parent , false);

        if(position > courses.size()-1){
            return rowView;
        }
        Course course = courses.get(position);

        TextView universityname = (TextView) rowView.findViewById(R.id.universityName);
        TextView courseName = (TextView) rowView.findViewById(R.id.courseName);
        courseName.setText(course.getFullCourseName());
        universityname.setText(courses.get(position).getUniversityWhereCourseIsTaught());
        universityname.setAllCaps(true);
        String all = course.getCourseName() + course.getCourseTypeText() + course.getUniversityWhereCourseIsTaught();

        return rowView;
    }


}
