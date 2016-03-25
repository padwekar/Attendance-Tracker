package com.example.savi.atun.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.savi.atun.Constatnts.Constants;
import com.example.savi.atun.R;

/**
 * Created by Savi on 25-03-2016.
 */
public class ConfirmClassModified extends Fragment {
    static int strength;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_confirm_modified,container,false);
        TextView textview_name = (TextView)view.findViewById(R.id.classViewName);
        TextView textview_section = (TextView)view.findViewById(R.id.tvSection);
        TextView textview_strength = (TextView)view.findViewById(R.id.classStrengthMain);

        textview_name.setText(Constants.updatedClassName);
        textview_section.setText(Constants.updatedSection);
        textview_strength.setText(Constants.updatedTotalStudent+"");
        return view;
    }

    public void setClassDetails(String classID,String className,String section,String department,int strength){

        Constants.updatedClassID=classID;
        Constants.updatedClassName =className ;
        Constants.updatedSection = section;
        Constants.updatedDepartment =department;
        Constants.updatedTotalStudent= strength ;
    }
}
