package com.example.savi.atun.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.savi.atun.Activities.TakeAttendanceActivity;
import com.example.savi.atun.Beans.ClassInfo;
import com.example.savi.atun.Constatnts.Constants;
import com.example.savi.atun.R;

import java.util.ArrayList;

/**
 * Created by Savi on 20-03-2016.
 */
public class ShowClassAdapter  extends RecyclerView.Adapter<ShowClassAdapter.ViewClassHolder>{

    ArrayList<ClassInfo> classInfos = new ArrayList<ClassInfo>();

    public ShowClassAdapter(ArrayList<ClassInfo> classInfos) {
        this.classInfos = classInfos;
    }

    @Override
    public ViewClassHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        LayoutInflater inflater = (LayoutInflater)viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_class_confirm,viewGroup,false);
        ViewClassHolder holder = new ViewClassHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewClassHolder holder, int position) {
        holder.className.setText(classInfos.get(position).getClassName().toString());
        holder.section.setText(classInfos.get(position).getSection().toString());
        holder.department.setText(classInfos.get(position).getDepartment().toString());
        holder.strength.setText(classInfos.get(position).getStrength()+"");
    }

    @Override
    public int getItemCount() {
        return classInfos.size();
    }

    class ViewClassHolder extends RecyclerView.ViewHolder{

        TextView className,section,department,strength ;
        public ViewClassHolder(final View itemView) {
            super(itemView);
            className = (TextView)itemView.findViewById(R.id.classViewName);
            section = (TextView)itemView.findViewById(R.id.tvSection);
            department = (TextView)itemView.findViewById(R.id.tvDepartment);
            strength = (TextView)itemView.findViewById(R.id.classStrength);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String  studentString = classInfos.get(getAdapterPosition()).getStudentListString();
                        Constants.updatedClassName = className.getText().toString();
                         Constants.updatedSection   =section.getText().toString();
                        Constants.updatedDepartment = department.getText().toString();
                        Constants.updatedTotalStudent = Integer.parseInt(strength.getText().toString());
                        Intent intent = new Intent(view.getContext(),TakeAttendanceActivity.class);
                        intent.putExtra("studentString",studentString);
                        view.getContext().startActivity(intent);

        }
    }  );
}
}

}

