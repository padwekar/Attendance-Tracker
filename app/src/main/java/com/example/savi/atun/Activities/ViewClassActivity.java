package com.example.savi.atun.Activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


import com.example.savi.atun.Adapters.ShowClassAdapter;
import com.example.savi.atun.Beans.ClassInfo;
import com.example.savi.atun.Database.DataHelper;
import com.example.savi.atun.R;

import java.util.ArrayList;
import java.util.List;

public class ViewClassActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowClassAdapter showClassAdapter ;
    RecyclerView.LayoutManager layoutManager ;
    ArrayList<ClassInfo> classInfoList = new ArrayList<ClassInfo>();
    DataHelper dataHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_list_recyclerview);
        dataHelper = new DataHelper(ViewClassActivity.this);
        classInfoList = dataHelper.getData();
        layoutManager = new LinearLayoutManager(this);
        recyclerView =(RecyclerView)findViewById(R.id.recyclerView_viewClass);
        recyclerView.setLayoutManager(layoutManager);
        showClassAdapter = new ShowClassAdapter(ViewClassActivity.this,classInfoList);
        recyclerView.setAdapter(showClassAdapter);
    }

}
