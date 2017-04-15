package com.example.neboja.tasky;

/**
 * Created by Nebojša on 13/4/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

    public class DBHelper extends SQLiteOpenHelper{

        static final String CREATE_TABLE_TODO = "CREATE TABLE " + Schema.TABLE_TODO + " (" + Schema.TITLE + " TEXT," +
                Schema.DESCRIPTION + " TEXT," + Schema.PRIORITY + " TEXT," + Schema.CATEGORY + " TEXT);";

        static final String DROP_TABLE_TODO = "DROP TABLE IF EXISTS " + Schema.TABLE_TODO;
        static final String SELECT_ALL_TASKS = "SELECT " + Schema.TITLE + "," + Schema.DESCRIPTION + "," +
                Schema.PRIORITY + "," + Schema.CATEGORY  + " FROM " + Schema.TABLE_TODO;

        private static DBHelper DBHelper = null;

        private DBHelper (Context context){
            super(context.getApplicationContext(), Schema.DATABASE_NAME, null, Schema.SCHEMA_VERSION);
        }

        public static synchronized DBHelper getInstance(Context context){
            if (DBHelper == null){
                DBHelper = new DBHelper(context);
            }
            return DBHelper;
        }

        public void Delete(Task task){
            getWritableDatabase().execSQL("DELETE FROM "+Schema.TABLE_TODO + " WHERE " + Schema.TITLE +
                    "='"+task.gettTitle()+"' AND "+ Schema.DESCRIPTION +" ='" +task.gettDescription() + "' AND "
                    + Schema.PRIORITY +" = '" + task.gettPriority() +"' AND " + Schema.CATEGORY +" = '"
                    + task.gettCategory() + "'");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_TODO);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE_TODO);
            this.onCreate(db);
        }

        public void insertTask(Task task){
            ContentValues contentValues = new ContentValues();
            contentValues.put(Schema.TITLE, task.gettTitle());
            contentValues.put(Schema.DESCRIPTION, task.gettDescription());
            contentValues.put(Schema.PRIORITY, task.gettPriority());
            contentValues.put(Schema.CATEGORY, task.gettCategory());
            SQLiteDatabase writableDatabase = this.getWritableDatabase();
            writableDatabase.insert(Schema.TABLE_TODO, Schema.TITLE, contentValues);
            writableDatabase.close();
        }

        public ArrayList<Task> getAllTasks(){
            final SQLiteDatabase writableDatabase = this.getWritableDatabase();
            Cursor taskCursor = writableDatabase.rawQuery(SELECT_ALL_TASKS, null);
            ArrayList<Task> tasks = new ArrayList<>();
            if (taskCursor.moveToFirst()){
                do {
                    String title = taskCursor.getString(0);
                    String description = taskCursor.getString(1);
                    String priority = taskCursor.getString(2);
                    String category = taskCursor.getString(3);
                    tasks.add(new Task(title, description, priority, category));
                } while (taskCursor.moveToNext());
            }
            taskCursor.close();
            writableDatabase.close();
            return tasks;
        }

        public static class Schema
        {
            private static final int SCHEMA_VERSION = 1;
            private static final String DATABASE_NAME = "Task.db";
            static final String TABLE_TODO = "Tasks";
            static final String TITLE = "Task_name";
            static final String DESCRIPTION = "Task_description";
            static final String PRIORITY = "Task_priority";
            static final String CATEGORY = "Task_category";
    }
}
