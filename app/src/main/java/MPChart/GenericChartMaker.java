package MPChart;

import android.graphics.Color;
import android.graphics.Typeface;

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
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

import Data.ChartStats;
import Utilities.Colours;

//import com.github.mikephil.charting.components.xAxis;


/**
 * Created by Jack on 06/03/2017.
 *
 * This class handles the generic building of charts
 *
 */

public class GenericChartMaker {


    /**
     *  A functions which take two strings arrays and reference to the chart and returns it as a bar data to construct a bar chart
     * @param cs- Chart stats objects contains the data to be displayed i
     * @param chart - reference to the chart from the view
     * @param chartTitle - Title of the chart
     * @return The BarData object for the data provided to be used for bar chart construction
     */
    public static BarData constructBarChart(ChartStats cs, BarChart chart, String chartTitle){
        if(!cs.hasData()){//Checks the have data flag is false espace function computer
            return new BarData();
        }

        int[] data = cs.getDataInt();
        String[] tags = cs.getTags();


        //new arraylists for the data entries and the text labels for them
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        //add the data and the labsls to the arraylists
       for (int i = 0; i < data.length; i++) {
                    entries.add(new BarEntry(i, data[i]));
                    labels.add(tags[i]);
            }



       BarDataSet barDataSet = new BarDataSet(entries, chartTitle); //Creating a dataSet for the chart


        //Sets up the colours to be used by the charts
        int[] Colors = { Colours.BLUE.getColor(),
                Colours.GREEN_SHEEN.getColor() ,
                Colours.LIGHT_BROWN.getColor(),
                Colours.LIGHT_YELLOW.getColor(),
                Colours.PURPLE.getColor(),
                Colours.MUMMYS_TOMB.getColor()};
        barDataSet.setColors(Colors);

        barDataSet.setValueTextSize(20.0f);

        //Creates a format whereby the value has a percent sign at the end
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return String.valueOf(value) + "%";
            }
        });

            chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));//Setting the X axis labels
        barChartXaxisSettings(chart,labels);
        barChartStyleSettigs(chart);
        BarData theData = new BarData(barDataSet);
        theData.setBarWidth(0.4f);
        return theData;
    }
    /**
     * method that changes the X axis style settings of the bar chart
     * @param chart having its settings modified
     * @param labels Arraylist contains the labels for the bar chart data
     */
    private static void barChartXaxisSettings(BarChart chart,ArrayList<String> labels){

        XAxis xaxis = chart.getXAxis(); // gets the X axis of the chart
        xaxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);//Moves the labels to the top of the x axis


        xaxis.setCenterAxisLabels(true);
        xaxis.setDrawGridLines(false);

        xaxis.setDrawGridLines(false);
        xaxis.setLabelCount(labels.size()); //sets the labels amounts to the to the number of labels in the arraylist -so none are cut off
        xaxis.setDrawAxisLine(false);

    }

    /**
     * method that changes the style settings of the bar chart
     * @param chart having its settings modified
     */
    private static void barChartStyleSettigs(BarChart chart){
        //axis chart and data display settings being altered
        chart.setDrawGridBackground(false);
        chart.setDrawMarkers(false);
        chart.setFitBars(false);
        chart.setDrawValueAboveBar(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisLeft().setAxisLineWidth(1.0f);
        chart.getAxisLeft().setStartAtZero(true);
        Description emptyDescription = new Description();
        emptyDescription.setText("");
        chart.setDescription(emptyDescription);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setEnabled(false);

        chart.getLegend().setEnabled(false);

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
 
    public static PieData constructPieChart(ChartStats cs, PieChart chart, String chartTitle) {
        if (!cs.hasData()) {//Checks the have data flag is false espace functioncomputer

            return new PieData(null);
        }

        int[] data = cs.getDataInt();
        String[] tags = cs.getTags();

        ArrayList<PieEntry> yentries = new ArrayList<>();

        //populates the arraylist with pie entries from the data array
        for (int i = 0; i < data.length; i++) {
            yentries.add(new PieEntry((data[i]), tags[i]));
        }

        setPieChartDisplay(chart);

        //Creating a dataSet for the chart
        PieDataSet pieDataSet = new PieDataSet(yentries, "");
        pieDataSet.setSliceSpace(4);
        pieDataSet.setValueTextSize(16);


        //Colours array in the form of hex codes
        int[] Colors = {ColorTemplate.rgb("2c7bb5"), ColorTemplate.rgb("ff3300"), ColorTemplate.rgb("C781B8"), ColorTemplate.rgb("ff9900")};

        //Setting the colours of the data set
        pieDataSet.setColors(Colors);

        setPieDescription(chart,chartTitle);

        setPieLegend(chart);

        chart.setDrawSliceText(false);


        return setPiedata(pieDataSet);
    }

    /**
     * Method which sets turns the data set into chart data
     * @param chart - reference to the chart from the view
     */
    public static void setPieChartDisplay(PieChart chart){
        //piechart display settings
        chart.setCenterText("%");
        chart.setCenterTextSize(20f);
        chart.setHoleRadius(40f);
        chart.setHighlightPerTapEnabled(true);
    }

    /**
     * Method which sets turns the data set into chart data
     * @param pieDataSet - data set to be used by the chart
     */
    public static PieData setPiedata(PieDataSet pieDataSet){
        //changing the dataset settings
        PieData theData = new PieData(pieDataSet);
        theData.setValueTextColor(Color.WHITE);
        theData.setValueTypeface(Typeface.DEFAULT_BOLD);
        return theData;
    }

    /**
     * Method which sets the legend settings for the pie chart
     * @param chart - reference to the chart from the view
     */
    public static void setPieLegend(PieChart chart){

        //Changing legend potion and settings
        Legend l = chart.getLegend();
        l.setTextColor(ColorTemplate.rgb("3B322C"));
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);

        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
    }

    /**
     * Method which sets the description settings and content for the pie chart
     * @param chart - reference to the chart from the view
     * @param chartTitle - String title of the chart
     */
    public static void setPieDescription(PieChart chart,String chartTitle){
        //Changing description settings
        Description description = new Description();
        description.setText(chartTitle);
        description.setPosition(0f, 0f);
        chart.setDescription(description);
        chart.setDrawEntryLabels(true);
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
        dataSet = setLineGraphData(dataSet);
        dataSet = setGraphCurve(dataSet);
        chart = setLineChartData(chart);

        XAxis xAxis1 = chart.getXAxis();
        setxAxisfeatures(xAxis1);

        YAxis yAxisRightSide = chart.getAxis(YAxis.AxisDependency.RIGHT);
        setUpYAxisRightSide(yAxisRightSide);

        YAxis yAxisLeftSide = chart.getAxis(YAxis.AxisDependency.LEFT);
        setUpYAXaisLeft(yAxisLeftSide);

        Legend legend = chart.getLegend();
        setLegendFeatures(legend);

        return new LineData(dataSet);
}

    /**
     * Method to turn the legend off
     * @param legend The legend that you want turning off
     * @return the modified legend information
     */
    private static Legend setLegendFeatures(Legend legend) {
        legend.setEnabled(false);
        return legend;
    }

    /**
     * Sets up the parameters for the Graphs right side yAXis
     * @param yAxisRightSide The right hand Y axis you want modifying
     * @return The modified yAxis
     */
    private static YAxis setUpYAxisRightSide(YAxis yAxisRightSide) {
        yAxisRightSide.setEnabled(false);
        yAxisRightSide.setDrawGridLines(false);
        return yAxisRightSide;
    }

    /**
     * Sets up the Y Axis left side
     * @param yAxisLeftSide The yAxis you want Modifying
     * @return the modified yAxis
     */
    private static YAxis setUpYAXaisLeft(YAxis yAxisLeftSide) {
        yAxisLeftSide.setDrawGridLines(false);
        yAxisLeftSide.setGranularityEnabled(true);
        yAxisLeftSide.setGranularity(5); //Sets how much the graph increments by
        yAxisLeftSide.setAxisMinimum(0);
        return yAxisLeftSide;
    }

    /**
     * Sets up the details on the X Axis
     * @param xAxis1 The xXaxis of the graph you want to modify
     * @return The modified xAxis
     */
    private static XAxis setxAxisfeatures(XAxis xAxis1) {
        xAxis1.setDrawAxisLine(true);
        xAxis1.setDrawGridLines(false);
        xAxis1.disableAxisLineDashedLine();
        xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis1.setAxisMinimum(48); //Sets the minimun amount of the xAxis to 48 as this is the lowest that the informaition gives
        xAxis1.setAxisMaximum(240);//Sets the Graphs max to 240 as it is the highest that we recieve.
        return xAxis1;

    }


    /**
     * Sets the Line Chart to have zoom and other properties.
     * @param chart takes in the LineChart data that you want to be modified
     * @return the modified LineCahrt properties
     */
    private static LineChart setLineChartData(LineChart chart) {
        chart.setDescription(null);
        chart.invalidate(); // Calls the graph to refresh when viewed
        chart.setDoubleTapToZoomEnabled(false);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        return chart;
    }

    /**
     * Set the LineGraph to have a curved graph instead of straight lines
     * @param dataSet The lineDataSet
     *@return the line modified properties of the dataSet
     */
    private static LineDataSet setGraphCurve(LineDataSet dataSet) {
        dataSet.setCubicIntensity(0.2f); //Set the amount the graph curves
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setDrawHorizontalHighlightIndicator(false);
        dataSet.setDrawVerticalHighlightIndicator(false);
        return dataSet;
    }

    /**
     * Converts a list of strings into a list of floats
     * @param info - An array of Strings
     * @return A list of floats
     */
    private static float[] stringToFloatConvertor(String[] info){
        float[] data;
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

    /**
     * Sets the Lingraph chart colours and
     * @param dataSet The LineDataSet that you want to modify
     * @return the modified LineData to put into the chart
     */
    private static LineDataSet setLineGraphData(LineDataSet dataSet)
    {
        dataSet.setColor(ColorTemplate.rgb("F26D21"));
        dataSet.setDrawFilled(true);
        dataSet.setFillAlpha(240);
        dataSet.setFillColor(ColorTemplate.rgb("F26D21"));
        dataSet.setValueTextSize(18);
        dataSet.setDrawCircles(false); //Takes the points off th graph
        dataSet.setDrawValues(false); //Takes the Values off the graph
        return dataSet;
    }
}
