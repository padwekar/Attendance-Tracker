package com.example.savi.atun.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.savi.atun.Adapters.TakeAttendanceAdapter;
import com.example.savi.atun.R;

import java.util.ArrayList;

/**
 * Created by Savi on 20-03-2016.
 */
public class TakeAttendanceFragment extends Fragment {


    ArrayList<String> studentStatusList ;
    String studentStatusListString ;
    TakeAttendanceAdapter takeAttendanceAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_class, container, false);
        studentStatusListString = getArguments().getString("studentString");

        studentStatusList = getListFromString(studentStatusListString );
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView_viewClass);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
         takeAttendanceAdapter = new TakeAttendanceAdapter(getContext(),studentStatusList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(takeAttendanceAdapter);
        return view;
    }

    private ArrayList<String> getListFromString(String studentStatusList) {
        ArrayList<String>  studentStatusListInfo = new ArrayList<String>();
        for(String tempString : studentStatusList.split(",")){
            studentStatusListInfo.add(tempString);
        }
        return  studentStatusListInfo;
    }

    public void callSaveAttendance(){
        takeAttendanceAdapter.saveAttendance();
    }
}
