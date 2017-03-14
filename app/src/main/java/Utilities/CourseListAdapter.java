package Utilities;

import android.app.Activity;
import android.content.Context;
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

    public CourseListAdapter(Activity activity, int textViewResourceId , ArrayList<Course> courses) {
        super(activity, textViewResourceId, courses);
        this.activity = activity;
        this.courses = courses;
        added.clear();
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public View getView(int position, View convertView, ViewGroup parent){
        View rowView = inflater.inflate(R.layout.activity_search_results , parent ,false);

        if(position > courses.size()-1){
            return rowView;
        }
        Course course = courses.get(position);

        TextView universityname = (TextView) rowView.findViewById(R.id.universityname);
        TextView courseName = (TextView) rowView.findViewById(R.id.coursename);
        TextView courseType = (TextView) rowView.findViewById(R.id.degreetype);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        universityname.setText(courses.get(position).getUniversityWhereCourseIsTaught());
        universityname.setAllCaps(true);
        courseName.setText(courses.get(position).getCourseName());
        courseType.setText(course.getCourseTypeText());
        String all = course.getCourseName() + course.getCourseTypeText() + course.getUniversityWhereCourseIsTaught();

        /**
        //This is because the database contains duplicates and is being used as a quick fix
        if(added.contains(all)){
            if(position-1 == courses.size()){
                return  rowView;
            }
            return getView(position+1, convertView, parent);
        }
        added.add(all); */

        return rowView;
    }


}
