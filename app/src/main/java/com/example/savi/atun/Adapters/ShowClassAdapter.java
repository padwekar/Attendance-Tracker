package com.example.savi.atun.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.savi.atun.Activities.TakeAttendanceActivity;
import com.example.savi.atun.Activities.ViewClassActivity;
import com.example.savi.atun.Beans.ClassInfo;
import com.example.savi.atun.Constatnts.Constants;
import com.example.savi.atun.Database.DataHelper;
import com.example.savi.atun.R;

import java.util.ArrayList;

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


public class ShowClassAdapter  extends RecyclerView.Adapter<ShowClassAdapter.ViewClassHolder>{
    Context context;
    DataHelper dataHelper ;
    ArrayList<ClassInfo> classInfos = new ArrayList<>();
    ViewClassActivity viewClassActivity = new ViewClassActivity();
    public ShowClassAdapter(Context context,ArrayList<ClassInfo> classInfos) {
        this.classInfos = classInfos;
        this.context =context;
        dataHelper = new DataHelper(context);

    }

    @Override
    public ViewClassHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        LayoutInflater inflater = (LayoutInflater)viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_class_list_view,viewGroup,false);

        return new ViewClassHolder(view);

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
        Button btn_take_attendance,btn_delete ;
        public ViewClassHolder(final View itemView) {
            super(itemView);
            className = (TextView)itemView.findViewById(R.id.classViewName);
            section = (TextView)itemView.findViewById(R.id.tvSection);
            department = (TextView)itemView.findViewById(R.id.tvDepartment);
            strength = (TextView)itemView.findViewById(R.id.classStrengthMain);
            btn_take_attendance = (Button)itemView.findViewById(R.id.btn_takeAttendance);
            btn_delete = (Button)itemView.findViewById(R.id.btn_delete_attendance);

            btn_take_attendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String  studentString = classInfos.get(getAdapterPosition()).getStudentListString();
                    Constants.updatedClassName = className.getText().toString();
                    Constants.updatedSection   =section.getText().toString();
                    Constants.updatedClassID = Constants.updatedClassName + Constants.updatedSection;
                    Constants.updatedDepartment = department.getText().toString();
                    Constants.updatedTotalStudent = Integer.parseInt(strength.getText().toString());
                    Intent intent = new Intent(view.getContext(),TakeAttendanceActivity.class);
                    intent.putExtra("studentString",studentString);
                    view.getContext().startActivity(intent);

                }
            }  );

            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(btn_delete.getContext());
                    builder.setTitle("Are you sure you want to delete this class ?");
                    builder.setMessage("All the attendance record associated with this class will be delete.");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Constants.updatedClassName = className.getText().toString();
                            Constants.updatedSection = section.getText().toString();
                            Constants.updatedClassID = Constants.updatedClassName + Constants.updatedSection;
                            int tempos = getAdapterPosition();
                            classInfos.remove(getAdapterPosition());
                            notifyItemRemoved(tempos);
                            deleteData(Constants.updatedClassID);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create();
                    builder.show();
                }
            });
        }
    }

    private void deleteData(String classId) {
        dataHelper.deleteData(classId);

    }

}
