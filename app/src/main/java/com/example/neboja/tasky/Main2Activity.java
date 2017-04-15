package com.example.neboja.tasky;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

    public class Main2Activity extends Activity implements View.OnClickListener {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            setUI();
        }

        EditText etNewTask;
        EditText etNewTaskDescription;
        Button bAddNewTask;
        String taskTitle;
        String taskDescription;
        String taskPriority;
        String taskCategory;
        Spinner CSpinner;
        Spinner PSpinner;
        ArrayAdapter<String> Padapter;
        ArrayAdapter<String> Cadapter;
        String priority;
        String category;

        private void setUI() {

            PSpinner = (Spinner) findViewById(R.id.sPSpinner);
            Padapter = new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1,
                    getResources().getStringArray(R.array.sPriorityOptions));
            Padapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            PSpinner.setAdapter(Padapter);

            PSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object item = parent.getItemAtPosition(position);
                    priority = item.toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            CSpinner = (Spinner) findViewById(R.id.sCSpinner);
            Cadapter = new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1,
                    getResources().getStringArray(R.array.sCategoryOptions));
            Cadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            CSpinner.setAdapter(Cadapter);

            CSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object item = parent.getItemAtPosition(position);
                    category = item.toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            etNewTask = (EditText) findViewById(R.id.etNewTaskTitle);
            etNewTaskDescription = (EditText) findViewById(R.id.etNewTaskDescription);
            bAddNewTask = (Button) findViewById(R.id.bAddNewTask);
            bAddNewTask.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            taskTitle = etNewTask.getText().toString();
            taskDescription = etNewTaskDescription.getText().toString();

            if (priority.equals("High Priority"))
                taskPriority = "high";

            if (priority.equals("Medium Priority"))
                taskPriority = "medium";

            if (priority.equals("Low Priority"))
                taskPriority = "low";

            if (category.equals("Faculty"))
                taskCategory="Faculty";

            if (category.equals("Shopping"))
                taskCategory="Shopping";

            if (category.equals("Work"))
                taskCategory="Work";

            if (category.equals("Other"))
                taskCategory="Other";

            if (taskTitle.isEmpty() || taskDescription.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please Enter Task Title And Description!", Toast.LENGTH_SHORT).show();
            }
            else {
                Task task = new Task(taskTitle, taskDescription, taskPriority, taskCategory);
                DBHelper.getInstance(getApplicationContext()).insertTask(task);
                Intent newintent = new Intent();
                newintent.putExtra(MainActivity.KEY_TITLE, taskTitle);
                newintent.putExtra(MainActivity.KEY_DESCRIPTION, taskDescription);
                newintent.putExtra(MainActivity.KEY_PRIORITY, taskPriority);
                newintent.putExtra(MainActivity.KEY_CATEGORY, taskCategory);
                this.setResult(RESULT_OK, newintent);
                finish();
            }
        }
    }