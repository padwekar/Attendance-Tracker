package com.example.savi.atun.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.support.design.widget.Snackbar;
import android.widget.TextView;

import com.example.savi.atun.Constatnts.Constants;
import com.example.savi.atun.Database.DataHelper;
import com.example.savi.atun.Fragments.ConfirmClassFrag;
import com.example.savi.atun.Fragments.ListFragmentClassCreate;
import com.example.savi.atun.R;

import java.util.ArrayList;


public class CreateClassActivity extends AppCompatActivity {
    EditText edittext_classId,edittext_className,edittext_department,edittext_section,edittext_strength ;
    ConfirmClassFrag confirmClassFrag;
    ListFragmentClassCreate listFragmentClassCreate ;
     View coordinatorLayoutView ;
    DataHelper dataHelper;
    ArrayList<String> classNameList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        classNameList = new ArrayList<>();
        edittext_classId = (EditText)findViewById(R.id.edittext_classId);
        setclassIDtodisable();
        edittext_className = (EditText)findViewById(R.id.edittext_className);
        edittext_department = (EditText)findViewById(R.id.edittext_department);
        edittext_section = (EditText)findViewById(R.id.edittext_sectionName);
        edittext_strength = (EditText)findViewById(R.id.edittext_classStrength);
        coordinatorLayoutView = findViewById(R.id.snackbarPosition);
        setTextWatcher(edittext_className);
        setTextWatcher(edittext_section);
        setTextWatcher(edittext_department);
        setTextWatcher(edittext_strength);
        dataHelper = new DataHelper(CreateClassActivity.this);
       classNameList = dataHelper.getClassNameList();
        getClassID();

    }


    private void getClassID() {
        edittext_className.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {
                String classID = edittext_className.getText().toString() + edittext_section.getText().toString();
                edittext_classId.setText(classID);
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String classID = edittext_className.getText().toString() + edittext_section.getText().toString();
                edittext_classId.setText(classID);
            }
        });

      edittext_section.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {
          }

          @Override
          public void afterTextChanged(Editable s) {
              String classID = edittext_className.getText().toString() + edittext_section.getText().toString();
              edittext_classId.setText(classID);
          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
              String classID = edittext_className.getText().toString() + edittext_section.getText().toString();
              edittext_classId.setText(classID);
          }
      });

    }

    void setTextWatcher(final EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String result = s.toString().replace(" ", "");
                if (!s.toString().equals(result)) {
                    editText.setText(result);
                    editText.setSelection(result.length());
                }
            }
        });
    }
    private void setclassIDtodisable() {
        edittext_classId.setFocusable(false);
        edittext_classId.setEnabled(false);
        edittext_classId.setCursorVisible(false);
        edittext_classId.setKeyListener(null);
        edittext_classId.setBackgroundColor(Color.TRANSPARENT);
    }

    void goToResultActivity(){

    if(!Validate()){
        return;
    }


    listFragmentClassCreate = new ListFragmentClassCreate();
     confirmClassFrag = new ConfirmClassFrag();

    Intent intent = new Intent(CreateClassActivity.this,ResultActivity.class);
    String classID = edittext_classId.getText().toString();
    String className = edittext_className.getText().toString();
    String department = edittext_department.getText().toString();
    String section = edittext_section.getText().toString();
    int strength = Integer.parseInt(edittext_strength.getText().toString());


    intent.putExtra("classId", classID);
    intent.putExtra("className",className);
    intent.putExtra("department",department);
    intent.putExtra("section",section);
    intent.putExtra("strength",strength);


    confirmClassFrag.setClassDetails(classID, className, section, department, strength);
    listFragmentClassCreate.setClassStrength(strength);
    startActivity(intent);
    finish();
}

    private boolean Validate() {
        if(edittext_className.getText().toString().equals("")){
            showSnackbar("Enter Class Name");
            return false;
        }

        if(edittext_section.getText().toString().equals("")){
            showSnackbar("Enter Section");
            return false;
        }

        if(edittext_department.getText().toString().equals("")){
            showSnackbar("Enter Department");
            return false;
        }

        if(edittext_strength.getText().toString().equals("")){
            showSnackbar("Enter Strength");
            return false;
        }

        if(Integer.parseInt(edittext_strength.getText().toString())>100){
            showSnackbar("Strength cannot be more than 100");
            return false;
        }
        String classID = edittext_classId.getText().toString();
        for(int i=0; i <classNameList.size();i++){
            if(classNameList.get(i).equalsIgnoreCase(classID)){
                showSnackbar("Class Section Combination Already Exists");
                return false;
            }

        }


        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_next : goToResultActivity();
                       return true;
            default:  return super.onOptionsItemSelected(item);
        }
    }

    void showSnackbar(String Messege){
       Snackbar snackbar = Snackbar.make(coordinatorLayoutView, Messege, Snackbar.LENGTH_SHORT);

        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.RED);
        snackbar.show();
        return;
    }
}
