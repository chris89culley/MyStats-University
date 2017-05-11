package FragmentSelectors;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.chris.mystats_univeristy.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import Data.Course;
import MPChart.UniversityStatsChartMaker;
import Adapters.ExpandableSatisfactionAdapter;

/**
 * Created by c077ing on 08/03/2017.
 */

public class StatsPageSelector extends Fragment {

    private int pos;
    private View view;
    private PieChart pChart;
    private Course course;
    private LineChart lineChart;
    Typeface retroFont;

    /**
     * sets the position of the fragment pager and the course data
     * @param position
     * @param course
     */
    public StatsPageSelector(int position, Course course) {
        this.pos = position;
        this.course = course;

        }


    /**
     * on create view determines which fragment to inflate, pending on the position.
     *
     * @param inflater           - The layout inflator
     * @param container          - The container where the view is to be held
     * @param savedInstanceState - The current saved instance
     * @return The view containing the selected fragment
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        retroFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Josefin_Sans/JosefinSans-SemiBold.ttf");
        try {
        switch(pos) {
            case 0:
                view = inflater.inflate(R.layout.fragment_cost_stats, container, false);
                createCostStatsPage();
                return view;
            case 1:
                view =  inflater.inflate(R.layout.fragment_employ_stats, container, false);
                createEmploymentStatsPage();
                return view;
            case 2:

                view =  inflater.inflate(R.layout.fragment_satisfaction_stats, container, false);
                createSatisfactionStatsPage();
                return  view;

            case 3:
                view = inflater.inflate(R.layout.fragment_study_info, container, false);
                createStudyInfo();

                return view;
            case 4:
                view = inflater.inflate(R.layout.fragment_entry_info, container, false);
                createEntryInfo();

                return view;

            default:
                return inflater.inflate(R.layout.fragment_error, container, false);
        }}
        catch(Exception IO) {
            return view = inflater.inflate(R.layout.fragment_error, container, false);
        }
    }



    /**
     * This method is used to restart the animation when going onto the right fragment
     * used to get over the problem with fragments caching ahead of being opened and doing the animation before the view was seen
     * @param visible Sets to true if the fragment is on the screen
     */
    @Override
    public void setMenuVisibility(final boolean visible) {
            super.setMenuVisibility(visible);
            if (visible) {
                try {
                    switch (pos){
                        case 0:
                            //Nothing to animate
                        case 1:
                            animatePieChartEmployStats();
                        case 2:
                            //Nothing to Animate
                        case 3:
                            animatePieChartCaseStudyInfo();
                        case 4:
                            animateLineGraphsEntryInfo();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
        }}

    /**
     * Animates the pie chart on the EmployStats page when called
     */
    private void animatePieChartEmployStats(){
        pChart = (PieChart) view.findViewById(R.id.espie1);
        pChart.animateXY(2000, 2000);
    }

    /**
     * Animates the pieCharts on the Case Study info page
     */
    private void animatePieChartCaseStudyInfo()
    {
        pChart = (PieChart) view.findViewById(R.id.sipie1);
        pChart.animateXY(2000, 2000);
        pChart = (PieChart) view.findViewById(R.id.sipie2);
        pChart.animateXY(2000, 2000);
    }

    /**
     * Animates the line graph on the Entry Info page when called
     */
    private void animateLineGraphsEntryInfo(){
        lineChart = (LineChart) view.findViewById(R.id.linechart);
        lineChart.animateX(800, Easing.EasingOption.EaseInCubic);
    }

    /**
     * Sets the fonts of each textView up and then sets the data for the lineChart
     */
    private void createEntryInfo() {
        lineChart = (LineChart) view.findViewById(R.id.linechart);

        TextView pageDescription = (TextView) view.findViewById(R.id.pageDescription);
        pageDescription.setTypeface(retroFont);

        TextView yAxislabel = (TextView) view.findViewById(R.id.esYAxis);
        yAxislabel.setTypeface(retroFont);

        TextView xAxislabel = (TextView) view.findViewById(R.id.esXAxis);
        xAxislabel.setTypeface(retroFont);
        lineChart.setData(UniversityStatsChartMaker.getChartPreviousEntries(course, lineChart));
    }

    /**
     * This creates the satisfaction stats  page with list view with bar charts giving the students satisfaction levels with the course
     */
    private void createSatisfactionStatsPage(){

        TextView satisfactionTitle = (TextView) view.findViewById(R.id.satisfactionTitle);
        satisfactionTitle.setTypeface(retroFont);
        final ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView1);
        ExpandableSatisfactionAdapter adapter = new ExpandableSatisfactionAdapter(getContext(), course, getActivity(), (satisfactionTitle.getTextSize()*0.30f));

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int prev = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != prev){
                    expandableListView.collapseGroup(prev);
                }
                prev = groupPosition;
            }
        });

        expandableListView.setAdapter(adapter);
    }

    /**
     * used to create the costs stats fragments
     */
    private void createCostStatsPage() {
        TextView costIntro = (TextView) view.findViewById(R.id.cost_text_id);
        TextView accomPvt = (TextView) view.findViewById(R.id.private_id);
        TextView accomHalls = (TextView) view.findViewById(R.id.halls_id);
        TextView insuanceCost = (TextView) view.findViewById(R.id.insurance_id);
        TextView personalCost = (TextView) view.findViewById(R.id.personal_id);
        TextView foodCost = (TextView) view.findViewById(R.id.food_id);
        TextView leisureCost = (TextView) view.findViewById(R.id.leisure_id);
        TextView travelCost = (TextView) view.findViewById(R.id.travel_id);

        costIntro.setTypeface(retroFont);
        accomPvt.setTypeface(retroFont);
        accomHalls.setTypeface(retroFont);
        insuanceCost.setTypeface(retroFont);
        personalCost.setTypeface(retroFont);
        foodCost.setTypeface(retroFont);
        leisureCost.setTypeface(retroFont);
        travelCost.setTypeface(retroFont);

        int low, high;

        String[] pte = course.getPrivateAccomodationDetails().getData();
        low = Integer.parseInt(pte[0]);
        high = Integer.parseInt(pte[1]);
        accomPvt.setText("Private: £" + low + " - £" + high);
        accomPvt.setTypeface(retroFont);

        String[] inst = course.getInstitutionalAccomDetails().getData();
        low = Integer.parseInt(inst[0]);
        high = Integer.parseInt(inst[1]);
        accomHalls.setText("Student Halls: £" + low + " - £" + high);
        accomHalls.setTypeface(retroFont);

    }

    /**
     * used to create the employment stats fragments
     */
    private void createEmploymentStatsPage(){


        final TextView chartTitle1 = (TextView) view.findViewById(R.id.esChartTitle1);
        chartTitle1.setTypeface(retroFont);



        pChart = (PieChart) view.findViewById(R.id.espie1);
        pChart.setData(UniversityStatsChartMaker.getChartEploymentSixMonths(course, pChart));
        pChart.getLegend().setTypeface(retroFont);
        pChart.getLegend().setTextColor(ColorTemplate.rgb("#3C6478"));


        view.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            //false if chart in view
            boolean animFlag = false;
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                try{
                //chart position plus chart height
                float animPos = pChart.getY()+pChart.getHeight();
                if(animFlag == true && scrollY < animPos ){
                    pChart.animateXY(2000,2000);
                    animFlag = false;
                } else if(scrollY > animPos ){
                    animFlag = true;
                }
            }catch (Exception e){
                    System.out.println(e);
                }}
        }) ;




        final TextView salarySixmonthText = (TextView) view.findViewById(R.id.animText1);
        final TextView salaryFourtymonthText = (TextView) view.findViewById(R.id.animText2);
        final TextView monthlyBreakdownTitle = (TextView) view.findViewById(R.id.monthlyBreakdownTitle);
        final TextView averageSalaryTitle = (TextView) view.findViewById(R.id.averageSalaryTitle);

        monthlyBreakdownTitle.setTypeface(retroFont);
        salaryFourtymonthText.setTypeface(retroFont);
        averageSalaryTitle.setTypeface(retroFont);
        salarySixmonthText.setTypeface(retroFont);


        salaryFourtymonthText.setText("The average salary 40 months after was " + course.getAverageSalaryAfter40MonthsText().trim());


        salarySixmonthText.setText("The average salary 6 months after was " + course.getAverageSalaryAfter6MonthsText().trim());

        double tax = 0;
        double salary = 0;
        try{
            Log.d("Salary", course.getAverageSalaryAfter6MonthsText().substring(1));
            salary = Double.parseDouble(course.getAverageSalaryAfter6MonthsText().substring(1));
            Log.d("", Double.toString(salary));
        }catch (Exception e){
            Log.d("Exception", e.toString());

            e.printStackTrace();
        }
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
        tax = ((salary - 11000) * 0.2) / 12;

        salary = salary - tax;
        salary = salary / 12;



        salary -= repayment;
        salary = Math.round(salary);
        tax = Math.round(tax);

        TextView monthlyWage = (TextView) view.findViewById(R.id.monthlyBreakdownAfterTax);
        TextView loanRepayment = (TextView) view.findViewById(R.id.monthlyBreakdownLoanrepay);
        TextView taxPayment = (TextView) view.findViewById(R.id.monthlyBreakdownTax);

        monthlyWage.setTypeface(retroFont);
        loanRepayment.setTypeface(retroFont);
        taxPayment.setTypeface(retroFont);

        monthlyWage.setText("Average monthly wage of: £" + salary+" (after payments)");
        loanRepayment.setText("Student loan repayment of: £"+repayment+" per month");
        taxPayment.setText("Paying £"+tax+" in tax per month" );
        }
    }


    /**
     * used to create the study info fragments
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createStudyInfo(){

        TextView chartTitle1 = (TextView) view.findViewById(R.id.siChartTitle1);
        chartTitle1.setTypeface(retroFont);

        TextView chartTitle2 = (TextView) view.findViewById(R.id.siChartTitle2);
        chartTitle2.setTypeface(retroFont);


        String[] vals = course.getPercentageInScheduled().getData();

        TextView stat1 = (TextView) view.findViewById(R.id.sistat1);
        stat1.setTypeface(retroFont);
        stat1.setText(vals[0]+"% "+"Of time spent in supervised learning (lectures and seminars)");

        vals = course.getPercentageAssesedByCourseWork().getData();

        TextView stat2 = (TextView) view.findViewById(R.id.sistat2);
        stat2.setTypeface(retroFont);
        stat2.setText(vals[0]+"% "+"Of the course is assessed by coursework");


        //Degree classification chart
        pChart =  (PieChart) view.findViewById(R.id.sipie1);
        pChart.setData(UniversityStatsChartMaker.getChartDegreeClass(course, pChart));
        pChart.getLegend().setTypeface(retroFont);
        pChart.getLegend().setTextColor(ColorTemplate.rgb("#3C6478"));

        //What students are doing chart
        pChart =  (PieChart) view.findViewById(R.id.sipie2);
        pChart.setData(UniversityStatsChartMaker.getChartContinuationStats(course, pChart));
        pChart.getLegend().setTypeface(retroFont);
        pChart.getLegend().setTextColor(ColorTemplate.rgb("#3C6478"));

        view.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            boolean animFlag1 = false;
            boolean animFlag2 = false;
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                PieChart pie1 = (PieChart) view.findViewById(R.id.sipie1);
                PieChart pie2 = (PieChart) view.findViewById(R.id.sipie2);
                float animPos = pie1.getY()+pie1.getHeight();

                //using the postions of the charts to trigger animations in the scroll view
                if(animFlag1 == true && scrollY < animPos ){
                    pie1.animateXY(2000,2000);
                    animFlag1 = false;
                } else if(scrollY > animPos ){
                    animFlag1 = true;
                }else if(animFlag2 == true && scrollY+view.getHeight() > pie2.getY()){
                    pie2.animateXY(2000,2000);
                    animFlag2 = false;
                } else if (scrollY+view.getHeight() < pie2.getY() && animFlag2 == false ){
                    animFlag2 = true;
                }
            }
        });


    }

}
