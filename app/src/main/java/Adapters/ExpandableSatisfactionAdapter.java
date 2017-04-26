package Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chris.mystats_univeristy.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Data.ChartStats;
import Data.Course;
import MPChart.UniversityStatsChartMaker;
import Utilities.Colours;

/**
 * Created by c077ing on 27/03/2017.
 */

// current adapter class for the satisfaction data, this class gets each element within the charts
// and then display it within a expandable list
public class ExpandableSatisfactionAdapter extends BaseExpandableListAdapter {

    // the titles of each expandable row
    private String[] groupItems = {"Teaching on the Course", "Assessment and Feedback",
            "Academic Support", "Organisation and Management",
            "Learning Resources", "Personal Development", "StudentUnion"};

    private Context context;
    private Course course;
    private Typeface retroFont;
    private Typeface vintageFont;
    private float graphTextSize = 1.0f;
    private static LayoutInflater inflater = null;
    private int i = 0;

    /**
     * This method uses the course and context to set up a header content expandable view
     * @param context
     * @param course
     */
    public ExpandableSatisfactionAdapter(Context context, Course course, Activity activity, Float graphTextSize) {
        retroFont = Typeface.createFromAsset(context.getAssets(), "fonts/Josefin_Sans/JosefinSans-SemiBold.ttf");
        vintageFont = Typeface.createFromAsset(context.getAssets(), "fonts/octin vintage b rg.ttf");
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.course = course;
        this.graphTextSize = graphTextSize;

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
        return 1;
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
        return null;
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
     * This method sets the content for the group view ie the header
     * @param groupPosition - The index of the group view
     * @param isExpanded - Whether it has been expanded or not
     * @param convertView - The view it converts
     * @param parent - The viewgroups parent
     * @return - A view with the updated content
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final View rowView = inflater.inflate(R.layout.satisfaction_row, parent , false);

        TextView title = (TextView) rowView.findViewById(R.id.satisfactionTitle);


        //Sets up alternating colours for the header
        TextView sectionName = (TextView) rowView.findViewById(R.id.satisfactionName);
        if(groupPosition %2 == 0){
            sectionName.setBackgroundColor(Colours.GREEN_SHEEN.getColor());
        }
        else{
            sectionName.setBackgroundColor(Colours.BACKGROUND_GREEN.getColor());
        }

        sectionName.setText(groupItems[groupPosition]);
        sectionName.setTypeface(retroFont);

        return rowView;
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
        View childView = inflater.inflate(R.layout.satisfaction_content, parent, false);

        BarChart chart = (BarChart) childView.findViewById(R.id.changeableBarChartOnSatisfactionDropDowns);
        chart.setData(getTheRightDataForTheGraph(groupPosition,chart));
        chart.animateY(2000);
        chart.getXAxis().setTypeface(retroFont);
        chart.getXAxis().setTextSize(graphTextSize);

        return childView;
    }

    /**
     * This method uses the position id to determine which of the charts should be displayed to the user
     * @param id - The position id
     * @param chart - The chart for which the data will be entered into
     * @return - Bar data that will fill up the chart
     */
    public BarData getTheRightDataForTheGraph(int id, BarChart chart){

        switch (id){

            case 0 :
                return UniversityStatsChartMaker.getChartTeachingOnMyCourse(course, chart);
            case 1:
                return UniversityStatsChartMaker.getChartAssesmentAndFeedback(course,chart);
            case 2:
                return UniversityStatsChartMaker.getChartAccademicSupport(course,chart);
            case 3:
                return UniversityStatsChartMaker.getChartOrganisationAndManagement(course,chart);
            case 4:
                return UniversityStatsChartMaker.getChartLearningResources(course,chart);
            case 5:
                return UniversityStatsChartMaker.getChartPersonalDevelopment(course,chart);
            case 6:
                return UniversityStatsChartMaker.getChartStudentUnion(course,chart);
            default:
                return null;
        }


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
