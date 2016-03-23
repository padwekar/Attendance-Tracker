package com.example.savi.atun.Adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.savi.atun.Beans.StudentInfo;
import com.example.savi.atun.Constatnts.Constants;
import com.example.savi.atun.Database.DataHelper;
import com.example.savi.atun.R;

import java.util.ArrayList;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.savi.atun.Beans.StudentInfo;
import com.example.savi.atun.Constatnts.Constants;
import com.example.savi.atun.Database.DataHelper;
import com.example.savi.atun.R;

import java.util.ArrayList;


public class TakeAttendanceAdapter extends RecyclerView.Adapter<TakeAttendanceAdapter.TakeAttendanceViewHolder> {
    ArrayList<String> studentInfos = new ArrayList<>();
    Context context;
    boolean[] presentyRecorder ;
    DataHelper dataHelper ;
    public TakeAttendanceAdapter(Context context ,ArrayList<String> studentInfos) {
        this.studentInfos = studentInfos;
        this.context =context;
        dataHelper = new DataHelper(context);
        presentyRecorder = new boolean[studentInfos.size()];

    }

    @Override
    public TakeAttendanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_student_status,parent,false);
        return new TakeAttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TakeAttendanceViewHolder holder, int position) {
        holder.rollNo.setText(position+1 +"");
        holder.StudentName.setText(studentInfos.get(position).toString());
        holder.isPresent.setChecked(presentyRecorder[position]);

    }

    @Override
    public int getItemCount() {
        return studentInfos.size();
    }

    class TakeAttendanceViewHolder extends RecyclerView.ViewHolder{
        TextView rollNo, StudentName ;
        Switch isPresent ;

        public TakeAttendanceViewHolder(View itemView) {
            super(itemView);
            rollNo = (TextView)itemView.findViewById(R.id.tvrollno);
            StudentName = (TextView)itemView.findViewById(R.id.tv_name_main);
            isPresent = (Switch)itemView.findViewById(R.id.switch_isPresent);
            dataHelper = new DataHelper(itemView.getContext());
            isPresent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    isPresent.setChecked(isChecked);
                    presentyRecorder[getAdapterPosition()]=isPresent.isChecked();
                }
            });

        }
    }

    public void saveAttendance(){
        Constants.lastupdatedAttendance = new boolean[studentInfos.size()];
        Constants.lastupdatedAttendance =presentyRecorder;
        String attendanceRecord = getStringfromBoolean(presentyRecorder);
        dataHelper.insertAttenace(attendanceRecord);
    }

    private String getStringfromBoolean(boolean[] presentyRecorder) {
        String booleanString ="";
        String flag = "" ;
        for(int i=0 ; i<presentyRecorder.length ; i++){
            flag = (presentyRecorder[i])?"1":"0" ;
            booleanString = booleanString + flag ;
        }
        return booleanString;
    }
}
