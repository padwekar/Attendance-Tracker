package com.example.savi.atun.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.savi.atun.R;

/**
 * Created by devuser on 01-04-2016.
 */
public class ClassDetailsSmall extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_details_small,container,false);
        String name = getArguments().getString("name");
        String strength = getArguments().getString("strength");
        TextView textView_className =(TextView)view.findViewById(R.id.textview_className_main_frag);
        TextView textView_strength = (TextView)view.findViewById(R.id.textview_strength_main_frag);

        textView_className.setText(name);
        textView_strength.setText(strength);

        return view;
    }
}
