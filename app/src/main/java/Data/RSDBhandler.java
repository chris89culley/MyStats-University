package Data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

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

    ByteArrayOutputStream bo = null;
    ByteArrayInputStream bi = null;

    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    /**
     * Deafult constructor uses the context and calls the super class
     * @param context context of the page
     */
    public RSDBhandler(Context context){
        super(context,DATABASE_NAME,null,1);
        Log.d("Database","database created");
    }

    /**
     * Called when the database is created creates a create query from the handler class constants and executes them
     * @param db the db referneced
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            emptyEntries();
        }catch(Exception e){
        }
        emptyEntries();
        String CREATE_RECENTLY_VIEWED_TABLE = "CREATE TABLE "
                + TABLE_NAME
                + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NAME + " TEXT" + ")";
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
     * @throws IOException IOexception thrown when writing to the output stream
     */
    public void addEntry(Course course) throws IOException {
        int count = countEntries();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(count > 9) {
            deleteFirst();
        }
            try {
                bo = new ByteArrayOutputStream();
                out = new ObjectOutputStream(bo);
                out.writeObject(course);
                out.flush();
                String serializedEntry = bo.toString();
                bo.flush();
                values.put(COL_NAME, serializedEntry);
                db.insert(TABLE_NAME, null, values);
                Log.d("Database","Inserted "+course.getCourseName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                out.close();
                bo.close();
                db.close();
            }

    }


    public List readAll() throws IOException {
            List<Course> list = new ArrayList<Course>();

            SQLiteDatabase db = this.getReadableDatabase();

            String selectQuery = "SELECT * FROM " + TABLE_NAME;

            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) { // If data (records) available
            int nameIdx = cursor.getColumnIndex(COL_NAME);

            do {
                try {
                    bo.flush();
                    out.flush();


                    Log.d("Database",cursor.getString(nameIdx));

                    bi = new ByteArrayInputStream(bo.toByteArray());
                    in = new ObjectInputStream(bi);
                    Object obj = (Object) in.readObject();

                    list.add((Course)obj);
                    Log.d("Database",Integer.toString(cursor.getPosition()));

                }catch(Exception e){
                     e.printStackTrace();
                }
            }while (cursor.moveToNext()); // repeat until there are no more records
        }
        bi.close();
        in.close();
        db.close();
        printList(list);
        return list;
    }

    public void printList(List list){
        for(int i=0;i<list.size();i++){
            Course one = (Course) list.get(i);
            Log.d("Database","Contents "+i+":"+one.getCourseName());
        }

    }

    private int countEntries(){
        String countQuery = "SELECT * FROM "+TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int cnt = cursor.getCount();
        cursor.close();

       // Cursor cursor = db.rawQuery(countQuery, null);
        Log.d("Database","No of entries "+cnt);
        return cnt;
    }

    private void emptyEntries(){
        String deleteQuery = "DELETE FROM "+TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL(deleteQuery);
    }

    private void deleteFirst(){
        String alterQuery ="delete from " + TABLE_NAME+ " where rowid IN (Select rowid from " + TABLE_NAME + " limit 1)";

        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL(alterQuery);
    }

}
