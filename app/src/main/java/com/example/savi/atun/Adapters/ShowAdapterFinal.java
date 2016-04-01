package com.example.savi.atun.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.savi.atun.Beans.StudentInfo;
import com.example.savi.atun.R;

import java.util.ArrayList;

/**
 * Created by devuser on 30-03-2016.
 */
public class ShowAdapterFinal extends RecyclerView.Adapter<ShowAdapterFinal.ShowViewHolder> {
    ArrayList<StudentInfo> studentObjectList ;

    public ShowAdapterFinal(ArrayList<StudentInfo> studentObjectList) {
        this.studentObjectList = studentObjectList;
    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_student_present_status,parent,false);
        return new ShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowViewHolder holder, int position) {
        holder.rollNo.setText(studentObjectList.get(position).getRollNo()+"");
        holder.name.setText(studentObjectList.get(position).getName()+"");
        if(!studentObjectList.get(position).isPresent()){
            holder.isPresent.setImageDrawable(holder.itemView.getContext().getDrawable(R.drawable.abssenticon));
            holder.card_name.setCardBackgroundColor(Color.parseColor("#50F44336"));
        }else{
            holder.isPresent.setImageDrawable(holder.itemView.getContext().getDrawable(R.drawable.presenticon));
            holder.card_name.setCardBackgroundColor(Color.parseColor("#70607D8B"));
        }
    }

    @Override
    public int getItemCount() {
        return studentObjectList.size();
    }

    class ShowViewHolder extends RecyclerView.ViewHolder{
        TextView rollNo , name ;
        ImageView isPresent; CardView card_name ;
        public ShowViewHolder(View itemView) {
            super(itemView);

            rollNo = (TextView)itemView.findViewById(R.id.textview_rollNo);
            name = (TextView)itemView.findViewById(R.id.textview_studentName);
            isPresent = (ImageView)itemView.findViewById(R.id.img_present);
            card_name = (CardView)itemView.findViewById(R.id.card_view_nameStudent);
        }

    }
}
