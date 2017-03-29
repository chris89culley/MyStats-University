package MPChart;

import android.graphics.Color;
import android.graphics.Typeface;

import com.example.chris.mystats_univeristy.CourseStats;
import com.example.chris.mystats_univeristy.Home;
import com.example.chris.mystats_univeristy.R;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import Data.ChartStats;


/**
 * Created by Jack on 06/03/2017.
 *
 * This class handles the generic building of charts
 *
 */

public class GenericChartMaker {


    /**
     *  A functions which take two strings arrays and reference to the chart and returns it as a bar data to construct a bar chart
     * @param cs- Chart stats objects contains the data to be displayed
     * @param chart - reference to the chart from the view
     * @param chartTitle - Title of the chart
     * @return The BarData object for the data provided to be used for bar chart construction
     */
    public static BarData constructBarChart(ChartStats cs, BarChart chart, String chartTitle){
        if(!cs.hasData()){//Checks the have data flag is false espace functioncomputer
            return new BarData();
        }

        int[] data = cs.getDataInt();
        String[] tags = cs.getTags();


            ArrayList<BarEntry> entries = new ArrayList<>();
            ArrayList<String> labels = new ArrayList<>();

            for (int i = 0; i < data.length; i++) {
                    entries.add(new BarEntry(i, data[i]));
                    labels.add(tags[i]);
            }

            BarDataSet barDataSet = new BarDataSet(entries, chartTitle); //Creating a dataSet for the chart
            int[]Colors =  {ColorTemplate.rgb("F26D21"),ColorTemplate.rgb("C2571A"),ColorTemplate.rgb("F58B4c"),ColorTemplate.rgb("DA621E")};
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
    }

    /**
     *  A functions which take two strings arrays and reference to the chart and returns it as a bar data to construct a bar chart
     * @param cs- Chart stats objects contains the data to be displayed
     * @param chart - reference to the chart from the view
     * @param chartTitle - Title of the chart
     * @param Colour - colour preset selction
     * @return The BarData object for the data provided to be used for bar chart construction
     */
    public static BarData constructBarChart(ChartStats cs, BarChart chart, String chartTitle,String Colour){
        if(!cs.hasData()){//Checks the have data flag is false espace functioncomputer
            return new BarData();
        }

        int[] data = cs.getDataInt();
        String[] tags = cs.getTags();


            ArrayList<BarEntry> entries = new ArrayList<>();
            ArrayList<String> labels = new ArrayList<>();

            for (int i = 0; i < data.length; i++) {
                    entries.add(new BarEntry(i, data[i]));
                    labels.add(tags[i]);

            }

            BarDataSet barDataSet = new BarDataSet(entries, chartTitle); //Creating a dataSet for the chart
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

    }
    /**
     *  Overloaded form of constructBarChart to be used when no data title parameter has been provided
     * @param cs- Chart stats objects contains the data to be displayed
     * @param chart - reference to the chart from the view
     * @return The BarData object for the data provided to be used for bar chart construction
     */
    public static BarData constructBarChart(ChartStats cs, BarChart chart){
        return constructBarChart(cs, chart ,"Data");
    }

