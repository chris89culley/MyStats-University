package Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chris.mystats_univeristy.R;

import Data.Course;

/**
 * Created by c077ing on 27/03/2017.
 */

// current adapter class for the satisfaction data, this class gets each element within the charts
// and then display it within a expandable list
public class ExpandableSatisfactionAdapter extends BaseExpandableListAdapter {

    // the titles of each expandable row
    private String[] groupItems = {"Teaching on the Course", "Assesment and Feedback",
            "Accademic Support", "Organisation and Management",
            "Learning Resources", "Personal Development", "StudentUnion"};

    String[][] items;
    Context context;
    Course course;
    Typeface retroFont;
    Typeface vintageFont;
    static int i = 0;

    public ExpandableSatisfactionAdapter(Context context, Course course) {
        retroFont = Typeface.createFromAsset(context.getAssets(), "fonts/Market_Deco.ttf");
        vintageFont = Typeface.createFromAsset(context.getAssets(), "fonts/octin vintage b rg.ttf");

        this.context = context;
        this.course = course;

        // all data that would be within a chart are stored within a string list
        // which is later used to separate the chart elements for each expandable row
        String[] stat1 = course.getTeachingOnMyCourseStats();
        String[] stat2 = course.getAssesmentAndFeedbackStats();
        String[] stat3 = course.getAccademicSupportStats();
        String[] stat4 = course.getOrganisationAndManagementStats();
        String[] stat5 = course.getLearningResourcesStats();
        String[] stat6 = course.getPersonalDevelopmentStats();
        String[] stat7 = course.getStudentUnionStats();

        // two dimensional array used to set the data within each of the expandable rows
        String[][] items1 = {
                {"The course is intellectually stimulating: " + stat1[0] + "%", "Staff are ethusiastic about what they're teaching: " + stat1[1] + "%",
                        "Staff made the subject interesting: " + stat1[2] + "%", "Staff are good at explaining things: " + stat1[3] + "%"},
                {"Feedback on my work helped me clarify things I did not understand: " + stat2[0] + "%", "I have received detail comments on my work: " + stat2[1] + "%",
                        "Feedback on my work has been prompt: " + stat2[2] + "%", "Assessment arrangements and marking have been fair: " + stat2[3] + "%", "The criteria used in marking have been clear in advance: " + stat2[4] + "%"},
                {"Good advice was available when I needed to make study choices: " + stat3[0] + "%", "I have been able to contact staff when I needed to: " + stat3[1] + "%",
                        "I have received sufficient advice and support with my studies: " + stat3[2] + "%"},
                {"The course is well organised and is running smoothly: " + stat4[0] + "%",
                        "Any changes in the course or teaching have been communicated effectively: " + stat4[1] + "%",
                        "The timetable works efficiently as far as my activities are concerned: " + stat4[2] + "%"},
                {"I have been able to access specialised equipment, facilities, or rooms when I needed to: " + stat5[0] + "%",
                        "I have been able to access general IT resources when I needed to: " + stat5[1] + "%",
                        "The library resources and services are good enough for my needs: " + stat5[2] + "%"},
                {"As a result of the course, I feel confident in tackling unfamiliar problems: " + stat6[0] + "%", "My communication skills have improved: " + stat6[1] + "%",
                        "The course has helped me to present myself with confidence: " + stat6[2] + "%"},
                {"I am satisfied with the Student' Union at my institution: " + stat7[0] + "%"}};

        items = items1;
    }

    @Override
    public int getGroupCount() {
        return groupItems.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return items[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupItems[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return items[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    // expandable row data being set
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(groupItems[groupPosition]);
        textView.setTypeface(retroFont);
        textView.setPadding(100, 15, 15, 15);
        textView.setTextSize(30);
        return textView;
    }

    // data within the expandable row being set with alternate colours
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final TextView textView = new TextView(context);
        textView.setText(items[groupPosition][childPosition]);
        textView.setTypeface(vintageFont);
        textView.setPadding(100, 15, 15, 15);
        textView.setTextSize(20);
        if(i == 0) {
            textView.setBackgroundColor(Color.parseColor("#43ABC9"));
            i++;
        }
        else{
            i--;
            textView.setBackgroundColor(Color.parseColor("#1496BB"));
        }
        return textView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
