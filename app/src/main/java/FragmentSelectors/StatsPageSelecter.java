package FragmentSelectors;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
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

public class StatsPageSelecter extends Fragment {

    private int pos;
    private View view;
    private BarChart chart;
    private HorizontalBarChart hbchart;
    private PieChart pChart;
    private Course course;
    private LineChart lineChart;
    private Activity activity;

    /**
     * sets the position of the fragment pager and the course data
     * @param position
     * @param course
     */
    public StatsPageSelecter(int position, Course course) {
        this.pos = position;
        this.course = course;

        }


    /**
     * on create view determines which fragment to inflate, pending on the position.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Typeface retroFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Josefin_Sans/JosefinSans-SemiBold.ttf");
        Typeface vintage = Typeface.createFromAsset(getActivity().getAssets(), "fonts/octin vintage b rg.ttf");
        try {
        switch(pos) {
            case 0:

                view = inflater.inflate(R.layout.fragment_cost_stats, container, false);createCostStatsPage(view,retroFont);
                return view;
            case 1:
                view =  inflater.inflate(R.layout.fragment_employ_stats, container, false);
                activity.onRestoreInstanceState(onResume());
                createEmploymentStatsPage(view, retroFont);
                return view;
            case 2:

                view =  inflater.inflate(R.layout.fragment_satisfaction_stats, container, false);
                view.invalidate();
                createSatisfactionStatsPage(view, retroFont);
                return  view;

            case 3:
                view = inflater.inflate(R.layout.fragment_study_info, container, false);
                createStudyInfo(view,retroFont);
                return view;
            case 4:
                //Entry_information fragment
                // set the view as the fragment_Entry_info view
                view = inflater.inflate(R.layout.fragment_entry_info, container, false);

                //Create Entry Intro page fonts
                createEntryInfo(retroFont);
                return view;

            default:
                return inflater.inflate(R.layout.fragment_error, container, false);
        }}
        catch(Exception IO) {
            return view = inflater.inflate(R.layout.fragment_error, container, false);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        // put your code here...

    }
    private void createEntryInfo(Typeface retroFont) {
        lineChart = (LineChart) view.findViewById(R.id.linechart);

        //Set the description
        TextView pageDescription = (TextView) view.findViewById(R.id.pageDescription);
        pageDescription.setTypeface(retroFont);

        //Sets the Y Axis title
        TextView yAxislabel = (TextView) view.findViewById(R.id.esYAxis);
        yAxislabel.setTypeface(retroFont);

        //Sets the X Axis title
        TextView xAxislabel = (TextView) view.findViewById(R.id.esXAxis);
        xAxislabel.setTypeface(retroFont);
        lineChart.setData(UniversityStatsChartMaker.getChartPreviousEntries(course, lineChart));
    }

    /**
     * This creates the satisfaction stats  page with list view with bar charts giving the students satisfaction levels with the course
     * @param v - The ivew
     * @param fontUsed - The font used on the page
     * @return - The page with the satisfaction stats
     */
    private View createSatisfactionStatsPage(View v, Typeface fontUsed){

        TextView satisfactionTitle = (TextView) view.findViewById(R.id.satisfactionTitle);
        satisfactionTitle.setTypeface(fontUsed);
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
        return v;
    }

    /**
     * used to create the costs stats fragments
     * @param v View
     * @param font The font being used
     * @return
     */
    private View createCostStatsPage(View v,Typeface font) {
        TextView costIntro = (TextView) v.findViewById(R.id.cost_text_id);
        TextView accomPvt = (TextView) v.findViewById(R.id.private_id);
        TextView accomHalls = (TextView) v.findViewById(R.id.halls_id);
        TextView insuanceCost = (TextView) v.findViewById(R.id.insurance_id);
        TextView personalCost = (TextView) v.findViewById(R.id.personal_id);
        TextView foodCost = (TextView) v.findViewById(R.id.food_id);
        TextView leisureCost = (TextView) v.findViewById(R.id.leisure_id);
        TextView travelCost = (TextView) v.findViewById(R.id.travel_id);

        costIntro.setTypeface(font);
        accomPvt.setTypeface(font);
        accomHalls.setTypeface(font);
        insuanceCost.setTypeface(font);
        personalCost.setTypeface(font);
        foodCost.setTypeface(font);
        leisureCost.setTypeface(font);
        travelCost.setTypeface(font);

        int low, high;

        String[] pte = course.getPrivateAccomodationDetails().getData();
        low = Integer.parseInt(pte[0]);
        high = Integer.parseInt(pte[1]);
        accomPvt.setText("Private: £" + low + " - £" + high);
        accomPvt.setTypeface(font);

        String[] inst = course.getInstitutionalAccomDetails().getData();
        low = Integer.parseInt(inst[0]);
        high = Integer.parseInt(inst[1]);
        accomHalls.setText("Student Halls: £" + low + " - £" + high);
        accomHalls.setTypeface(font);

        return v;
    }

