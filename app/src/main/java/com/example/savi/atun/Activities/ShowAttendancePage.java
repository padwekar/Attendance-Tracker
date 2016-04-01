package com.example.savi.atun.Activities;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.savi.atun.Adapters.PagerAdapter;
import com.example.savi.atun.Beans.StudentInfo;
import com.example.savi.atun.Database.DataHelper;
import com.example.savi.atun.Fragments.ClassDetailsSmall;
import com.example.savi.atun.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by devuser on 29-03-2016.
 */
public class ShowAttendancePage extends AppCompatActivity {
    TextView textview_month , textView_date , textview_year ;
    ImageButton  btn_prev ,btn_next ;
    FragmentTransaction fragmentTransaction ;
    FragmentManager fragmentManager ;
    TabLayout tabLayout ;
    PagerAdapter adapter;
    DataHelper dataHelper ;
    ViewPager viewPager;
    ClassDetailsSmall classDetailsFragmemt ;
    String month , year , table , name , strength ;
    ArrayList<StudentInfo> slot1Objects ;
    ArrayList<StudentInfo> slot2Objects ;
    ArrayList<StudentInfo> slot3Objects ;
    int position = 0;
    int tabPos ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_attendance_main);
        textView_date = (TextView)findViewById(R.id.textview_date);
        textview_month = (TextView)findViewById(R.id.textview_month);
        textview_year = (TextView)findViewById(R.id.textview_year);
        btn_prev = (ImageButton)findViewById(R.id.btn_prev);
        btn_next = (ImageButton)findViewById(R.id.btn_next);
        fragmentManager = getSupportFragmentManager();
        classDetailsFragmemt = new ClassDetailsSmall();
        Intent getMonth = getIntent();
        month = getMonth.getStringExtra("month");
        name = getMonth.getStringExtra("name");
        strength = getMonth.getStringExtra("strength");
        textview_month.setText(month);
        textView_date.setText(0 +"");
        setClassDetailFragment();
        year = getMonth.getStringExtra("year");
        textview_year.setText(year);
        table = getMonth.getStringExtra("table");
        fragmentTransaction = fragmentManager.beginTransaction();
        slot1Objects = new ArrayList<>();
        slot2Objects = new ArrayList<>();
        slot3Objects = new ArrayList<>();
        dataHelper = new DataHelper(getApplicationContext());

        Calendar mycal = new GregorianCalendar(2016 ,3 , 1);

// Get the number of days in that month
       final int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

        //Tab
         tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("SLOT 1"));
        tabLayout.addTab(tabLayout.newTab().setText("SLOT 2"));
        tabLayout.addTab(tabLayout.newTab().setText("SLOT 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Pager Adapter
        adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());


        //View Pager
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
        tabPos = viewPager.getCurrentItem();

        findViewById(R.id.btn_prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position >= 2)
                    position--;
                else
                    return;

                textView_date.setText(position + "");
                setList(position, month,year,table);
                adapter.notifyDataSetChanged();

            }
        });

        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position<=daysInMonth-1)
                    position++;
                else
                    return;

                textView_date.setText(position+"");
                setList(position,month,year,table);
                adapter.notifyDataSetChanged();


            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tabPos = viewPager.getCurrentItem();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setList(int date, String month ,String year,String table) {
       ArrayList<String> studentlist = dataHelper.getStudentList(table);
        HashMap<String,String> map = dataHelper.checkslotstatusgetData(date + "", month, year, table);

        if(map.size()==0){
            slot1Objects = new ArrayList<>();
            slot2Objects = new ArrayList<>();
            slot3Objects = new ArrayList<>();
        }else
        {
            if (map.get("period1") == ""){
                slot1Objects = new ArrayList<>();
            }
            else
                slot1Objects = null;

            if (map.get("period2") == ""){
                slot2Objects = new ArrayList<>();
          }
            else
                slot2Objects = null;

            if (map.get("period3") == ""){
                slot3Objects = new ArrayList<>();
           }
            else
                slot3Objects = null;

            if (slot1Objects == null) {
                slot1Objects = new ArrayList<>();
                for (int i = 0; i < studentlist.size(); i++) {
                    slot1Objects.add(new StudentInfo(i + 1, studentlist.get(i), map.get("period1").charAt(i)== '1' ? true : false));
                }
            }

            if (slot2Objects == null) {
                slot2Objects = new ArrayList<>();
                for (int i = 0; i < studentlist.size(); i++) {
                    slot2Objects.add(new StudentInfo(i + 1, studentlist.get(i), map.get("period2").charAt(i) == '1' ? true : false));
                }
            }
            if (slot3Objects == null) {
                slot3Objects = new ArrayList<>();
                for (int i = 0; i < studentlist.size(); i++) {
                    slot3Objects.add(new StudentInfo(i + 1, studentlist.get(i), map.get("period3").charAt(i) == '1' ? true : false));
                }
            }
        }



        adapter.setSuperLists(slot1Objects,slot2Objects,slot3Objects);

    }
    private void setClassDetailFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout_class_details, classDetailsFragmemt);
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putString("strength",strength);
        classDetailsFragmemt.setArguments(bundle);
        fragmentTransaction.commit();
    }

}

