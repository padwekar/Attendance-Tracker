package com.example.savi.atun.Activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.savi.atun.R;

/**
 * Created by devuser on 29-03-2016.
 */
public class ShowAttendancePage extends AppCompatActivity {
    TextView textview_month , textView_date ;
    ImageButton  btn_prev ,btn_next ;
    FragmentTransaction fragmentTransaction ;
    FragmentManager fragmentManager ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance_main);
        textView_date = (TextView)findViewById(R.id.textview_date);
        textview_month = (TextView)findViewById(R.id.textview_month);
        btn_prev = (ImageButton)findViewById(R.id.btn_prev);
        btn_next = (ImageButton)findViewById(R.id.btn_next);

    }
}
