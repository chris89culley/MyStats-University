package Data;

/**
 *  This is an enum depicting the different course types and their database reference point
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
