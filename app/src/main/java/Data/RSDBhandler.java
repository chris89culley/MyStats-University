package Data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 04/04/2017.
 * Recent Searches database handler
 */

public class RSDBhandler extends SQLiteOpenHelper {
    // Database Name
    private static final String DATABASE_NAME = "recentSearchesDatabase.db";
    // Table Name
    private static final String TABLE_NAME = "lastViewed";
    //Id column name
    private static final String COL_ID = "_id";
    //Name column name
    private static final String COL_NAME = "courseinfo";

    private Gson gson = new Gson();

    SQLiteDatabase db;

    /**
     * Deafult constructor uses the context and calls the super class
     * @param context context of the page
     */
    public RSDBhandler(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    /**
     * Called when the database is created creates a create query from the handler class constants and executes them
     * @param db the db referenced
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //when created empty the db
        try{
            emptyEntries();
        }catch(Exception e){
        }

        // Creating the query string for a recently viewed table
        String CREATE_RECENTLY_VIEWED_TABLE = "CREATE TABLE "
                + TABLE_NAME
                + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NAME + " TEXT" + ")";
        //executing the query for creating the recently viewed table
        db.execSQL(CREATE_RECENTLY_VIEWED_TABLE);

        Log.d("Database","database created");
    }

    /**
     * A method used to update db scehma
     * @param db database being referenced
     * @param oldVersion the version of the old database
     * @param newVersion the new versions version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     *  A method which adds a serialized course object to the database
     * @param course The course object being added
     */
    public void addEntry(Course course){
        //count entries - returns num in the db
        int count = countEntries();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //if the db has over 10 entries remove the first one and add one at the end
        String json = gson.toJson(course);
        if(count > 9) {
            deleteFirst();
        }
        try {
            if (checkIfExists(json) == false){
                values.put(COL_NAME, json);
                db.insert(TABLE_NAME, null, values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Reads all entries in the table and returns them a s a list
     * @return List of Course objects
     */
    public ArrayList<Course> readAll() {
        ArrayList<Course> list = new ArrayList<Course>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        readEntries(cursor,list);

        printList(list);
        return list;
    }

    /**
     * Read the entries one at a time using the cursor and return them as course objects
     * @param cursor cursor used to traverse the
     * @param list course object list
     */
    private void readEntries( Cursor cursor,ArrayList<Course> list){
        if (cursor.moveToFirst()) { // If data (records) available
            int nameIdx = cursor.getColumnIndex(COL_NAME);
            do {
                try {
                    String json = cursor.getString(nameIdx);
                    Course object = gson.fromJson(json, Course.class);
                    list.add(object);
                    Log.d("Database",Integer.toString(cursor.getPosition()));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }while (cursor.moveToNext()); // repeat until there are no more records
        }
    }

    /**
     * prints all entries in the list used for debugging
     * @param list - the list of items to be printed
     */
    public void printList(List list){
        //list all entries ihn the db through logging
        for(int i=0;i<list.size();i++){
            Log.d("Database","Contents "+i+":"+((Course) list.get(i)).getCourseName());
        }

    }

    /**
     * counts the number of data entries in table
     * @return number of entries in the table
     */
    private int countEntries(){
        // Creating the query string for selecting the db entries
        String countQuery = "SELECT * FROM "+TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        //Executing the select query

        Cursor cursor = db.rawQuery(countQuery, null);
        //count the results from the select
        int cnt = cursor.getCount();
        cursor.close();

        Log.d("Database","No of entries "+cnt);
        return cnt;
    }

    /**
     * Empties all the entries in the table
     */
    public void emptyEntries(){
        //creating the query string for deleting all the entries
        String deleteQuery = "DELETE FROM "+TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        //Executing the query for deleting the entires

        db.execSQL(deleteQuery);
    }

    /**
     * deletes the first entry in the table
     */
    private void deleteFirst(){
        //creating the query string for deletingthe first entry
        String alterQuery ="delete from " + TABLE_NAME+ " where rowid IN (Select rowid from " + TABLE_NAME + " limit 1)";

        SQLiteDatabase db = this.getReadableDatabase();
        //Executing the query for deleting the first entry

        db.execSQL(alterQuery);
    }

    /**
     * Check if a reccord exists in the table
     * @param fieldValue - the value being searched for
     */
    private boolean checkIfExists(String fieldValue){
        boolean ret = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "Select * from " + TABLE_NAME + " where " + COL_NAME + " = '" + fieldValue+"'";
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.getCount() > 0){
            cursor.close();
            ret = true;
        }
        cursor.close();
        return ret;
    }

}
