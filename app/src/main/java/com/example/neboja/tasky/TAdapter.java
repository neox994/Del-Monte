package com.example.neboja.tasky;

/**
 * Created by Nebojša on 13/4/2017.
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;




    public class TAdapter extends BaseAdapter {

        private ArrayList<Task> tTasks;

        public TAdapter(ArrayList<Task> tasks){
            tTasks = tasks;
        }

        @Override
        public int getCount() {
            return this.tTasks.size();
        }

        @Override
        public Object getItem(int position) {
            return this.tTasks.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder taskViewHolder;
            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(R.layout.task, parent, false);
                taskViewHolder = new ViewHolder(convertView);
                convertView.setTag(taskViewHolder);
            }
            else{
                taskViewHolder = (ViewHolder) convertView.getTag();
            }
            Task task = this.tTasks.get(position);
            taskViewHolder.tvTaskTitle.setText(task.gettTitle());
            taskViewHolder.tvTaskDescription.setText(task.gettDescription());

            switch (task.gettPriority()){
                case "high":
                    taskViewHolder.tvTaskPriority.setBackgroundResource(R.drawable.high_priority);
                    break;
                case "medium":
                    taskViewHolder.tvTaskPriority.setBackgroundResource(R.drawable.medium_priority);
                    break;
                case "low":
                    taskViewHolder.tvTaskPriority.setBackgroundResource(R.drawable.low_priority);
                    break;
                default:
                    taskViewHolder.tvTaskPriority.setBackgroundResource(R.drawable.low_priority);
                    break;
            }
            taskViewHolder.tvTaskCategory.setText(task.gettCategory());
            return convertView;
        }

        public void insert(Task task) {
            this.tTasks.add(task);
            this.notifyDataSetChanged();
        }

        public void deleteAt(int position) {
            this.tTasks.remove(position);
            this.notifyDataSetChanged();
        }

        public static class ViewHolder {
            public TextView tvTaskTitle, tvTaskDescription, tvTaskPriority,tvTaskCategory;

            public ViewHolder(View taskView) {
                tvTaskTitle = (TextView) taskView.findViewById(R.id.tvTaskTitle);
                tvTaskDescription = (TextView) taskView.findViewById(R.id.tvTaskDescription);
                tvTaskPriority = (TextView) taskView.findViewById(R.id.tvTaskPriority);
                tvTaskCategory= (TextView) taskView.findViewById(R.id.tvTaskCategory);
            }
        }

}
