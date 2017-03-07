package MPChart;

import android.widget.Toast;

import com.example.chris.mystats_univeristy.HomePage;
import com.example.chris.mystats_univeristy.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 06/03/2017.
 */

public class Charts{

    /*Takes in Data for the chart, the labels for each bar, a chart object from the view and title of the chart */
        public static BarData constructBarChart(String[] tags,String[] values, BarChart chart, String DataTitle){
            //Convert the array of strings into an array of floats
            float [] data = new float[values.length];
            for (int i = 0; i < data.length; i++) {
                data[i] = Float.parseFloat(values[i]);
            }

            ArrayList<BarEntry> entries = new ArrayList<>();
            ArrayList<String> labels = new ArrayList<>();

            for (int i = 0; i < data.length; i++){
                entries.add(new BarEntry(i,data[i]));
                labels.add(tags[i]);
            }

            BarDataSet barDataSet = new BarDataSet(entries,DataTitle); //Creating a dataSet for the chart
            barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

            chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));//Setting the X axis labels

            XAxis xaxis = chart.getXAxis(); // gets the X axis of the chart
            xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);//Moves the labels to the bottom of thr x axis
            xaxis.setLabelCount(labels.size()); //sets the labels amounts to the to the number of labels in the arraylist -so none are cut off
            BarData theData = new BarData(barDataSet);
            return theData;
        }
        /*Overrloading for optional arguements - Chart is labelled Data by default*/
        public static BarData constructBarChart(String[] tags, String[] data, BarChart chart){
               return constructBarChart(tags,data, chart ,"Data");
        }

        public static PieData constructPieChart(String[] tags,String [] values, PieChart chart, String DataTitle){
            //Convert the array of strings into an array of floats
            float [] data = new float[values.length];
            for (int i = 0; i < data.length; i++) {
                data[i] = Float.parseFloat(values[i]);
            }

            ArrayList<PieEntry> yentries = new ArrayList<>();

            //populates the arraylist with pie entries from the data array
            for (int i = 0; i < data.length; i++) {
                yentries.add(new PieEntry((data[i]), tags[i]));
            }
            //Ensures two elements in thst array so that if one stat is supplied it can be clearly identified on the pie chart
            if (yentries.size() == 1 ) {
                if (yentries.get(0).getValue() <= 10f) {
                    yentries.add(new PieEntry(10f - yentries.get(0).getValue(),"Other"));
                } else{
                    yentries.add(new PieEntry(100f - yentries.get(0).getValue(),"Other"));
                }
            }

            //piechart display settings
            chart.setHoleRadius(25f);
            chart.setTransparentCircleAlpha(0);
            chart.setCenterText(DataTitle);
            chart.setCenterTextSize(10);
            chart.setTouchEnabled(true);
            chart.setHighlightPerTapEnabled(true);

            //Creating a dataSet for the chart
            PieDataSet pieDataSet = new PieDataSet(yentries,DataTitle);
            pieDataSet.setSliceSpace(2);
            pieDataSet.setValueTextSize(12);
            pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

            chart.setDrawEntryLabels(true);
            PieData theData = new PieData(pieDataSet);
            theData.setValueFormatter(new PercentFormatter());

            return theData;
        }
        /*Overrloading for optional arguements - Chart is labelled Data by default*/
        public static PieData constructPieChart(String[] tags, String[] data, PieChart chart){
            return constructPieChart(tags,data, chart ,"Data");
        }






}
