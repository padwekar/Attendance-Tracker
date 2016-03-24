package com.example.savi.atun.Adapters;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;


import com.example.savi.atun.Constatnts.Constants;
import com.example.savi.atun.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Savi on 12-03-2016.
 */
public class FillListAdapter extends ArrayAdapter {
    Context context ;
    List<Integer> studentList = new ArrayList();

    private int lastFocussedPosition = -1;
    private Handler handler = new Handler();

    public FillListAdapter(Context context, int resource, List<Integer> studentList) {
        super(context, resource, studentList);
        this.context=context;
        this.studentList =studentList;
        Constants.studentname = new String[studentList.size()];
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {



            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_student_fill,parent,false);


        TextView rollno = (TextView)convertView.findViewById(R.id.createrollNofixed);
        rollno.setText(studentList.get(position) + "");

        final EditText edittext_studentName = (EditText)convertView.findViewById(R.id.etStudentName);
        edittext_studentName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Constants.studentname[position] = edittext_studentName.getText().toString();

            }
        });

        if(Constants.studentname[position]!=null)
            edittext_studentName.setText(Constants.studentname[position]);


        return convertView;
    }
}
