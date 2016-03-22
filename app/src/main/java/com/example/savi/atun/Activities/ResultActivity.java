package com.example.savi.atun.Activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.savi.atun.Constatnts.Constants;
import com.example.savi.atun.Database.DataHelper;
import com.example.savi.atun.Fragments.ConfirmClassFrag;
import com.example.savi.atun.Fragments.ListFragmentClassCreate;
import com.example.savi.atun.R;


public class ResultActivity extends AppCompatActivity {
    ConfirmClassFrag confirmFrag ;
    ListFragmentClassCreate ClassListCreateFrag ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        confirmFrag = new ConfirmClassFrag();
        ClassListCreateFrag = new ListFragmentClassCreate();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_result_top, confirmFrag);
        fragmentTransaction.add(R.id.fragment_result_middle,ClassListCreateFrag);
        fragmentTransaction.commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_done :    createStudentTable();
                                                    createAttendanceTable(Constants.updatedClassID);
                                                    createSuccessDialog();
                                                    return true ;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void createAttendanceTable(String TABLE_NAME) {
        DataHelper dataHelper = new DataHelper(ResultActivity.this);
        dataHelper.createClassTable(TABLE_NAME);
    }

    private void createSuccessDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
        builder.setTitle("Status");
        builder.setMessage("Success");
        builder.create();
        builder.show();
    }


    private void createStudentTable(){
        String nameString ="";
        for(int i=0 ; i< Constants.studentname.length ; i++)
                 nameString =nameString+ Constants.studentname[i] + "," ;
        nameString =nameString.substring(0,nameString.length()-1);

        String classId =  Constants.updatedClassID;
        String className = Constants.updatedClassName;
        String section = Constants.updatedSection;
        String department = Constants.updatedDepartment;
        int strength = Constants.updatedTotalStudent;
        String studentList = nameString;

        DataHelper dataHelper = new DataHelper(ResultActivity.this);
        dataHelper.insertData(classId,className,section,department,strength,studentList);


    }
}
