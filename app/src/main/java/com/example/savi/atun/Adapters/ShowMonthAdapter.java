package com.example.savi.atun.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.savi.atun.Activities.ShowAttendancePage;
import com.example.savi.atun.R;

/**
 * Created by devuser on 29-03-2016.
 */
public class ShowMonthAdapter extends RecyclerView.Adapter<ShowMonthAdapter.MonthHolder> {

    String[] month ; String year;String table ;

    public ShowMonthAdapter(String[] month,String year,String table){
        this.table = table;
        this.month = month ;
        this.year = year;
    }
    @Override
    public MonthHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_month_view,parent,false);
        return new MonthHolder(view);
    }

    @Override
    public void onBindViewHolder(MonthHolder holder, int position) {
        holder.textView_month.setText(month[position]);
    }

    @Override
    public int getItemCount() {
        return month.length;
    }

    class MonthHolder extends RecyclerView.ViewHolder{
        TextView textView_month;
        public MonthHolder(final View itemView) {
            super(itemView);
            textView_month = (TextView)itemView.findViewById(R.id.textview_month);

            textView_month.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent showAttendanceIntent = new Intent(itemView.getContext(), ShowAttendancePage.class);
                    showAttendanceIntent.putExtra("month",textView_month.getText().toString());
                    showAttendanceIntent.putExtra("year",year);
                    showAttendanceIntent.putExtra("table",table);
                    itemView.getContext().startActivity(showAttendanceIntent);
                }
            });
        }



    }
}