    /**
     * used to create the employment stats fragments
     * @param v View
     * @param font The font being used
     * @return
     */
    private View createEmploymentStatsPage(View v,Typeface font){

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(null,android.R.layout.simple_list_item_1,arr);
        //ExpandableListView elv = (ExpandableListView) view.findViewById(R.id.esExpandableListView);

        final TextView chartTitle1 = (TextView) view.findViewById(R.id.esChartTitle1);
        chartTitle1.setTypeface(font);

        pChart = (PieChart) view.findViewById(R.id.espie1);
        pChart.setData(UniversityStatsChartMaker.getChartEploymentSixMonths(course, pChart));
        pChart.getLegend().setTypeface(font);
        pChart.getLegend().setTextColor(ColorTemplate.rgb("#3C6478"));
        pChart.animateXY(2000,2000);


        v.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            //false if chart in view
            boolean animFlag = false;
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //chart position plus chart height
                float animPos = pChart.getY()+pChart.getHeight();
                if(animFlag == true && scrollY < animPos ){
                    pChart.animateXY(2000,2000);
                    animFlag = false;
                } else if(scrollY > animPos ){
                    animFlag = true;
                }
            }
        });



        final TextView salarySixmonthText = (TextView) view.findViewById(R.id.animText1);
        final TextView salaryFourtymonthText = (TextView) view.findViewById(R.id.animText2);
        final TextView monthlyBreakdownTitle = (TextView) view.findViewById(R.id.monthlyBreakdownTitle);
        final TextView averageSalaryTitle = (TextView) view.findViewById(R.id.averageSalaryTitle);

        monthlyBreakdownTitle.setTypeface(font);
        salaryFourtymonthText.setTypeface(font);
        averageSalaryTitle.setTypeface(font);
        salarySixmonthText.setTypeface(font);

        salaryFourtymonthText.setTextSize(13f);
        //salaryFourtymonthText.setY(salaryFourtymonthText.getY() + 10f);
        salaryFourtymonthText.setText("The average salary 40 months after\n was\n" + course.getAverageSalaryAfter40MonthsText().trim()+" GBP");

        salarySixmonthText.setTextSize(13f);
        //salarySixmonthText.setY(salarySixmonthText.getY() + 10f);
        salarySixmonthText.setText("The average salary 6 months after\n was\n" + course.getAverageSalaryAfter6MonthsText().trim()+" GBP");

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
        salary = salary * 0.8;
        salary = salary / 12;

        salary -= repayment;
        salary = Math.round(salary);

        TextView monthlyWage = (TextView) view.findViewById(R.id.monthlyBreakdownAfterTax);
        TextView loanRepayment = (TextView) view.findViewById(R.id.monthlyBreakdownLoanrepay);
        TextView taxPayment = (TextView) view.findViewById(R.id.monthlyBreakdownTax);

        monthlyWage.setTypeface(font);
        loanRepayment.setTypeface(font);
        taxPayment.setTypeface(font);

        monthlyWage.setText("Average monthly wage of: " + salary+" Pounds");
        loanRepayment.setText("Student loan repayment of: "+repayment+" Pounds");
        taxPayment.setText("Paying 20% tax");

        }
       return v ;
    }
    /**
     * used to create the study info fragments
     * @param v View
     * @return
     */
    private View createStudyInfo(View v,Typeface font){

        TextView chartTitle1 = (TextView) view.findViewById(R.id.siChartTitle1);
        chartTitle1.setTypeface(font);

        TextView chartTitle2 = (TextView) view.findViewById(R.id.siChartTitle2);
        chartTitle2.setTypeface(font);

        TextView aBitMoreStudyInfoTitle = (TextView) view.findViewById(R.id.aBitMoreStudyInfoTitle);
        aBitMoreStudyInfoTitle.setTypeface(font);


        String[] vals = course.getPercentageInScheduled().getData();

        TextView stat1 = (TextView) view.findViewById(R.id.sistat1);
        stat1.setTypeface(font);
        stat1.setText(vals[0]+"% "+"Of time spent in supervised learning (lectures and seminars)");

        vals = course.getPercentageAssesedByCourseWork().getData();

        TextView stat2 = (TextView) view.findViewById(R.id.sistat2);
        stat2.setTypeface(font);
        stat2.setText(vals[0]+"% "+"Of the course is assessed by coursework");


        //Chart 1 data population and settings
        pChart =  (PieChart) view.findViewById(R.id.sipie1);
        pChart.setData(UniversityStatsChartMaker.getChartDegreeClass(course, pChart));
        pChart.getLegend().setTypeface(font);
        pChart.getLegend().setTextColor(ColorTemplate.rgb("#3C6478"));
        pChart.animateXY(2000,2000);

        //Chart 2 data population and settings
        pChart =  (PieChart) view.findViewById(R.id.sipie2);
        pChart.setData(UniversityStatsChartMaker.getChartContinuationStats(course, pChart));
        pChart.getLegend().setTypeface(font);
        pChart.getLegend().setTextColor(ColorTemplate.rgb("#3C6478"));
        pChart.animateXY(2000,2000);

        v.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            //false if chart in view
            boolean animFlag1 = false;
            boolean animFlag2 = false;
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //chart position plus chart height
                PieChart pie1 = (PieChart) view.findViewById(R.id.sipie1);
                PieChart pie2 = (PieChart) view.findViewById(R.id.sipie2);

                //animation position
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
