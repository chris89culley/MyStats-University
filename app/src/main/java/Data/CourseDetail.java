package Data;

import android.util.Log;

/**
 * Createdby chris on 06/03/17.
 * This class creates the basic details in an object that can passed to the view for extraction
 */

public class CourseDetail {

    String name, id, universityname, courseType;

    CourseDetail(String name, String id, String universityname, String courseType){
        this.name = name;
        this.id = id;
        this.universityname = universityname;
        this.courseType = courseType;


    }

    /**
     * prints to the monitor the object details
     */
    public void showDetails(){
        Log.d("courseDetailAdding ", name + "    " + id + "   " + universityname + "    " + courseType);
    }

    /**
     * Updates the university name of the object to the passed value
     * @param uniname
     */
    public void setUniversityName(String uniname){
        universityname = uniname;

    }
}