    /**
     *  A functions which take two strings arrays and refernce to the chart and returns it as a pie data to construct a pie chart
     * @param cs - Chart stats objects contains the data to be displayed
     * @param chart - reference to the chart from the view
     * @param chartTitle - Title of the chart
     * @return The PieData object for the data provided to be used for pie chart construction
     */
    public static PieData constructPieChart(ChartStats cs, PieChart chart, String chartTitle){
            if(!cs.hasData()){//Checks the have data flag is false espace functioncomputer
                return new PieData(null);
            }

            int[] data = cs.getDataInt();
            String[] tags = cs.getTags();

            ArrayList<PieEntry> yentries = new ArrayList<>();

            //populates the arraylist with pie entries from the data array
            for (int i = 0; i < data.length; i++) {
                yentries.add(new PieEntry((data[i]), tags[i]));
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
            description.setText(chartTitle);
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
    }
    /**
     *  Overloaded form of constructPieChart to be used when no data title parameter has been provided
     * @param cs - Chart stats objects contains the data to be displayed
     * @param chart - reference to the chart from the view
     * @return The PieData object for the data provided to be used for pie chart construction
     */
    public static PieData constructPieChart(ChartStats cs, PieChart chart){
        return constructPieChart(cs, chart,"");
    }
    /**
     *  A functions which take two strings arrays and refernce to the chart and returns it as a pie data to construct a pie chart
     * @param cs - Chart stats objects contains the data to be displayed
     * @param chart - reference to the chart from the view
     * @param chartTitle - Title of the chart
     * @param Colour - colour preset selction
     * @return The PieData object for the data provided to be used for pie chart construction
     */
    public static PieData constructPieChart(ChartStats cs, PieChart chart, String chartTitle,String Colour){
             if(!cs.hasData()){//Checks the have data flag is false espace functioncomputer
                return new PieData(null);

            }

            int[] data =  cs.getDataInt();
            String[] tags = cs.getTags();


            ArrayList<PieEntry> yentries = new ArrayList<>();

            //populates the arraylist with pie entries from the data array
            for (int i = 0; i < data.length; i++) {
                yentries.add(new PieEntry((data[i]), tags[i]));
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

    }

    /**
     * Generic LineChart creater
     * @param key Current X axis values
     * @param values Y axis values that are actually plotted against the X axis
     * @param chart The LineChart that is being added
     * @return
     */
    public static LineData constructLineChart(String[] key, String[] values, LineChart chart) {

        //Creates an arrayList to store the x and y axis
        float[] yAxis = stringToFloatConvertor(values);
        float[] xAxis = stringToFloatConvertor(key);
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i < key.length; i++){
            entries.add(new Entry(xAxis[i], yAxis[i]));
        }

        //create a set of data to add to the Lines to create the lines
        LineDataSet dataSet = new LineDataSet(entries, null);

        //Format the dataSet with cleaner lines and add different colours
        dataSet.setColor(ColorTemplate.rgb("C2571A"));
        dataSet.setDrawFilled(true);
        dataSet.setFillAlpha(180);
        dataSet.setFillColor(ColorTemplate.rgb("F58B4C"));
        dataSet.setValueTextSize(18);

        //Creates the curved effect on the line graph
        dataSet.setCubicIntensity(0.2f);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        //Chart formatting
        chart.setDescription(null);

        dataSet.setDrawCircles(false); //Takes the points off th graph
        dataSet.setDrawValues(false); //Takes the Values off the graph
        chart.invalidate();
        chart.setDoubleTapToZoomEnabled(false);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);


        //Format the X Axis
        XAxis xAxis1 = chart.getXAxis();
        xAxis1.setDrawAxisLine(true);
        xAxis1.setDrawGridLines(false);
        xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis1.setAxisMinimum(48);
        xAxis1.setAxisMaximum(240);

        //Format the Y Axis from the right side of the graph
        YAxis yAxis1 = chart.getAxis(YAxis.AxisDependency.RIGHT);
        yAxis1.setEnabled(false);
        yAxis1.setDrawGridLines(false);


        //Format the Y Axis from the right side of the graph
        YAxis yAxis2 = chart.getAxis(YAxis.AxisDependency.LEFT);
        yAxis2.setDrawGridLines(false);
        yAxis2.setGranularityEnabled(true);
        yAxis2.setGranularity(5);
        yAxis2.setAxisMinimum(0);




        //Add the informaiton about the lines to the chart
        return new LineData(dataSet);
    }

    /**
     * Converts a list of strings into a list of floats
     * @param info - An array of Strings
     * @return A list of floats
     */
    private static float[] stringToFloatConvertor(String[] info){
        float[] data;
        //Convert the array of strings into an array of floats
        if (info.length > 0 ){
            data = new float[info.length];
        }  else{
            return null;
        }
        for (int i = 0; i < info.length; i++){
            data[i] = Float.parseFloat(info[i]);
        }
        return data;
    }








}
