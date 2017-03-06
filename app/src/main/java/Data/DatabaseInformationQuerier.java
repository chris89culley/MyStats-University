package Data;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by chris on 06/03/17.
 */

public class DatabaseInformationQuerier {

    DatabaseReference database;
    ArrayList<Course> courses = new ArrayList<>();
    public DatabaseInformationQuerier(DatabaseReference database){
        this.database = database;
    }

    /**
     * This method gets all the courses in the database that match the passed in name and coursetype
     *
     * @param name  - The name of the course that is being searched for
     * @param coursetype - The course type (taken from the enum of CourseTypes)
     * @return - An DataSnapshot object containing all matches
     *
     */
    public void getAllCoursesByCourseName(String name, CourseTypes coursetype){
                Query query =  database.child(coursetype.getDatabaseRef()).orderByChild("TITLE").equalTo(name);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> data = dataSnapshot.getChildren().iterator();
                        while(data.hasNext()){
                            DataSnapshot next = data.next();
                           // courses.add(new Course(next.hasChild("TITLE"), next.get)))
                            Log.d("out " , next.toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        }


    }
