package com.example.savi.atun.Database;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savi.atun.Beans.ClassInfo;
import com.example.savi.atun.Constatnts.Constants;
import com.example.savi.atun.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class DataHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myattendancedbv1";
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
    SQLiteDatabase sqLiteDatabase = getWritableDatabase();
    Context context ;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context ;
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

    public void insertAttenace(String statusList){
        boolean status[] = {false,false,false};
        createTableifNotExist();
        status = checkslotsStatus();
        slotDialog(status, statusList);
    }


    private void createTableifNotExist() {
        createClassTable(Constants.updatedClassID);
    }

    private boolean[] checkslotsStatus() {
        boolean status[] = {false,false,false};
        final String TABLE = Constants.updatedClassID ;

        String date = getdate();
        Log.i("DATE", date + "");
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE,null,"date =?",new String[]{date},null,null,null);
        cursor.moveToFirst();

        if(cursor.getString(2)==null)
            status[0]=false;
        else
            status[0]=true;

        if(cursor.getString(3)==null)
            status[1]=false;
        else
            status[1]=true;

        if(cursor.getString(4)==null)
            status[2]=false;
        else
            status[2]=true;

        return status;
    }


    public HashMap<String,String>
    checkslotstatusgetData(String date,String month ,String year ,String table){
        String period1="",period2="",period3="" ;
        HashMap<String,String> map = new HashMap<>();
        boolean status[]={false,false,false};
        SQLiteDatabase database = getReadableDatabase() ;

        if(Integer.parseInt(date)<10){
            date = "0"+date;
        }

        Cursor cursor = database.rawQuery("select * from " + table
                + " where xdate = ? AND xmonth = ? AND xyear = ?", new String[]{date,month,year});

        if(!cursor.moveToFirst())
            return map;

        if(cursor.getString(2)==null)
        {  status[0]=false;}
        else{
            status[0]=true;
            period1 = cursor.getString(2);
        }

        if(cursor.getString(3)==null)
        { status[1]=false;}
        else {
            status[1] = true;
            period2 = cursor.getString(3);
        }

        if(cursor.getString(4)==null)
        { status[2]=false;}
        else {
            status[2] = true;
            period3 = cursor.getString(4);
        }

        map.put("period1",period1);
        map.put("period2",period2);
        map.put("period3",period3);

        return map;

    }



    public void slotDialog(final boolean[] status, final String statusList) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_select_slot);
        final TextView textView_slot1Status = (TextView) dialog.findViewById(R.id.textviewe_slot1Status);
        final TextView textView_slot2Status = (TextView) dialog.findViewById(R.id.textviewe_slot2Status);
        final TextView textView_slot3Status = (TextView) dialog.findViewById(R.id.textviewe_slot3Status);
        final RadioButton radioButton_slot1 = (RadioButton) dialog.findViewById(R.id.radiobtn1);
        final RadioButton radioButton_slot2 = (RadioButton) dialog.findViewById(R.id.radiobtn2);
        final RadioButton radioButton_slot3 = (RadioButton) dialog.findViewById(R.id.radiobtn3);
        Button button_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        final Button button_save = (Button) dialog.findViewById(R.id.btn_save);
        if (status[0]) {
            textView_slot1Status.setText("FULL");
            textView_slot1Status.setTextColor(Color.RED);
        } else {
            textView_slot1Status.setText("EMPTY");
            textView_slot1Status.setTextColor(Color.GREEN);
        }
        if (status[1]) {
            textView_slot2Status.setText("FULL");
            textView_slot2Status.setTextColor(Color.RED);
        } else {
            textView_slot2Status.setText("EMPTY");
            textView_slot2Status.setTextColor(Color.GREEN);
        }
        if (status[2]) {
            textView_slot3Status.setText("FULL");
            textView_slot3Status.setTextColor(Color.RED);
        } else {
            textView_slot3Status.setText("EMPTY");
            textView_slot3Status.setTextColor(Color.GREEN);

        }

        radioButton_slot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status[0])
                    button_save.setText("Replace");
                else
                    button_save.setText("Save");
                radioButton_slot2.setChecked(false);
                radioButton_slot3.setChecked(false);
                radioButton_slot1.setChecked(true);

            }
        });

        radioButton_slot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status[1])
                    button_save.setText("Replace");
                else
                    button_save.setText("Save");
                radioButton_slot1.setChecked(false);
                radioButton_slot3.setChecked(false);
                radioButton_slot2.setChecked(true);


            }
        });

        radioButton_slot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status[2])
                    button_save.setText("Replace");
                else
                    button_save.setText("Save");
                radioButton_slot1.setChecked(false);
                radioButton_slot2.setChecked(false);
                radioButton_slot3.setChecked(true);
            }
        });


        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textviewStatus1 = textView_slot1Status.getText().toString();
                String textviewStatus2 = textView_slot2Status.getText().toString();
                String textviewStatus3 = textView_slot3Status.getText().toString();

                if (radioButton_slot1.isChecked()) {
                    if (textviewStatus1.equals("FULL")) {
                        confirmationDialog("Data in slot 1 will will be replaced.Go ahead ?", "period1", statusList);

                    } else {
                        insertdataInperiod("period1", statusList);
                    }
                }
                if (radioButton_slot2.isChecked()) {
                    if (textviewStatus2.equals("FULL")) {
                        confirmationDialog("Data in slot 2 will will be replaced.Go ahead ?", "period2", statusList);

                    } else {
                        insertdataInperiod("period2", statusList);
                    }
                }
                if (radioButton_slot3.isChecked()) {
                    if (textviewStatus3.equals("FULL")) {
                        confirmationDialog("Data in slot 3 will will be replaced.Go ahead ?", "period3", statusList);
                    } else {
                        insertdataInperiod("period3", statusList);
                    }
                }
                dialog.dismiss();
            }

        });
        dialog.show();
    }

    private void insertdataInperiod(String period,String statusList) {

        String date = getdate();
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(period, statusList);
        database.update(Constants.updatedClassID, values, "date=?", new String[]{date});
        database.close();
    }
    public void deleteData(String id){
        Log.i("DELETED ROW 0",id);
        String table = TABLE_CLASS_DATA;
        String whereClause = "id" + "=?";
        String[] whereArgs = new String[] { id };
        sqLiteDatabase.delete(table, whereClause, whereArgs);
        Log.i("DELETED ROW", id);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + id);
        Toast.makeText(context,"DELETED SUCCESSFULLY",Toast.LENGTH_SHORT).show();

    }
    void confirmationDialog(String message, final String period, final String statusList){
        boolean flag ;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to replace.");
        builder.setMessage(message);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            String date = getdate();

            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteDatabase database = getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(period, statusList);
                database.update(Constants.updatedClassID, values, "date =?", new String[]{date});
                database.close();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();

    }

    public void insertData(String id,String name,String section,String department,int strength,String studentlist) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CLASS_ID,id);
        values.put(CLASS_NAME,name);
        values.put(SECTION, section);
        values.put(DEPARTMENT, department);
        values.put(STRENGTH, strength);
        values.put(STUDENT_LIST, studentlist);


        // Inserting Row
        db.insert(TABLE_CLASS_DATA, null, values);
        db.close();

    }

    public void createClassTable(String TABLE_NAME){

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        String  createQuery = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (srno INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,date INTEGER ,period1 TEXT,period2 TEXT,period3 TEXT,xdate TEXT,xmonth TEXT,xyear TEXT)";
        database.execSQL(createQuery);
        values.put("date", getdate());
        values.put("xdate", getdateno());
        values.put("xmonth", getMonth());
        values.put("xyear", getYear());
        Cursor cursor = database.query(TABLE_NAME,null,"date =?",new String[]{getdate()},null,null,null);
        cursor.moveToFirst();

        if(cursor.getCount()<1)
            database.insert(TABLE_NAME,null,values);

        database.close();

    }

    public  ArrayList<String> getClassNameList(){
        ArrayList<String> nameList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT id FROM "+TABLE_CLASS_DATA;
        Cursor cursor = database.rawQuery(query,null);
        if(cursor.getCount()<1)
            return nameList;
        cursor.moveToFirst();
        nameList.add(cursor.getString(0));
        while (cursor.moveToNext()){
            nameList.add(cursor.getString(0));
            cursor.moveToNext();
        }
        return nameList;
    }
    public ArrayList<ClassInfo> getData(){
        boolean cursorStatus = false ;
        ArrayList<ClassInfo> classInfoList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_CLASS_DATA ;
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.getCount()>0) {
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

    public ArrayList<String> getStudentList (String table){
        ArrayList<String> studentlist = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String Query = "SELECT * FROM "+TABLE_CLASS_DATA;
        String studentString ="" ;
        Cursor cursor = database.query(TABLE_CLASS_DATA, null, "id =?", new String[]{table}, null,null,null);

        if(cursor!=null) {
            cursor.moveToFirst();
            studentString = cursor.getString(6);
        }
        studentlist = getListFromString(studentString);
        return studentlist;
}
    String getdate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        String date = format.format(calendar.getTime());
        return date;
    }

    String getdateno(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd");
        String date = format.format(calendar.getTime());
        return date;
    }

    private ArrayList<String> getListFromString(String studentStatusList) {
        ArrayList<String>  studentStatusListInfo = new ArrayList<String>();
        for(String tempString : studentStatusList.split(",")){
            studentStatusListInfo.add(tempString);
        }
        return  studentStatusListInfo;
    }
    String getMonth(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("MMMM");
        String month = format.format(calendar.getTime());
        return month;
    }

    String getYear(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String year = format.format(calendar.getTime());
        return year;
    }

    public Set<String> getYearList(String TABLE_NAME) {
        Set<String> yearList = new ArraySet<>();
        SQLiteDatabase database = getReadableDatabase() ;
        String Query = "SELECT xyear FROM "+TABLE_NAME ;
        Cursor cursor = database.rawQuery(Query,null);
        if(cursor.getCount()<0){
            return yearList;
        }
        cursor.moveToFirst();


            while (!cursor.isAfterLast()) {
                yearList.add(cursor.getString(0));
                cursor.moveToNext();
            }
        return yearList;
    }


    public Set<String> getMonthList(String TABLE_NAME, String YEAR){
        Set<String> monthList = new ArraySet<>();
        SQLiteDatabase database = getReadableDatabase() ;
        String Query = "SELECT xmonth FROM "+TABLE_NAME+" WHERE xyear ="+YEAR ;
        Cursor cursor = database.rawQuery(Query,null);
        if(cursor.getCount()<0){
            return monthList;
        }
        cursor.moveToFirst();


        while (!cursor.isAfterLast()) {
            monthList.add(cursor.getString(0));
            cursor.moveToNext();
        }
        return monthList;
    }
}

