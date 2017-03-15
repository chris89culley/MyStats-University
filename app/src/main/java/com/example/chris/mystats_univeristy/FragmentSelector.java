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
                view =  inflater.inflate(R.layout.fragment_study_info, container, false);
                chart = (BarChart) view.findViewById(R.id.bar1);
                chart.setData(UniversityStatsChartMaker.getChartTeachingOnMyCourse(course, chart));
                chart.animate();
//                if(chart == null){
//                    return inflater.inflate(R.layout.fragment_error, container, false);
//                }
                return  view;
            case 2:
                view =  inflater.inflate(R.layout.fragment_entry_info, container, false);
                chart = (BarChart) view.findViewById(R.id.bar2);
                chart.setData(UniversityStatsChartMaker.getChartOrganisationAndManagement(course, chart));
                chart.animate();
//                if(chart == null){
//                    return inflater.inflate(R.layout.fragment_error, container, false);
//                }
                return view;
            case 3:

                view =  inflater.inflate(R.layout.fragment_cost_stats, container, false);
                pChart = (PieChart) view.findViewById(R.id.cspie1);
                pChart.setData(UniversityStatsChartMaker.getChartEploymentSixMonths(course, pChart));
                pChart.animateXY(2000,2000);
                chart = (BarChart) view.findViewById(R.id.csbar1);
                chart.setData(UniversityStatsChartMaker.getChartPrivateAccomodation(course, chart));
                chart.animateXY(2000,2000);
                chart = (BarChart) view.findViewById(R.id.csbar2);
                chart.setData(UniversityStatsChartMaker.getChartInstitutionalAccomodation(course, chart));
                chart.animateXY(2000,2000);
                return  view;
            case 4:
                view =  inflater.inflate(R.layout.fragment_employ_stats, container, false);
                pChart = (PieChart) view.findViewById(R.id.espie1);
                pChart.setData(UniversityStatsChartMaker.getChartEploymentSixMonths(course, pChart));
                pChart.animateXY(2000,2000);
                chart = (BarChart) view.findViewById(R.id.esbar1);
                chart.setData(UniversityStatsChartMaker.getAvgSalaryFourtyMonths(course, chart));
                chart.animate();
//                if(chart == null){
//                    return inflater.inflate(R.layout.fragment_error, container, false);
//                }
                return  view;
            case 5:
                view =  inflater.inflate(R.layout.fragment_satisfaction_stats, container, false);
                chart = (BarChart) view.findViewById(R.id.bar5);
                chart.setData(UniversityStatsChartMaker.getChartPersonalDevelopment(course, chart));
                chart.animate();
//                if(chart == null){
//                    return inflater.inflate(R.layout.fragment_error, container, false);
//                }
                return  view;
            
            case 6:
                view =  inflater.inflate(R.layout.fragment_assessed_coursework, container, false);
                pChart = (PieChart) view.findViewById(R.id.bar6);
                pChart.setData(UniversityStatsChartMaker.getChartPercentageAssesedByCourseWork(course, pChart));
                pChart.animate();
//                if(pChart == null){
//                    return inflater.inflate(R.layout.fragment_error, container, false);
//                }
                return  view;
            case 7:
                view =  inflater.inflate(R.layout.fragment_assessment_feedback, container, false);
                chart = (BarChart) view.findViewById(R.id.bar7);
                chart.setData(UniversityStatsChartMaker.getChartAssesmentAndFeedback(course, chart));
                chart.animate();
//                if(chart == null){
//                    return inflater.inflate(R.layout.fragment_error, container, false);
//                }
                return  view;
            case 8:
                view =  inflater.inflate(R.layout.fragment_acdm_support, container, false);
                chart = (BarChart) view.findViewById(R.id.bar8);
                chart.setData(UniversityStatsChartMaker.getChartAccademicSupport(course, chart));
                chart.animate();
//                if(chart == null){
//                    return inflater.inflate(R.layout.fragment_error, container, false);
//                }
                return  view;
            case 9:
                view =  inflater.inflate(R.layout.fragment_learning_res, container, false);
                chart = (BarChart) view.findViewById(R.id.bar9);
                chart.setData(UniversityStatsChartMaker.getChartLearningResources(course, chart));
                chart.animate();
//                if(chart == null){
//                    return inflater.inflate(R.layout.fragment_error, container, false);
//                }
                return  view;
            case 10:
                view =  inflater.inflate(R.layout.fragment_scheduled, container, false);
                chart = (BarChart) view.findViewById(R.id.bar10);
                chart.setData(UniversityStatsChartMaker.getChartPercentageInScheduled(course, chart));
                chart.animate();
//                if(chart == null){
//                    return inflater.inflate(R.layout.fragment_error, container, false);
//                }
                return  view;
            case 11:
                view =  inflater.inflate(R.layout.fragment_su, container, false);
                chart = (BarChart) view.findViewById(R.id.bar11);
                chart.setData(UniversityStatsChartMaker.getChartStudentUnion(course, chart));
                chart.animate();
//                if(chart == null){
//                    return inflater.inflate(R.layout.fragment_error, container, false);
//                }
                return  view;

            case 12:
                view =  inflater.inflate(R.layout.fragment_work_study, container, false);
                pChart = (PieChart) view.findViewById(R.id.bar12);
                pChart.setData(UniversityStatsChartMaker.getChartPercentageWorkAndStudy(course, pChart));
                pChart.animate();
                if(pChart == null){
                    return inflater.inflate(R.layout.fragment_error, container, false);
                }
                return  view;
            case 13:
                view =  inflater.inflate(R.layout.fragment_personal_dev, container, false);
                chart = (BarChart) view.findViewById(R.id.bar13);
                chart.setData(UniversityStatsChartMaker.getChartPersonalDevelopment(course, chart));
                chart.animate();
//                if(chart == null){
//                    return inflater.inflate(R.layout.fragment_error, container, false);
//                }
                return  view;
            case 14:
                return inflater.inflate(R.layout.fragment_user_rating, container, false);
            default:
                return inflater.inflate(R.layout.fragment_error, container, false);
        }
    }
}
