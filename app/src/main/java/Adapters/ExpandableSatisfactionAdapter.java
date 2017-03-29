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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Data.ChartStats;
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

    private Context context;
    private Course course;
    private Typeface retroFont;
    private Typeface vintageFont;
    private int i = 0;
    private ArrayList<ChartStats> satisfactionStats = new ArrayList<>();

    /**
     * This method uses the course and context to set up a header content expandable view
     * @param context
     * @param course
     */
    public ExpandableSatisfactionAdapter(Context context, Course course) {
        retroFont = Typeface.createFromAsset(context.getAssets(), "fonts/Market_Deco.ttf");
        vintageFont = Typeface.createFromAsset(context.getAssets(), "fonts/octin vintage b rg.ttf");

        this.context = context;
        this.course = course;

        //Adds all the stats graphs to a list which we can then extract the data from
        Collections.addAll(satisfactionStats,course.getTeachingOnMyCourseStats() ,
                course.getAssesmentAndFeedbackStats(),
                course.getAccademicSupportStats(),
                course.getOrganisationAndManagementStats(),
                course.getLearningResourcesStats(),
                course.getPersonalDevelopmentStats(),
                course.getStudentUnionStats() );

    }

    /**
     * Gets the number of headers
     * @return - The number of headers
     */
    @Override
    public int getGroupCount() {
        return groupItems.length;
    }

    /**
     * Gets the number of entries at the passed header index
     * @param groupPosition - The header index
     * @return - Number of entries at the header index
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return satisfactionStats.get(groupPosition).getData().length;
    }

    /**
     * This method gets all of the data at the header index
     * @param groupPosition - The header index
     * @return - The data held in the header index
     */
    @Override
    public Object getGroup(int groupPosition) {
        return groupItems[groupPosition];
    }

    /**
     * Given the header index and content index this method retrieves the relevant data
     * @param groupPosition  - The header index
     * @param childPosition - The content index
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return satisfactionStats.get(groupPosition).getData()[childPosition];
    }

    /**
     * Gets the group id (which is passed in )
     * @param groupPosition - Header index
     * @return - The header index
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * Gets the child id from the passed in group and child position
     * @param groupPosition - The header index
     * @param childPosition - The content index
     * @return - The child position
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * Always returns false
     * @return - false
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * This methhod sets the content for the group view ie the header
     * @param groupPosition - The index of the group view
     * @param isExpanded - Whether it has been expanded or not
     * @param convertView - The view it converts
     * @param parent - The viewgroups parent
     * @return - A view with the updated content
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(groupItems[groupPosition]);
        textView.setTypeface(retroFont);
        textView.setPadding(100, 15, 15, 15);
        textView.setTextSize(30);
        return textView;
    }


    /**
     * This method gets the child view based on the group position and the child positon which
     * points to a particular stat area and then the specific stat
     * @param groupPosition - The type of stat
     * @param childPosition - The specific stat index
     * @param isLastChild - True if we are at the last index
     * @param convertView - The view we are converting
     * @param parent - The parent view group
     * @return - A view containing the content specified
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final TextView textView = new TextView(context);
        textView.setText(satisfactionStats.get(groupPosition).getTags()[childPosition] + ", " + satisfactionStats.get(groupPosition).getData()[childPosition] + "%");
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

    /**
     * Returns if the child is able to be selected
     * @param groupPosition - The header position
     * @param childPosition - The index of the content within the child position
     * @return
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
