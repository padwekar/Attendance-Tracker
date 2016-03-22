package com.example.savi.atun.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.savi.atun.Fragments.ConfirmClassFrag;
import com.example.savi.atun.Fragments.TakeAttendanceFragment;
import com.example.savi.atun.R;

import java.util.ArrayList;

public class TakeAttendanceActivity extends AppCompatActivity {
    ArrayList<String> studentList = new ArrayList<String>();
    TakeAttendanceFragment attendanceFragment;   ConfirmClassFrag confirmClassFrag ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);


        Intent intent = getIntent();
        String studentString =intent.getStringExtra("studentString");

       Bundle bundle = new Bundle();
       bundle.putString("studentString", studentString);

        attendanceFragment = new TakeAttendanceFragment();
        attendanceFragment.setArguments(bundle);
        confirmClassFrag = new ConfirmClassFrag();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_confirmCLass,confirmClassFrag);
        fragmentTransaction.add(R.id.fragment_studentList, attendanceFragment);
        fragmentTransaction.commit();
    }

    public void onSave(View view){
        attendanceFragment.callSaveAttendance();
    }

}
