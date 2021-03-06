package Data;


import android.util.Log;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Jack on 28/03/2017.
 * A class to store chart stats
 */

public class ChartStats {
    private String[] tags;
    private String[] data;
    private int[] dataInt;
    private String chartTitle;
    private boolean hasData;

    /**
     * Constructor for the ChartStats object tags an array of strings for the fields of the data, another for the titles of the data and a title of the chart
     * @param tags String[] containing the data titles
     * @param data String[] containing the data
     * @param chartTitle String containing the title of the chart
     */

    public ChartStats(String[] data,String[] tags,String chartTitle,ChartType type){
        if (tags.length > 0 && data.length > 0){ //If Data exists in the passed arrays add it to the objects arrays
            this.tags  = tags;
            this.data = data;
            try{ // Try complete the data set and then create an integer arry for the Generic chart functions
                completeDataSet(type);
                dataToInt();
                hasData = true; //No problems
            } catch (Exception e){ //Catch any exceptions
                e.printStackTrace();
                hasData = false; //If problems use the has data flag to signal this
            }
            hasData = true;
        } else{ // if it does'nt initlize reccord 0 as N/A (has daat used as escape condition in validation)
            this.tags = new String[1];
            tags[0] = "N/A";
            this.data = new String[1];
            data[0] = "N/A";
            hasData = false;
        }
        this.chartTitle = chartTitle;
    }

    /**
     * Accessor method for the title of the chart
     * @return  String ChartTitle
     */
    public String getChartTitle() {
        return chartTitle;
    }
    /**
     * Accessor method for the tags
     * @return  String[] tags
     */
    public String[] getTags() {
        return tags;
    }
    /**
     * Accessor method for the data as ints
     * @return  int[] Data
     */
    public int[] getDataInt() {
        return dataInt;
    }
    /**
     * Accessor method for the data
     * @return  String[] Data
     */
    public String[] getData() {
        return data;
    }
    /**
     * Accessor method for the hasData flag
     * @return  hasData flag
     */
    public boolean hasData(){
        return hasData;
    }

    /**
     * A method that purges the data stored by removing empty entries and their tags,
     */
    private void completeDataSet(ChartType type){
        ArrayList<String> dataAL = new ArrayList<String>(Arrays.asList(data));
        ArrayList<String> tagsAL = new ArrayList<String>(Arrays.asList(tags));
        String str;

        emptyZeroEntries(dataAL,tagsAL);

        fillEntries(type,dataAL,tagsAL);

        //Set the arrylists into the arrays
        data = new String[dataAL.size()];
        data = dataAL.toArray(data);

        tags = new String[tagsAL.size()];
        tags = tagsAL.toArray(tags);
    }

    /**
     * Removes all the zero entries from the data set
     * @param dataAL an arraylist that contains the data points
     * @param tagsAL an arrayList that contains the tags for the data points
     */
    private void emptyZeroEntries(ArrayList<String> dataAL,ArrayList<String> tagsAL){
        for(int i = 0; i < dataAL.size() ;i++ ){ // removes any empty entries

            if (Objects.equals(dataAL.get(i),"")|| Objects.equals(dataAL.get(i),"0") || Objects.equals(dataAL.get(i)," " )) {
                dataAL.remove(i);
                tagsAL.remove(i);
            }
        }
    }

    /**
     * adds dummy entries to incomplete data sets
     * @param type  the type of chart
     * @param dataAL an arraylist that contains the data points
     * @param tagsAL an arrayList that contains the tags for the data points
     */
    private void fillEntries(ChartType type,ArrayList<String> dataAL,ArrayList<String> tagsAL){
        if (type == ChartType.PIE) {
            int percentage = 0;
            for (int i = 0; i < dataAL.size(); i++) { // add the values stored see if the data is complete
                percentage += Integer.parseInt(dataAL.get(i));
            }
            if (percentage < 100) { // if the data isnt complete add an other entry
                dataAL.add(Integer.toString(100 - percentage));
                tagsAL.add("Other");
            }
        }
    }

    /**
     * Store the data as integers rather than strings
     */
    private void dataToInt(){
        dataInt = new int[data.length];
        for (int i =0; data.length > i;i++){
            dataInt[i] = Integer.parseInt(data[i]);
        }
    }


}
