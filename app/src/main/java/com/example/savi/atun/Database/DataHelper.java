package com.example.savi.atun.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.savi.atun.Beans.ClassInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${venkie} on ${28/1/16}.
 */
public class DataHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myattendancedb";
    public static final String TABLE_CLASS_DATA = "classdata";
    public static final int DATABASE_VERSION = 1;
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CLASS_DATA + "(srno INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT ,name TEXT,section TEXT,department TEXT,strength INTEGER,studentlist TEXT)";
    public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_CLASS_DATA;

    public static final String  CLASS_ID ="id";
    public static final String  SECTION ="section";
    public static final String  DEPARTMENT ="department";
    public static final String  STRENGTH="strength";
    public static final String  CLASS_NAME="name";
    public static final String  STUDENT_LIST ="studentlist";


    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL(DELETE_TABLE);
        //Create tables again
        onCreate(db);
    }

    public void insertAttenace(String[] statusList){

    }


    public void insertData(String id,String name,String section,String department,int strength,String studentlist) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CLASS_ID,id);
        values.put(CLASS_NAME,name);
        values.put(SECTION, section);
        values.put(DEPARTMENT,department);
        values.put(STRENGTH,strength);
        values.put(STUDENT_LIST, studentlist);


        // Inserting Row
        db.insert(TABLE_CLASS_DATA, null, values);
        db.close();

    }

   public void createClassTable(String TABLE_NAME){
       SQLiteDatabase database = getWritableDatabase();
       String  createQuery = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (srno INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,date INTEGER ,period1 TEXT,period2 TEXT,period3 TEXT,period4 TEXT,period5 TEXT)";
        database.execSQL(createQuery);
   }

   public ArrayList<ClassInfo> getData(){
       boolean cursorStatus = false ;
       ArrayList<ClassInfo> classInfoList = new ArrayList<ClassInfo>();
       SQLiteDatabase database = this.getReadableDatabase();
       String query = "SELECT * FROM "+TABLE_CLASS_DATA ;
       Cursor cursor = database.rawQuery(query, null);

       if(cursor!=null) {
           cursor.moveToFirst();
           cursorStatus =true ;
       }

       if(cursorStatus){
           do{
               String id =  cursor.getString(1);
               String name = cursor.getString(2);
               String section =  cursor.getString(3);
               String department = cursor.getString(4);
               int strength =Integer.parseInt(cursor.getString(5)) ;
               String studentListString = cursor.getString(6);
               classInfoList.add(new ClassInfo(id,name,section,department,studentListString,strength));
                cursor.moveToNext();
           }while (!cursor.isAfterLast());
       }

       return  classInfoList;
   }

}

