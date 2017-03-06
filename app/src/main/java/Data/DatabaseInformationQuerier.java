package Data;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by chris on 06/03/17.
 */

public class DatabaseInformationQuerier {

    DatabaseReference database;
    static ArrayList<CourseDetail> courseList = new ArrayList<>();
    public DatabaseInformationQuerier(DatabaseReference database) {
        this.database = database;
    }

    public ArrayList<CourseDetail> getCourses(){
        return courseList;
    }

    /**
     * This method retrieves the university name from the database using the passed university code
     * @param course - The course that we are trying to get the information for
     * @param uniCode - The code relating to the university
     */
    public void addUniNameToCourse(final CourseDetail course, String uniCode){
        Query query = database.child("universities").orderByChild("UKPRN").equalTo(Integer.valueOf(uniCode));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Iterator<DataSnapshot> children = dataSnapshot.getChildren().iterator();
                    DataSnapshot next = children.next();
                     course.setUniversityName(next.getKey().toString());
                    course.showDetails();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //This is where the method goes to update on the screen the course name Dan
    }


    /**
     * This method is used because firebase is Async, this method extracts the information needed from the
     * course object creating a courseDetail object which can then be updated with the relevant details
     * since the course location is held in a different database (at the moment) this is retrieved elsewhere
     *
     * @param courses
     * @param coursetype
     */
    private void collectCourses(DataSnapshot courses, CourseTypes coursetype){
        Iterator<DataSnapshot> data = courses.getChildren().iterator();
        while(data.hasNext()){
            DataSnapshot next = data.next();
            CourseDetail course = new CourseDetail(next.child("TITLE").getValue().toString(),
                    next.child("KISCOURSEID").getValue().toString(),
                    "null",
                    coursetype.toString());
            addUniNameToCourse(course, next.child("UKPRN").getValue().toString());
            courseList.add(course);


        }
    }

    /**
     * This method gets all the courses in the database that match the passed in name and coursetype
     *
     * @param name  - The name of the course that is being searched for
     * @param coursetype - The course type (taken from the enum of CourseTypes)
     * @return - An DataSnapshot object containing all matches
     *
     */
    public void getAllCoursesByCourseName(String name, final CourseTypes coursetype){
                Query query =  database.child(coursetype.getDatabaseRef()).orderByChild("TITLE").equalTo(name);
                ArrayList<CourseDetail> test = new ArrayList<>();
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        collectCourses(dataSnapshot, coursetype);
                        }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }




    }
