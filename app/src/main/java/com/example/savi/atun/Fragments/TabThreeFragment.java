package com.example.savi.atun.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.savi.atun.Adapters.ShowAdapterFinal;
import com.example.savi.atun.Beans.StudentInfo;
import com.example.savi.atun.R;

import java.util.ArrayList;

/**
 * Created by devuser on 29-03-2016.
 */
public class TabThreeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slot3,container,false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.next_frag_list);
        ArrayList<StudentInfo> studentInfos = getArguments().getParcelableArrayList("tab3");
        ShowAdapterFinal adapterFinal = new ShowAdapterFinal(studentInfos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterFinal);
        return view;
    }
}
