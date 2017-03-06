package Data;

/**
 * Created by chris on 06/03/17.
 */

public enum CourseTypes {

    FULL_TIME("fulltimecourses"),PART_TIME("parttimecourses");

    private String databaseRef;

    CourseTypes(String databaseReference){
        databaseRef = databaseReference;
    }
    public String getDatabaseRef(){
        return databaseRef;
    }
}
