package MPChart;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

/**
 * Created by Jack on 06/03/2017.
 */

public class Charts{
    /*Takes in Data for the chart, the labels for each bar, a chart object from the view and title of the chart */
        public static BarData constructBarChart(String[] tags,float[] data, BarChart chart, String DataTitle){
            ArrayList<BarEntry> entries = new ArrayList<>();
            ArrayList<String> labels = new ArrayList<>();

            for (int i = 0; i < data.length; i++){
                entries.add(new BarEntry(i,data[i]));
                labels.add(tags[i]);
            }

            BarDataSet barDataSet = new BarDataSet(entries,DataTitle); //Creating a dataSet for the chart
            chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));//Setting the X axis labels
            BarData theData = new BarData(barDataSet);
            return theData;
        }
    /*Overrloading for optional arguements - Chart is labelled Data by default*/
    public static BarData constructBarChart(String[] tags,float[] data, BarChart chart){
           return constructBarChart(tags,data, chart ,"Data");
    }






}
