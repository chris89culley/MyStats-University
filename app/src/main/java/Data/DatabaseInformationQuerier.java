package Data;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.example.chris.mystats_univeristy.MenuViewActivity;
import com.example.chris.mystats_univeristy.NoCoursesFoundFragment;
import com.example.chris.mystats_univeristy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by chris on 06/03/17.
 * This class handles all the extraction of data from the Firebase database with an aim of providing
 * that information to the view
 */

public class DatabaseInformationQuerier {

    private DatabaseReference database; // The firebase database
    private static ArrayList<Parcelable> courseList = new ArrayList<>(); //The courses found in the current query (may not be needed)
    private Intent intent;
    private MenuViewActivity current;
    private String searchedWord = "";
    private Iterator<DataSnapshot> coursesIterator;
    private final int MAX_NO_COURSES_TO_BE_DISPLAYED = 300;


    /**
     * Constructor sets the db reference
     * @param database - The database ref
     */
    public DatabaseInformationQuerier(DatabaseReference database) {
        this.database = database;
    }


    /**
     * Sets the intent for the activity to be moved to on a successful search
     * @param t - The current intent
     */
    public void setIntent(Intent t){
        intent = t;
    }


    /**
     * Sets the current activity which is needed to move on from successful searches
     * @param cu - The current activity
     */
    public void setCurrent(MenuViewActivity cu){
        current = cu;
    }

    /**
     * Moves the to the search results page with course list compiled and searched word
     */
    private void moveToSearchResults(){
        AVLoadingIndicatorView icon = (AVLoadingIndicatorView) current.findViewById(R.id.loadingIcon);

        //This makes sure that we have search results, otherwise it shows a message to the user saying no search results have been found
        if(courseList.size() < 1){
            NoCoursesFoundFragment fragment = new NoCoursesFoundFragment();
            fragment.show(current.getFragmentManager(), "");
            icon.hide();
            return;
        }

        //Packs up the search results (list of courses) and the searched word for the intent
        intent.putParcelableArrayListExtra("searchResults" , courseList);
        intent.putExtra("searchedName" , searchedWord);

        //Hides the loading icon
        if(icon != null)
        icon.hide();

        this.current.startActivity(intent);
    }


    /**
     * This method is used because firebase is Async, this method extracts the information needed from the
     * course object creating a course object which can then be updated with the relevant details.
     * Since there was an issue with courses including year abroad causing duplicates we remove them at this point
     * until the issue is fixed db side
     *
     * @param courses - The datasnapshot containing all the information about the courses
     * @param coursetype - The type of courses that are being searched
     */


