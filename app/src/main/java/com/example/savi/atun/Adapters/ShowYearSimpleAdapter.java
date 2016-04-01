package com.example.savi.atun.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.savi.atun.Activities.MonthActivity;
import com.example.savi.atun.Database.DataHelper;
import com.example.savi.atun.R;

import java.util.Set;

/**
 * Created by devuser on 01-04-2016.
 */
public class ShowYearSimpleAdapter extends ArrayAdapter {
    String[] year ;
    String[] colors = {"#5000E5FF","#50FF5722","#5000E676","#5090CAF9"};
    Context context ;
    MyViewHolder holder;
    DataHelper dataHelper ;
    String TABLE_NAME ="";
    String classname,strength ;
    public ShowYearSimpleAdapter(Context context, int resource, String[] year,String TABLE_NAME) {
        super(context, resource, year);
        this.context = context;
        this.year = year;
        dataHelper = new DataHelper(context);
        this.TABLE_NAME = TABLE_NAME;
    }

    public void setClassName(String classname ,String strength){
        this.classname = classname ;
        this.strength = strength ;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            holder = new MyViewHolder();
            convertView = inflater.inflate(R.layout.row_show_year,parent,false);
            holder.textView = (TextView)convertView.findViewById(R.id.textview_year);
            holder.cardView = (CardView)convertView.findViewById(R.id.card_view_year);
            convertView.setTag(holder);
        }
        else {
        holder =(MyViewHolder) convertView.getTag();
        }

        holder.textView.setText(year[0]);
        holder.cardView.setCardBackgroundColor(Color.parseColor(colors[position % 4]));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year =  holder.textView.getText().toString();
                Set<String> monthlist = dataHelper.getMonthList(TABLE_NAME, year);
                String[] month = monthlist.toArray(new String[monthlist.size()]);
                Intent monthIntent = new Intent(context, MonthActivity.class);
                monthIntent.putExtra("monthList", month);
                monthIntent.putExtra("year", year);
                monthIntent.putExtra("table", TABLE_NAME);
                monthIntent.putExtra("name",classname);
                monthIntent.putExtra("strength",strength);
                context.startActivity(monthIntent);
            }
        });
        return convertView;
    }

    class MyViewHolder {
        TextView textView ;
        CardView cardView ;
    }
}
