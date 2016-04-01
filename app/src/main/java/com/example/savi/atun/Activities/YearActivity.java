package com.example.savi.atun.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.savi.atun.Adapters.ShowYearSimpleAdapter;
import com.example.savi.atun.Database.DataHelper;
import com.example.savi.atun.Fragments.ClassDetailsSmall;
import com.example.savi.atun.R;

import java.util.Set;

public class YearActivity extends AppCompatActivity {
    ListView listView  ;
    DataHelper dataHelper ;
    String name = "" ,strength = "";
    ClassDetailsSmall classDetailsFragmemt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_year);
        setTitle("SELECT YEAR");
        listView = (ListView)findViewById(R.id.listview_showyear);
        String[] year ;
        dataHelper = new DataHelper(YearActivity.this);
        final Intent getList = getIntent() ;
        classDetailsFragmemt = new ClassDetailsSmall();
        year = getList.getStringArrayExtra("yearlist");
        name = getList.getStringExtra("name");
        strength = getList.getStringExtra("strength");
        final String TABLE_NAME = getList.getStringExtra("tablename");

        setClassDetailFragment();

        ShowYearSimpleAdapter adapter = new ShowYearSimpleAdapter(YearActivity.this,R.layout.activity_year,year,TABLE_NAME);
        adapter.setClassName(name,strength);
        listView.setAdapter(adapter);


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
