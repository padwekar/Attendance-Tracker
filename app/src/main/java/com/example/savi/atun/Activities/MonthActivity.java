package com.example.savi.atun.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.savi.atun.Adapters.ShowMonthAdapter;
import com.example.savi.atun.R;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MonthActivity extends Activity {
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        String month[];
        Intent getMonthIntent = getIntent();
        month = getMonthIntent.getStringArrayExtra("monthList");
        String year = getMonthIntent.getStringExtra("year");
        String TABLE_NAME = getMonthIntent.getStringExtra("table");
        month = getSortedMonths(month);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_monthlist);
    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        ShowMonthAdapter showMonthAdapter = new ShowMonthAdapter(month,year,TABLE_NAME);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(showMonthAdapter);
    }

    private String[] getSortedMonths(String[] month) {
        List<String> monlist = Arrays.asList(month);
        Collections.sort(monlist,dateCompare);
        return monlist.toArray(new String[monlist.size()]);
    }

    private void assignid() {
    }

    final Comparator<String> dateCompare = new Comparator<String>() {
        public int compare(String o1, String o2) {

            SimpleDateFormat s = new SimpleDateFormat("MMMM");
            Date s1 = null;
            Date s2 = null;
            try {
                s1 = s.parse(o1);
                s2 = s.parse(o2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return s1.compareTo(s2);
        }
    };

}
