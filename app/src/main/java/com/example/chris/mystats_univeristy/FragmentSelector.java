package com.example.chris.mystats_univeristy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;

import Data.Course;
import MPChart.UniversityStatsChartMaker;

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
        switch(pos) {
            case 0:
                view =  inflater.inflate(R.layout.fragment_overview, container, false);
                TextView txt = (TextView) view.findViewById(R.id.cName);
                TextView txt1 = (TextView) view.findViewById(R.id.cType);
                TextView txt2 = (TextView) view.findViewById(R.id.hasFoundation);
                TextView txt3 = (TextView) view.findViewById(R.id.hasPlacement);

                txt.setText(course.getCourseName());
                txt1.setText(course.getCourseTypeText());
                if(course.hasFoundationYear() || course.hasSandwichYear() == false){
                    txt2.append("No");
                }
                else{
                    txt2.append("Yes");
                }
                if(course.hasPlacementYear() || course.hasSandwichYear() == false){
                    txt3.append("No");
                }
                else{
                    txt3.append("Yes");
                }
                pChart = (PieChart) view.findViewById(R.id.fopie1);
                pChart.setData(UniversityStatsChartMaker.getChartPercentageAssesedByCourseWork(course, pChart));
                pChart.animateXY(2000,2000);
                pChart = (PieChart) view.findViewById(R.id.fopie2);
                pChart.setData(UniversityStatsChartMaker.getChartPercentageWorkAndStudy(course, pChart));
                pChart.animateXY(2000,2000);
                return  view;
            case 1:
                view =  inflater.inflate(R.layout.fragment_cost_stats, container, false);
                chart = (BarChart) view.findViewById(R.id.csbar1);
                chart.setData(UniversityStatsChartMaker.getChartPrivateAccomodation(course, chart));
                chart.animateXY(2000,2000);
                chart = (BarChart) view.findViewById(R.id.csbar2);
                chart.setData(UniversityStatsChartMaker.getChartInstitutionalAccomodation(course, chart));
                chart.animateXY(2000,2000);
                return  view;
            case 2:
                view =  inflater.inflate(R.layout.fragment_employ_stats, container, false);
                TextView chartTitle1 = (TextView) view.findViewById(R.id.chartTitle1);
                chartTitle1 .setText("Employment six months after completeing the course");
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
            case 3:
                view =  inflater.inflate(R.layout.fragment_satisfaction_stats, container, false);
                chart = (BarChart) view.findViewById(R.id.ssbar1);
                chart.setData(UniversityStatsChartMaker.getChartTeachingOnMyCourse(course, chart));
                chart.animateY(2000);
                chart = (BarChart) view.findViewById(R.id.ssbar2);
                chart.setData(UniversityStatsChartMaker.getChartAssesmentAndFeedback(course, chart));
                chart.animateY(2000);
                chart = (BarChart) view.findViewById(R.id.ssbar3);
                chart.setData(UniversityStatsChartMaker.getChartAccademicSupport(course, chart));
                chart.animateY(2000);
                chart = (BarChart) view.findViewById(R.id.ssbar4);
                chart.setData(UniversityStatsChartMaker.getChartOrganisationAndManagement(course, chart));
                chart.animateY(2000);
                chart = (BarChart) view.findViewById(R.id.ssbar5);
                chart.setData(UniversityStatsChartMaker.getChartLearningResources(course, chart));
                chart.animateY(2000);
                chart = (BarChart) view.findViewById(R.id.ssbar6);
                chart.setData(UniversityStatsChartMaker.getChartPersonalDevelopment(course, chart));
                chart.animateY(2000);
                chart = (BarChart) view.findViewById(R.id.ssbar7);
                chart.setData(UniversityStatsChartMaker.getChartStudentUnion(course, chart));
                chart.animateY(2000);
                return  view;
            case 4:
                return inflater.inflate(R.layout.fragment_study_info, container, false);
            case 5:
                View view = inflater.inflate(R.layout.fragment_entry_info, container, false);
                chart = (BarChart) view.findViewById(R.id.eibar1);
                chart.setData(UniversityStatsChartMaker.getChartPreviousEntries(course, chart));
                chart.animateY(2000);
                return view;
            case 6:
                return inflater.inflate(R.layout.fragment_user_rating, container, false);

            default:
                return inflater.inflate(R.layout.fragment_error, container, false);
        }
    }
}
