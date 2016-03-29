package com.example.savi.atun.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.savi.atun.Database.DataHelper;
import com.example.savi.atun.R;

import java.util.Set;

public class YearActivity extends Activity {
    ListView listView  ;
    DataHelper dataHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);
        setTitle("SELECT YEAR");
        listView = (ListView)findViewById(R.id.listview_showyear);
        String[] year ;  ;
        dataHelper = new DataHelper(YearActivity.this);
        final Intent getList = getIntent() ;
        year = getList.getStringArrayExtra("yearlist");
        final String TABLE_NAME = getList.getStringExtra("tablename");
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,year);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String year = parent.getItemAtPosition(position).toString();
                Set<String> monthlist =dataHelper.getMonthList(TABLE_NAME, year);
                String[] month = monthlist.toArray(new String[monthlist.size()]);
                Intent monthIntent = new Intent(getApplicationContext(),MonthActivity.class);
                monthIntent.putExtra("monthList",month);
                monthIntent.putExtra("year",year);
                monthIntent.putExtra("table",TABLE_NAME);
                startActivity(monthIntent);
            }
        });

    }
}
