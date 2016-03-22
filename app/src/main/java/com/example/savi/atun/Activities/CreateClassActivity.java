package com.example.savi.atun.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;


import com.example.savi.atun.Fragments.ConfirmClassFrag;
import com.example.savi.atun.Fragments.ListFragmentClassCreate;
import com.example.savi.atun.R;


public class CreateClassActivity extends AppCompatActivity {
    EditText edittext_classId,edittext_className,edittext_department,edittext_section,edittext_strength ;
    ConfirmClassFrag confirmClassFrag;
    ListFragmentClassCreate listFragmentClassCreate ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext_classId = (EditText)findViewById(R.id.edittext_classId);
        setclassIDtodisable();
        edittext_className = (EditText)findViewById(R.id.edittext_className);
        edittext_department = (EditText)findViewById(R.id.edittext_department);
        edittext_section = (EditText)findViewById(R.id.edittext_sectionName);
        edittext_strength = (EditText)findViewById(R.id.edittext_classStrength);
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

    }

    private void setclassIDtodisable() {
        edittext_classId.setFocusable(false);
        edittext_classId.setEnabled(false);
        edittext_classId.setCursorVisible(false);
        edittext_classId.setKeyListener(null);
        edittext_classId.setBackgroundColor(Color.TRANSPARENT);
    }

    void goToResultActivity(){



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


    confirmClassFrag.setClassDetails(classID,className,section,department,strength);
    listFragmentClassCreate.setClassStrength(strength);
    startActivity(intent);
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
}
