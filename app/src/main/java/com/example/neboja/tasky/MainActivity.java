package com.example.neboja.tasky;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

    public class MainActivity extends Activity {

        public static final String KEY_TITLE = "key_title";
        public static final String KEY_DESCRIPTION = "key_description";
        public static final String KEY_PRIORITY = "key_priority";
        public static final String KEY_CATEGORY = "key_category";

        public static final int KEY = 3;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setUI();
        }

        Button button;
        TAdapter tTaskAdapter;
        ListView lvTaskList;

        private void setUI() {
         button= (Button) findViewById(R.id.bAddNewTask);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), Main2Activity.class);
                    startActivity(intent);

                }
            });

            lvTaskList = (ListView) findViewById(R.id.lvTaskList);
            tTaskAdapter = new TAdapter(this.loadTasks());
            lvTaskList.setAdapter(this.tTaskAdapter);

            this.lvTaskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Task task = (Task) lvTaskList.getItemAtPosition(position);
                    tTaskAdapter.deleteAt(position);
                    DBHelper.getInstance(getApplicationContext()).Delete(task);
                    return false;
                }
            });
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode){
                case KEY:
                    TAdapter taskAdapter = (TAdapter) lvTaskList.getAdapter();
                    Task task = new Task(data.getDataString(),data.getDataString(),data.getDataString(), data.getDataString());
                    taskAdapter.insert(task);
                    loadTasks();
                    break;
            }
        }

        private ArrayList<Task> loadTasks() {
            return DBHelper.getInstance(this).getAllTasks();
        }
    }