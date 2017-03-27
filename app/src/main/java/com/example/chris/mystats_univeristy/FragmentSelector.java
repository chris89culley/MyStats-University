package com.example.chris.mystats_univeristy;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;

import org.w3c.dom.Text;

import Data.Course;
import MPChart.UniversityStatsChartMaker;
import Utilities.ExpandableSatisfactionAdapter;

/**
 * Created by c077ing on 08/03/2017.
 */

public class FragmentSelector extends Fragment {

    private int pos;
    private View view;
    private BarChart chart;
    private HorizontalBarChart hbchart;
    private PieChart pChart;
    private Course course;

    public FragmentSelector(int position, Course course) {
        this.pos = position;
        this.course = course;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Typeface retroFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Market_Deco.ttf");
        Typeface vintage = Typeface.createFromAsset(getActivity().getAssets(), "fonts/octin vintage b rg.ttf");

        switch(pos) {
            case 0:
                view = inflater.inflate(R.layout.fragment_cost_stats, container, false);
                try {
                    TextView text = (TextView) view.findViewById(R.id.textView10);
                    TextView text1 = (TextView) view.findViewById(R.id.textView11);
                    text.setTypeface(retroFont);
                    text1.setTypeface(retroFont);

                    int low, high;
                    String[] pte = course.getPrivateAccomodationDetails();
                    low = Integer.parseInt(pte[0]);
                    high = Integer.parseInt(pte[1]);
                    TextView x = (TextView) view.findViewById(R.id.costPvt);
                    x.setText("Private: £" + low + " - £" + high);
                    x.setTypeface(retroFont);


                    String[] inst = course.getInstitutionalAccomDetails();
                    low = Integer.parseInt(inst[0]);
                    high = Integer.parseInt(inst[1]);
                    x = (TextView) view.findViewById(R.id.costInst);
                    x.setText("Student Halls: £" + low + " - £" + high);
                    x.setTypeface(retroFont);
                }
                catch(Exception IO) {
                    view = inflater.inflate(R.layout.fragment_error, container, false);
                }
                return view;
            case 1:
                view =  inflater.inflate(R.layout.fragment_employ_stats, container, false);
                pChart = (PieChart) view.findViewById(R.id.espie1);
                pChart.setData(UniversityStatsChartMaker.getChartEploymentSixMonths(course, pChart));
                pChart.animateXY(2000,2000);
                chart = (BarChart) view.findViewById(R.id.esbar1);
                chart.setData(UniversityStatsChartMaker.getAvgSalaryFourtyMonths(course, chart));
                chart.animateY(2000);
                chart = (BarChart) view.findViewById(R.id.esbar2);
                chart.setData(UniversityStatsChartMaker.getChartAvgSalarySixMonths(course, chart));
                chart.animateY(2000);
                return  view;
            case 2:
                view =  inflater.inflate(R.layout.satisfaction_content, container, false);
                ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView1);
                ExpandableSatisfactionAdapter x = new ExpandableSatisfactionAdapter(getContext(), course);
                expandableListView.setAdapter(x);

                return  view;
            case 3:
                return inflater.inflate(R.layout.fragment_study_info, container, false);
            case 4:
                View view = inflater.inflate(R.layout.fragment_entry_info, container, false);
                chart = (BarChart) view.findViewById(R.id.eibar1);
                chart.setData(UniversityStatsChartMaker.getChartPreviousEntries(course, chart));
                chart.animateY(2000);
                return view;
            case 5:
                return inflater.inflate(R.layout.fragment_user_rating, container, false);

            default:
                return inflater.inflate(R.layout.fragment_error, container, false);
        }
    }
}
