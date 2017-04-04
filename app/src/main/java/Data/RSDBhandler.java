package Data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

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
    ObjectOutputStream out = null;

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
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            bo = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bo);
            out.writeObject(course);
            out.flush();
            String serializedEntry = bo.toString();
            values.put(COL_NAME, serializedEntry);
            db.insert(TABLE_NAME, null, values);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            out.close();
            bo.close();
            db.close();
        }
    }


    public void getAll(){
     //Should return a list of all course objects once deserialized


    }

}
