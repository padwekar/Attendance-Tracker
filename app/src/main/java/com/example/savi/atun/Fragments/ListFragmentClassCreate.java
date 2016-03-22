package com.example.savi.atun.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.savi.atun.Adapters.FillListAdapter;
import com.example.savi.atun.Constatnts.Constants;
import com.example.savi.atun.R;

import java.util.ArrayList;

/**
 * Created by Savi on 12-03-2016.
 */
public class ListFragmentClassCreate extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view,container,false);
        ListView listView = (ListView)view.findViewById(R.id.listview);
        ArrayList<Integer> studentNumbers = new ArrayList<>();

        for(int i=1 ; i <= Constants.updatedTotalStudent ; i++)
            studentNumbers.add(i);

        FillListAdapter fillListAdapter = new FillListAdapter(getActivity(),R.layout.fragment_list_view,studentNumbers);
        listView.setAdapter(fillListAdapter);
        return view;
    }

   public void setClassStrength(int total){

       Constants.updatedTotalStudent= total ;
    }
}
