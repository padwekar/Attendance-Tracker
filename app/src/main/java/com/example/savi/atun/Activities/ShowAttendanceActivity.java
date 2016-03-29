package com.example.savi.atun.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.savi.atun.Constatnts.Constants;
import com.example.savi.atun.R;

public class ShowAttendanceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_attendance);
        Intent viewClassIntent = new Intent(this,ViewClassActivity.class);
        viewClassIntent.putExtra("viewMode", true);
        startActivity(viewClassIntent);
        finish();
    }
}
