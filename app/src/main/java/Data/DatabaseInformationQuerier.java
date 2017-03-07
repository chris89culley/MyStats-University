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
 * This class handles all the extraction of data from the Firebase database with an aim of providing
 * that information to the view
 */

public class DatabaseInformationQuerier {

    DatabaseReference database; // The firebase database
    static ArrayList<Course> courseList = new ArrayList<>(); //The courses found in the current query (may not be needed)

    public DatabaseInformationQuerier(DatabaseReference database) {
        this.database = database;
    }



    /**
     * This method retrieves the university name from the database using the passed university code - we need
     * to update the front end from within either here or the collect courses method do the async nature of Firebase
     * @param course - The course that we are trying to get the information for
     * @param uniCode - The code relating to the university
     */
    public void addUniNameToCourse(final Course course, String uniCode){
        Query query = database.child("universities").orderByChild("UKPRN").equalTo(Integer.valueOf(uniCode));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Iterator<DataSnapshot> children = dataSnapshot.getChildren().iterator();
                    DataSnapshot next = children.next();

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    /**
     * This method is used because firebase is Async, this method extracts the information needed from the
     * course object creating a course object which can then be updated with the relevant details
     *
     * @param courses - The datasnapshot containing all the information about the courses
     * @param coursetype - The type of courses that are being searched
     */
    private void collectCourses(DataSnapshot courses, CourseTypes coursetype){
        courseList.clear();
        Iterator<DataSnapshot> data = courses.getChildren().iterator();
        while(data.hasNext()){
            DataSnapshot next = data.next();
            Course course = next.getValue(Course.class);
            courseList.add(course);
            //This is where the method is needed to pass the course data to the view
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


    /**
     * This method gets a course from the database by looking up the key passed in (id)
     * @param id - The key where the course is held in the database
     * @param coursetype - The type of course (ie part time/full time)
     */
    public void getAParticularCourseByID(String id, final CourseTypes coursetype){
        Query query =  database.child(coursetype.getDatabaseRef()).child(id);
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