    private void collectCourses(DataSnapshot courses, CourseTypes coursetype){
        courseList.clear();
        int count = 0;
        Set<String> courseunimatch = new HashSet<>();
        Iterator<DataSnapshot> data = courses.getChildren().iterator();
        while(data.hasNext() && count < MAX_NO_COURSES_TO_BE_DISPLAYED){
            DataSnapshot next = data.next();
            Course course = next.getValue(Course.class);
            if(course.hasStatistics()){
                String courseuni = course.getFullCourseName() + course.getUniversityWhereCourseIsTaught();
                if(!courseunimatch.contains(courseuni)) {
                    courseList.add(course);
                    count++;
                    courseunimatch.add(courseuni);
                }
            }
        }
        coursesIterator = data;
        moveToSearchResults();
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

                searchedWord = name;
                Query query =  courseNameQuery(name, coursetype);
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

    /**
     *
     * This method is used because firebase is Async, this method extracts the information needed from the
     * course object creating a course object which can then be updated with the relevant details.
     * Since there was an issue with courses including year abroad causing duplicates we remove them at this point
     * until the issue is fixed db side, this method additionally filters courses to only those who's value
     * of the key matches the value passed in
     *
     * @param datain - The datasnapshot that is to be filtered
     * @param courseType - The type of course (ie full or part time)
     * @param keyName - The key that will filter the data
     * @param valueToBeMatched - The value that if matched will be allowed to be sent to the view
     */
    public void collectFilteredCourses(DataSnapshot datain, CourseTypes courseType, String keyName, String valueToBeMatched){
        courseList.clear();
        int count = 0;
        Set<String> courseunimatch = new HashSet<>();
        Iterator<DataSnapshot> data = datain.getChildren().iterator();
        while(data.hasNext() && count < MAX_NO_COURSES_TO_BE_DISPLAYED){
            DataSnapshot next = data.next();
            count++;
            if(next.child(keyName).getValue().toString().startsWith(getStartAndFinishSearchIndexes(valueToBeMatched)[0])){
                Course course = next.getValue(Course.class);
                String courseuni = course.getFullCourseName() + course.getUniversityWhereCourseIsTaught();

                if(!courseunimatch.contains(courseuni)) {
                    courseList.add(course);
                    courseunimatch.add(courseuni);
                }
            }
            //This is where the method is needed to pass the course data to the view
        }

    }

    /**
     * This method gives flexibility to the users searches, it does this by returning start and end search
     * words which can be applied in a database search meaning that close misses (ie putting in exact starts of the word
     * but missing the tail or specialisation ) will be picked up
     * @param current - The word to be amended
     * @return - A two piece array with the start word indexed to 0 and the end word indexed to 1
     */
    public String[] getStartAndFinishSearchIndexes(String current){
        int lengthOfString = current.length();
        String starthere = Character.toUpperCase(current.charAt(0)) + current.substring(1);
        String end = current;
        if(lengthOfString > 3){
          end = current.substring(0,lengthOfString-1 ) + (char) (current.charAt(lengthOfString-1)+1);
        }

        String [] startend = {starthere, end};
        return startend;
    }

    /**
     * This method queries the database with the passed course name and course type, it also gives an allowence of missing the
     * end of the course name
     * @param courseName - The course to be searched
     * @param coursetype - The type of course to be searched
     * @return - A query to by applied to the database
     */
    private  Query courseNameQuery(String courseName, CourseTypes coursetype){

        String [] searchWordCritera = getStartAndFinishSearchIndexes(courseName);
        return database.child(coursetype.getDatabaseRef()).orderByChild("TITLE").startAt(searchWordCritera[0]).endAt(searchWordCritera[1]).limitToFirst(500);

    }

    /**
     * This method collects the courses that result from a query and filters the courses so that
     * only those with a location (prn) that matches those passed in are kept and added to the list of courses
     * is one list
     * @param dataSnapshot - The data snapshot of courses
     * @param keys - The locations that are acceptable for the courses
     */
    private void collectAndCheckLocation(DataSnapshot dataSnapshot, Set<String> keys){
        courseList.clear();
        Iterator<DataSnapshot> courses = dataSnapshot.getChildren().iterator();
        int count = 0;
        while(courses.hasNext() && count < MAX_NO_COURSES_TO_BE_DISPLAYED){
            count++;
            DataSnapshot course = courses.next();
            if(keys.contains(course.child("UKPRN").getValue())){
                Course c = course.getValue(Course.class);
                if(c.hasStatistics())
                courseList.add(course.getValue(Course.class));
            }
        }
        moveToSearchResults();
    }

    /**
     * This querey allows the user to search for courses by name around a set of ukprn keys which relate to
     * specific universities
     * @param coursename - the name of the course being searched
     * @param keys - the set of university ukprn keys
     * @param type - the type of course being searched
     */
    public void searchByCourseLocation(String coursename , final Set<String> keys, CourseTypes type){

        searchedWord = coursename;
        Query query = courseNameQuery(coursename, type);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                collectAndCheckLocation(dataSnapshot, keys);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    /**
     * This method retrieves from the database all close match courses to the passed in course and university name
     * these are the packaged as course objects and sent to the view in the collect filtered courses method
     * @param courseName - The name of the course to be searched
     * @param universityName - The name of the university to search by
     * @param coursetype - The type of course searched
     */
    public void getACourseByCoursenameAndUniversityName(String courseName, final String universityName, final CourseTypes coursetype){

                searchedWord = courseName;
                Query query =  courseNameQuery(courseName, coursetype);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        collectFilteredCourses(dataSnapshot,coursetype, "NAME", universityName);
                        }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }


    }
