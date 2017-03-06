package Data;

import android.util.Log;

/**
 * Created by chris on 06/03/17.
 */

public class CourseDetail {

    String name, id, universityname, courseType;

    CourseDetail(String name, String id, String universityname, String courseType){
        this.name = name;
        this.id = id;
        this.universityname = universityname;
        this.courseType = courseType;


    }

    public void showDetails(){
        Log.d("courseDetailAdding ", name + "    " + id + "   " + universityname + "    " + courseType);
    }
    public void setUniversityName(String uniname){
        universityname = uniname;

    }
}
