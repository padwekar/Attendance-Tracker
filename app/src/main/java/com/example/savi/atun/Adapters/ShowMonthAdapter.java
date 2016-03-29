package com.example.savi.atun.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.savi.atun.R;

/**
 * Created by devuser on 29-03-2016.
 */
public class ShowMonthAdapter extends RecyclerView.Adapter<ShowMonthAdapter.MonthHolder> {
    String[] month ;

    public ShowMonthAdapter(String[] month){
        this.month = month ;
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
        public MonthHolder(View itemView) {
            super(itemView);
            textView_month = (TextView)itemView.findViewById(R.id.textview_month);
        }
    }
}


