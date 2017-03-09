package Utilities;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chris.mystats_univeristy.R;

import java.util.ArrayList;
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

    public CourseListAdapter(Activity activity, int textViewResourceId , ArrayList<Course> courses) {
        super(activity, textViewResourceId, courses);
        this.activity = activity;
        this.courses = courses;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public View getView(int position, View convertView, ViewGroup parent){
        View rowView = inflater.inflate(R.layout.activity_search_results , parent ,false);
        TextView universityname = (TextView) rowView.findViewById(R.id.universityname);
        TextView courseName = (TextView) rowView.findViewById(R.id.coursename);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        universityname.setText(courses.get(position).getUniversityWhereCourseIsTaught());
        universityname.setAllCaps(true);
        courseName.setText(courses.get(position).getCourseName());
        return rowView;
    }


}
