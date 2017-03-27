package MPChart;

import android.graphics.Color;
import android.graphics.Typeface;
import android.provider.CalendarContract;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.chris.mystats_univeristy.HomePage;
import com.example.chris.mystats_univeristy.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import java.util.Arrays;

import java.util.ArrayList;


/**
 * Created by Jack on 06/03/2017.
 *
 * This class handles the generic building of charts
 *
 */

public class GenericChartMaker {


    /**
     *  A functions which take two strings arrays and reference to the chart and returns it as a bar data to construct a bar chart
     * @param tags - an Array labels for the data provided
     * @param values - an Array of values for each of the labelled fields
     * @param chart - reference to the chart from the view
     * @param DataTitle - Title of the chart
     * @return The BarData object for the data provided to be used for bar chart construction
     */
    public static BarData constructBarChart(String[] tags,String[] values, BarChart chart, String DataTitle){
        try {

            float[] data;
            //Convert the array of strings into an array of floats
            if (values.length > 0){
                data = new float[values.length];
            }  else{
                return new BarData();
            }
            for (int i = 0; i < data.length; i++) {
                data[i] = Float.parseFloat(values[i]);
            }

            ArrayList<BarEntry> entries = new ArrayList<>();
            ArrayList<String> labels = new ArrayList<>();

            for (int i = 0; i < data.length; i++) {
                try {
                    entries.add(new BarEntry(i, data[i]));
                    labels.add(tags[i]);
                } catch (Exception e){
                    ArrayList<BarEntry> yentries = new ArrayList<>();
                    yentries.add(new BarEntry(10f,10f));
                    return new BarData(new BarDataSet(yentries,""));
                }
            }

            BarDataSet barDataSet = new BarDataSet(entries, DataTitle); //Creating a dataSet for the chart
            //barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
            int[]Colors =  {ColorTemplate.rgb("F26D21"),ColorTemplate.rgb("C2571A"),ColorTemplate.rgb("F58B4c"),ColorTemplate.rgb("DA621E")};
            //Setting the colours of the data set
            barDataSet.setColors(Colors);

            chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));//Setting the X axis labels

            XAxis xaxis = chart.getXAxis(); // gets the X axis of the chart


            xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);//Moves the labels to the bottom of thr x axis

            chart.setDrawGridBackground(false);
            chart.getAxisLeft().setDrawGridLines(false);
            chart.getXAxis().setDrawGridLines(false);
            chart.getAxisLeft().setStartAtZero(true);
            Description emptyDescription = new Description();
            emptyDescription.setText("");
            chart.setDescription(emptyDescription);
            xaxis.setLabelCount(labels.size()); //sets the labels amounts to the to the number of labels in the arraylist -so none are cut off
            BarData theData = new BarData(barDataSet);
            return theData;
        } catch (Exception e){

            return new BarData();

        }
    }

    /**
     *  A functions which take two strings arrays and reference to the chart and returns it as a bar data to construct a bar chart
     * @param tags - an Array labels for the data provided
     * @param values - an Array of values for each of the labelled fields
     * @param chart - reference to the chart from the view
     * @param DataTitle - Title of the chart
     * @param Colour - colour preset selction
     * @return The BarData object for the data provided to be used for bar chart construction
     */
    public static BarData constructBarChart(String[] tags,String[] values, BarChart chart, String DataTitle,String Colour){
        try {

            float[] data;
            //Convert the array of strings into an array of floats
            if (values.length > 0){
                data = new float[values.length];
            }  else{
                return new BarData();
            }
            for (int i = 0; i < data.length; i++) {
                data[i] = Float.parseFloat(values[i]);
            }

            ArrayList<BarEntry> entries = new ArrayList<>();
            ArrayList<String> labels = new ArrayList<>();

            for (int i = 0; i < data.length; i++) {
                try {
                    entries.add(new BarEntry(i, data[i]));
                    labels.add(tags[i]);
                } catch (Exception e){
                    ArrayList<BarEntry> yentries = new ArrayList<>();
                    yentries.add(new BarEntry(10f,10f));
                    return new BarData(new BarDataSet(yentries,""));
                }
            }

            BarDataSet barDataSet = new BarDataSet(entries, DataTitle); //Creating a dataSet for the chart
            //Setting the colour of the chart too each preset
            Colour = Colour.toLowerCase();
            int[] Colors;
            switch (Colour){
                case "orange":
                    Colors = new int[] {ColorTemplate.rgb("C2571A"),ColorTemplate.rgb("F26D21"),ColorTemplate.rgb("F58B4C"),ColorTemplate.rgb("DA621E")};
                case "indigo":
                    Colors = new int[] {ColorTemplate.rgb("093145"),ColorTemplate.rgb("0D3D56"),ColorTemplate.rgb("3C6478"),ColorTemplate.rgb("0C374D")};
                case "blue":
                    Colors = new int[] {ColorTemplate.rgb("107896"),ColorTemplate.rgb("1496BB"),ColorTemplate.rgb("43ABC9"),ColorTemplate.rgb("1287A8")};
                    break;
                default: Colors = new int[] {ColorTemplate.rgb("C2571A"),ColorTemplate.rgb("F26D21"),ColorTemplate.rgb("F58B4C"),ColorTemplate.rgb("DA621E")};
                    break;
            }
            barDataSet.setColors(Colors);

            chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));//Setting the X axis labels

            XAxis xaxis = chart.getXAxis(); // gets the X axis of the chart


            xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);//Moves the labels to the bottom of thr x axis

            chart.setDrawGridBackground(false);
            chart.getAxisLeft().setDrawGridLines(false);
            chart.getXAxis().setDrawGridLines(false);
            chart.getAxisLeft().setStartAtZero(true);
            Description emptyDescription = new Description();
            emptyDescription.setText("");
            chart.setDescription(emptyDescription);
            xaxis.setLabelCount(labels.size()); //sets the labels amounts to the to the number of labels in the arraylist -so none are cut off
            BarData theData = new BarData(barDataSet);
            return theData;
        } catch (Exception e){

            return new BarData();

        }
    }
    /**
     *  Overloaded form of constructBarChart to be used when no data title parameter has been provided
     * @param tags - an Array labels for the data provided
     * @param values - an Array of values for each of the labelled fields
     * @param chart - reference to the chart from the view
     * @return The BarData object for the data provided to be used for bar chart construction
     */
    public static BarData constructBarChart(String[] tags, String[] values, BarChart chart){
        return constructBarChart(tags,values, chart ,"Data");
    }

    /**
     *  A functions which take two strings arrays and refernce to the chart and returns it as a pie data to construct a pie chart
     * @param tags - an Array labels for the data provided
     * @param values - an Array of values for each of the labelled fields
     * @param chart - reference to the chart from the view
     * @param dataTitle - Title of the chart
     * @return The PieData object for the data provided to be used for pie chart construction
     */
    public static PieData constructPieChart(String[] tags,String [] values, PieChart chart, String dataTitle){
        try {
            float[] data;
            //Convert the array of strings into an array of floats
            if (values.length > 0 ){
                data = new float[values.length];
            }  else{
                return new PieData();
            }

            for (int i = 0; i < data.length; i++) {
                try {
                    data[i] = Float.parseFloat(values[i]);
                } catch (Exception e){
                    ArrayList<PieEntry> yentries = new ArrayList<>();
                    yentries.add(new PieEntry(100f,"Data"));
                    return new PieData(new PieDataSet(yentries,"tag"));
                }
            }

            ArrayList<PieEntry> yentries = new ArrayList<>();

            //populates the arraylist with pie entries from the data array
            for (int i = 0; i < data.length; i++) {
                yentries.add(new PieEntry((data[i]), tags[i]));
            }
            //Ensures two elements in thst array so that if one stat is supplied it can be clearly identified on the pie chart
            if (yentries.size() == 1) {
                if (yentries.get(0).getValue() <= 10f) {
                    yentries.add(new PieEntry(10f - yentries.get(0).getValue(), "Other"));
                } else {
                    yentries.add(new PieEntry(100f - yentries.get(0).getValue(), "Other"));
                }
            }


            //piechart display settings
            chart.setHoleRadius(40f);
            chart.setHighlightPerTapEnabled(true);

            //Creating a dataSet for the chart
            PieDataSet pieDataSet = new PieDataSet(yentries, "");
            pieDataSet.setSliceSpace(4);
            pieDataSet.setValueTextSize(16);


            //Colours array in the form of hex codes
            int[]Colors =  {ColorTemplate.rgb("F26D21"),ColorTemplate.rgb("C2571A"),ColorTemplate.rgb("F58B4c"),ColorTemplate.rgb("DA621E")};
            //Setting the colours of the data set
            pieDataSet.setColors(Colors);

            Description description = new Description();
            description.setText(dataTitle);
            description.setPosition(0f,0f);
            chart.setDescription(description);
            chart.setDrawEntryLabels(true);

            Legend l = chart.getLegend();
            l.setTextColor(Color.WHITE);
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
            l.setOrientation(Legend.LegendOrientation.VERTICAL);
            l.setDrawInside(false);
            l.setXEntrySpace(7f);
            l.setYEntrySpace(0f);
            l.setYOffset(0f);

            chart.setDrawSliceText(false);

            PieData theData = new PieData(pieDataSet);
            theData.setValueTextColor(Color.WHITE);
            theData.setValueTypeface(Typeface.DEFAULT_BOLD);
            theData.setValueFormatter(new PercentFormatter());

            return theData;
        } catch (Exception e) {
            return new PieData();
        }
    }
    /**
     *  Overloaded form of constructPieChart to be used when no data title parameter has been provided
     * @param tags - an Array labels for the data provided
     * @param values - an Array of values for each of the labelled fields
     * @param chart - reference to the chart from the view
     * @return The PieData object for the data provided to be used for pie chart construction
     */
    public static PieData constructPieChart(String[] tags, String[] values, PieChart chart){
        return constructPieChart(tags,values, chart);
    }
    /**
     *  A functions which take two strings arrays and refernce to the chart and returns it as a pie data to construct a pie chart
     * @param tags - an Array labels for the data provided
     * @param values - an Array of values for each of the labelled fields
     * @param chart - reference to the chart from the view
     * @param dataTitle - Title of the chart
     * @param Colour - colour preset selction
     * @return The PieData object for the data provided to be used for pie chart construction
     */
    public static PieData constructPieChart(String[] tags,String [] values, PieChart chart, String dataTitle,String Colour){
        try {
            float[] data;
            //Convert the array of strings into an array of floats
            if (values.length > 0 ){
                data = new float[values.length];
            }  else{
                return new PieData();
            }

            for (int i = 0; i < data.length; i++) {
                try {
                    data[i] = Float.parseFloat(values[i]);
                } catch (Exception e){
                    ArrayList<PieEntry> yentries = new ArrayList<>();
                    yentries.add(new PieEntry(100f,"Data"));
                    return new PieData(new PieDataSet(yentries,"tag"));
                }
            }

            ArrayList<PieEntry> yentries = new ArrayList<>();

            //populates the arraylist with pie entries from the data array
            for (int i = 0; i < data.length; i++) {
                yentries.add(new PieEntry((data[i]), tags[i]));
            }
            //Ensures two elements in thst array so that if one stat is supplied it can be clearly identified on the pie chart
            if (yentries.size() == 1) {
                if (yentries.get(0).getValue() <= 10f) {
                    yentries.add(new PieEntry(10f - yentries.get(0).getValue(), "Other"));
                } else {
                    yentries.add(new PieEntry(100f - yentries.get(0).getValue(), "Other"));
                }
            }


            //piechart display settings
            chart.setHoleRadius(40f);
            chart.setHighlightPerTapEnabled(true);

            //Creating a dataSet for the chart
            PieDataSet pieDataSet = new PieDataSet(yentries, "");
            pieDataSet.setSliceSpace(4);
            pieDataSet.setValueTextSize(18);

            //Setting the colour of the chart too each preset
            Colour = Colour.toLowerCase();
            int[] Colors;
            switch (Colour){
                case "orange":
                    Colors = new int[] {ColorTemplate.rgb("C2571A"),ColorTemplate.rgb("F26D21"),ColorTemplate.rgb("F58B4C"),ColorTemplate.rgb("DA621E")};
                case "indigo":
                    Colors = new int[] {ColorTemplate.rgb("093145"),ColorTemplate.rgb("0D3D56"),ColorTemplate.rgb("3C6478"),ColorTemplate.rgb("0C374D")};
                case "blue":
                    Colors = new int[] {ColorTemplate.rgb("107896"),ColorTemplate.rgb("1496BB"),ColorTemplate.rgb("43ABC9"),ColorTemplate.rgb("1287A8")};
                    break;
                default: Colors = new int[] {ColorTemplate.rgb("C2571A"),ColorTemplate.rgb("F26D21"),ColorTemplate.rgb("F58B4C"),ColorTemplate.rgb("DA621E")};
                    break;
            }
            pieDataSet.setColors(Colors);

            Description emptyDescription = new Description();
            emptyDescription.setText("");
            chart.setDescription(emptyDescription);
            chart.setDrawEntryLabels(true);
            PieData theData = new PieData(pieDataSet);
            theData.setValueFormatter(new PercentFormatter());

            return theData;
        } catch (Exception e) {
            return new PieData();
        }
    }







}
