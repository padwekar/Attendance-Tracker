package com.example.savi.atun.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.savi.atun.R;

public class HomeActivity extends AppCompatActivity {
    Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onHomeOptionSelected(View view) {
        switch (view.getId()) {
            case R.id.btn_createClass:
                createClass();
                break;
            case R.id.btn_viewClass:
                viewClass();
                break;
            case R.id.btn_viewAttendance:
                viewAttendance();
                break;
            case R.id.btn_settings:
                changeSetting();
                break;

        }
    }

    private void createClass() {
        intent = new Intent(HomeActivity.this, CreateClassActivity.class);
        startActivity(intent);
    }

    private void viewClass() {
        intent = new Intent(HomeActivity.this, ViewClassActivity.class);
        startActivity(intent);
    }

    private void viewAttendance() {
        intent = new Intent(HomeActivity.this, ShowAttendanceActivity.class);
        startActivity(intent);
    }

    private void changeSetting() {
        Toast.makeText(this, "THIS FEATURE NOT AVAILABLE IN FREE VERSION", Toast.LENGTH_SHORT).show();

    }
}