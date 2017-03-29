package com.example.chris.mystats_univeristy;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import Data.Course;
import MPChart.UniversityStatsChartMaker;
import Adapters.ExpandableSatisfactionAdapter;

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
    private LineChart lineChart;

    public FragmentSelector(int position, Course course) {
        this.pos = position;
        this.course = course;
    }

    // fragment selector using a switch statement to determine which fragment to inflate
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Typeface retroFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Market_Deco.ttf");
        Typeface vintage = Typeface.createFromAsset(getActivity().getAssets(), "fonts/octin vintage b rg.ttf");
        try {
        switch(pos) {
            case 0:
                view = inflater.inflate(R.layout.fragment_cost_stats, container, false);createCostStatsPage(view,retroFont);
                return view;
            case 1:
                view =  inflater.inflate(R.layout.fragment_employ_stats, container, false);
                createEmploymentStatsPage(view, retroFont);
                return view;
            case 2:
                view =  inflater.inflate(R.layout.satisfaction_content, container, false);
                ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView1);
                ExpandableSatisfactionAdapter adapter = new ExpandableSatisfactionAdapter(getContext(), course);
                expandableListView.setAdapter(adapter);
                return view;

            case 3:
                view = inflater.inflate(R.layout.fragment_study_info, container, false);
                createStudyInfo(view,retroFont);
                return view;
            case 4:
                //Entry_information fragment
                    try {
                        //set the view as the fragment_Entry_info view
                        view = inflater.inflate(R.layout.fragment_entry_info, container, false);
                        lineChart = (LineChart) view.findViewById(R.id.linechart);

                        //Set the description
                        TextView entryChartTitle = (TextView) view.findViewById(R.id.esChartTitle1);
                        entryChartTitle.setText("Everyone needs to know how many UCAS points they need to get into their favourite Uni, Below you will see a chart that shows the spread of what last years students had when they started this course");
                        entryChartTitle.setTypeface(retroFont);

                        //Sets the Y Axis title
                        TextView yAxislabel = (TextView) view.findViewById(R.id.esYAxis);
                        yAxislabel.setText("Percentage of people");
                        yAxislabel.setTypeface(retroFont);

                        //Sets the X Axis title
                        TextView xAxislabel = (TextView) view.findViewById(R.id.esXAxis);
                        xAxislabel.setText("Amount of UCAS Points");
                        xAxislabel.setTypeface(retroFont);

                        lineChart.setData(UniversityStatsChartMaker.getChartPreviousEntries(course, lineChart));
                    }catch (Exception e){
                        return view = inflater.inflate(R.layout.fragment_error, container, false);
                    }

                return view;
            case 5:
                view = inflater.inflate(R.layout.fragment_user_rating, container, false);
                createUserRating(view);
                return view;

            default:
                return inflater.inflate(R.layout.fragment_error, container, false);
        }}
        catch(Exception IO) {
            return view = inflater.inflate(R.layout.fragment_error, container, false);
        }
    }


    /**
     * used to create the costs stats fragments
     * @param v View
     * @param font The font being used
     * @return
     */

    private View createCostStatsPage(View v,Typeface font) {

            TextView text = (TextView) view.findViewById(R.id.costStatInfo1);
            TextView text2 = (TextView) view.findViewById(R.id.costStatInfo2);
            text.setTypeface(font);
            text2.setTypeface(font);

            int low, high;
            String[] pte = course.getPrivateAccomodationDetails().getData();
            low = Integer.parseInt(pte[0]);
            high = Integer.parseInt(pte[1]);
            TextView x = (TextView) view.findViewById(R.id.costPvt);
            x.setText("Private: £" + low + " - £" + high);
            x.setTypeface(font);


            String[] inst = course.getInstitutionalAccomDetails().getData();
            low = Integer.parseInt(inst[0]);
            high = Integer.parseInt(inst[1]);
            x = (TextView) view.findViewById(R.id.costInst);
            x.setText("Student Halls: £" + low + " - £" + high);
            x.setTypeface(font);

        return v;
    }

    /**
     * used to create the employment stats fragments
     * @param v View
     * @param font The font being used
     * @return
     */
    private View createEmploymentStatsPage(View v,Typeface font){

        TextView pageOverview = (TextView) view.findViewById(R.id.esPageOverView);
        TextView chartTitle1 = (TextView) view.findViewById(R.id.esChartTitle1);
        pageOverview.setTypeface(font);
        chartTitle1.setTypeface(font);

        pChart = (PieChart) view.findViewById(R.id.espie1);
        pChart.setData(UniversityStatsChartMaker.getChartEploymentSixMonths(course, pChart));
        pChart.getLegend().setTypeface(font);
        pChart.getLegend().setTextColor(ColorTemplate.rgb("#3C6478"));
        pChart.animateXY(2000,2000);

        final ImageView salarySixmonthSymbol = (ImageView) view.findViewById(R.id.esStatIcon1);
        final TextView salarySixmonthText = (TextView) view.findViewById(R.id.animText1);
        final ImageView salaryFourtymonthSymbol = (ImageView) view.findViewById(R.id.esStatIcon2);
        final TextView salaryFourtymonthText = (TextView) view.findViewById(R.id.animText2);
        salaryFourtymonthText.setTypeface(font);
        salarySixmonthText.setTypeface(font);

        salaryFourtymonthText.setTextSize(13f);
        salaryFourtymonthText.setY(salaryFourtymonthText.getY() + 10f);
        salaryFourtymonthText.setText("The average salary 40 months after was \n" + course.getAverageSalaryAfter40MonthsText().trim());

        salarySixmonthText.setTextSize(13f);
        salarySixmonthText.setY(salarySixmonthText.getY() + 10f);
        salarySixmonthText.setText("The average salary 6 months after was \n " + course.getAverageSalaryAfter6MonthsText().trim());

        final Animation animright1 = AnimationUtils.loadAnimation(this.getContext(),R.anim.slide_right);
        final Animation animright2 = AnimationUtils.loadAnimation(this.getContext(),R.anim.slide_right);
        double salary = 0;
        try{
            Log.d("Salary", course.getAverageSalaryAfter6MonthsText().substring(1));
            salary = Double.parseDouble(course.getAverageSalaryAfter6MonthsText().substring(1));
            Log.d("", Double.toString(salary));
        }catch (Exception e){
            Log.d("Exception", e.toString());

            e.printStackTrace();
        }
        TextView monthlyWage = (TextView) view.findViewById(R.id.esWageAfterRepayments);
        if (salary != 0 ){
          int repayment = 0;
        if (salary > 174950 && salary <= 18500 ){
           repayment = 7;
        } else if(salary > 18500 && salary <= 21000 ){
        repayment = 26;
        } else if(salary > 21000 && salary <= 24000 ){
          repayment = 48;
        } else if(salary > 24000 && salary <= 27000) {
        repayment = 71;

        } else if(salary > 27000 && salary <= 30000 ) {
          repayment = 71;
        } else if(salary > 30000){
          repayment = 93;
        }
        salary = salary * 0.8;
        salary = salary / 12;

        salary -= repayment;
        salary = Math.round(salary);

        monthlyWage.setTypeface(font);
        monthlyWage.setText("Your Monthly wage will be £" + salary +"\nAfter paying 20% tax \nand a student loan repayment of £"+repayment);
        }

        /*salaryFourtymonthSymbol.setOnClickListener(new View.OnClickListener() {
            Boolean animFlag = true;
            @Override
            public void onClick(View v) {
                if (animFlag == true ) {
                    salaryFourtymonthText.setTextSize(13f);
                    salaryFourtymonthText.setY(salaryFourtymonthText.getY() + 10f);
                    salaryFourtymonthText.setText("The average salary 40 months after was \n" + course.getAverageSalaryAfter40MonthsText().trim());
                    salaryFourtymonthText.startAnimation(animright1);
                    animFlag = false;
                }
            }
        });
        salarySixmonthSymbol.setOnClickListener(new View.OnClickListener() {
            Boolean animFlag = true;
            @Override
            public void onClick(View v) {
                if (animFlag == true ) {
                    salarySixmonthText.setTextSize(13f);
                    salarySixmonthText.setY(salarySixmonthText.getY() + 10f);
                    salarySixmonthText.setText("The average salary 6 months after was \n " + course.getAverageSalaryAfter6MonthsText().trim());
                    salarySixmonthText.startAnimation(animright2);
                    animFlag = false;
                }
            }
        });*/
       return v ;
    }


    /**
     * used to create the satisfaction stats fragments
     * @param v View
     * @return
     */
    private View createSatisfactionStats(View v){
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
        return v;
    }


    /**
     * used to create the study info fragments
     * @param v View
     * @return
     */
    private View createStudyInfo(View v,Typeface font){
        TextView title = (TextView) view.findViewById(R.id.siPageOverView);
        title.setTypeface(font);

        TextView chartTitle1 = (TextView) view.findViewById(R.id.siChartTitle1);
        chartTitle1.setTypeface(font);

        TextView chartTitle2 = (TextView) view.findViewById(R.id.siChartTitle2);
        chartTitle2.setTypeface(font);

        String[] vals = course.getPercentageInScheduled().getData();

        TextView stat1 = (TextView) view.findViewById(R.id.sistat1);
        stat1.setTypeface(font);
        stat1.setText(vals[0]+"% "+"Of time spent in supervised learning (lectures and seminars)");

        vals = course.getPercentageAssesedByCourseWork().getData();

        TextView stat2 = (TextView) view.findViewById(R.id.sistat2);
        stat2.setTypeface(font);
        stat2.setText(vals[0]+"% "+"Of the course is assessed by coursework");



        pChart =  (PieChart) view.findViewById(R.id.sipie1);
        pChart.setData(UniversityStatsChartMaker.getChartDegreeClass(course, pChart));
        pChart.getLegend().setTypeface(font);
        pChart.getLegend().setTextColor(ColorTemplate.rgb("#3C6478"));
        pChart.animateXY(2000,2000);



        pChart  =  (PieChart) view.findViewById(R.id.sipie2);
        pChart.setData(UniversityStatsChartMaker.getChartContinuationStats(course, pChart));
        pChart.getLegend().setTypeface(font);
        pChart.getLegend().setTextColor(ColorTemplate.rgb("#3C6478"));
        pChart.animateXY(2000,2000);


        //view.setOnScrollChangeListener(new View.OnScrollChangeListener() {
          //  boolean inview1,inview2;
           // @Override
            //public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
              //  Log.d("Scroll y pos",Integer.toString(scrollY));
                //if (scrollY > 100 && scrollY < 1400 && inview1 == true){
                  //  pChart.animateXY(2000,2000);
                    //inview2 = !inview2;
                //}
                //if (scrollY > 1400 && inview2 == true){
                  //  inview1 = !inview1;
                    //pChart.animateXY(2000,2000);
                //}
            //}
        //});
        return v;
    }


    /**
     * used to create the entry info fragments
     * @param v View
     * @return
     */
    private View createEntryInfo(View v){
        //LineChart chart = (LineChart) view.findViewById(R.id.eibar1);
        //chart.setData(UniversityStatsChartMaker.getChartPreviousEntries(course, chart));
        //chart.animateY(2000);
        return v;
    }


    /**
     * used to create the user rating fragments
     * @param v View
     * @return
     */
    private View createUserRating(View v){

        return v;
    }
}
