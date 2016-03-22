package com.example.savi.atun.Database;

/**
 * Created by Savi on 13-03-2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Madhur on 12/08/15.
 */
public class DBHelper extends SQLiteOpenHelper {
    String TAG = "DbHelper";

    private static final String DATABASE_NAME = "attendance.db";
    private static final int DATABASE_VERSION = 1;
    //table name and entries
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    public static  String STUDENT_DATA_TABLE ="";
    public static String STUDENT_LIST_STRING ="";

/*

    public static  String STUDENT _DATA_TABLE = "";

   public static final String ROLL_NO = "roll";
  public static final String STUDENT_NAME = "roll";


    public static final String CLASS_ID = "id";
    public static final String CLASS_NAME = "class_name";
    public static final String SECTION = "section";
    public static final String DEPARTMENT = "department";
    public static final String STRENGTH = "strength";


    static final String SQL_CREATE_ATTENDANCE_TABLE =
            "CREATE TABLE " + CAMPAIGN_DATA_TABLE + " (" +
                    "id" + " INTEGER PRIMARY KEY ," +
                    CAMPAIGN_ID + INTEGER_TYPE + COMMA_SEP +
                    CAMPAIGN_TYPE + TEXT_TYPE + COMMA_SEP +
                    CAMPAIGN_OBJECT + TEXT_TYPE + " )";


    static final String SQL_CAMPAIGN_TABLE_PAGE40 =
            "CREATE TABLE " + CAMPAIGN_PAGE40_TABLE + " (" +
                    "id" + " INTEGER PRIMARY KEY ," +
                    CAMPAIGN_ID + INTEGER_TYPE + COMMA_SEP +
                    CAMPAIGN_FLOW_ID + TEXT_TYPE + COMMA_SEP +
                    PAGE_CAT + TEXT_TYPE + COMMA_SEP +
                    PAGE_CATVALUE + TEXT_TYPE + COMMA_SEP +
                    PAGE_SUBCAT + TEXT_TYPE + COMMA_SEP +
                    PAGE_SUBCATVALUE + TEXT_TYPE + COMMA_SEP +
                    PAGE_CAT_ID + INTEGER_TYPE + COMMA_SEP +
                    PAGE_SUBCAT_ID + INTEGER_TYPE +" )";

    static final String SQL_CAMPAIGN_TABLE_PAGE40_CSAT =
            "CREATE TABLE " + CAMPAIGN_PAGE40_CSAT_TABLE + " (" +
                    "id" + " INTEGER PRIMARY KEY ," +
                    CAMPAIGN_ID + INTEGER_TYPE + COMMA_SEP +
                    CAMPAIGN_FLOW_ID + TEXT_TYPE + COMMA_SEP +
                    PAGE_CAT + TEXT_TYPE + COMMA_SEP +
                    PAGE_CATVALUE + TEXT_TYPE + COMMA_SEP +
                    PAGE_SUBCAT + TEXT_TYPE + COMMA_SEP +
                    PAGE_SUBCATVALUE + TEXT_TYPE + COMMA_SEP +
                    PAGE_CAT_ID + INTEGER_TYPE + COMMA_SEP +
                    PAGE_SUBCAT_ID + INTEGER_TYPE +" )";

    static final String SQL_CAMPAIGN_UPLOAD_TABLE =
            "CREATE TABLE " + CAMPAIGN_UPLOAD_TABLE + " (" +
                    "id" + " INTEGER PRIMARY KEY ," +
                    CAMPAIGN_ID + INTEGER_TYPE + COMMA_SEP +
                    CAMPAIGN_TYPE + TEXT_TYPE + COMMA_SEP +
                    CAMPAIGN_FLOW_ID + TEXT_TYPE + COMMA_SEP +
                    CAMPAIGN_OBJECT + TEXT_TYPE + ")";
*/


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

   //     db.execSQL(SQL_CREATE_CAMPAIGN_TABLE);
   //     db.execSQL(SQL_CAMPAIGN_TABLE_PAGE40);
    //    db.execSQL(SQL_CAMPAIGN_TABLE_PAGE40_CSAT);
   //     db.execSQL(SQL_CAMPAIGN_UPLOAD_TABLE);
   //     Log.e(TAG, SQL_CREATE_CAMPAIGN_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //onCreate(db);
    }

    //method for executing query
    public Cursor myquery(String statment) {
        Cursor myDbcur = null;

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Log.e(TAG, statment);
            myDbcur = db.rawQuery(statment, null);
            myDbcur.moveToFirst();
            Log.e(TAG, myDbcur.getCount() + "");
        } catch (Exception e) {
            Log.e(this.getClass() + "  query() ", e.toString());
        } finally {
            db.close();
        }

        return myDbcur;


    }

    private synchronized SQLiteDatabase getReadableDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db;
    }
}

