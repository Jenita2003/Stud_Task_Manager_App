package com.example.stud_task_manager_app;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    ArrayList<Task> taskList;

    public TaskAdapter(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, date, status;
        CheckBox cbDone;
        Button btnEdit, btnDelete;

        public MyViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.tvTitle);
            date = view.findViewById(R.id.tvDate);
            status = view.findViewById(R.id.tvStatus);

            cbDone = view.findViewById(R.id.cbDone);
            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_task, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Task task = taskList.get(position);

        holder.title.setText(task.getTitle());
        holder.date.setText("Due: " + task.getDueDate());

        // Always show formatted status
        holder.status.setText("Status: " + task.getStatus());

        // prevent checkbox flicker
        holder.cbDone.setOnCheckedChangeListener(null);

        holder.cbDone.setChecked(task.getStatus().equals("Completed"));

        holder.cbDone.setOnCheckedChangeListener((buttonView, isChecked) -> {

            task.setStatus(isChecked ? "Completed" : "Pending");

            // update ONLY this row
            notifyItemChanged(holder.getAdapterPosition());
        });

        // EDIT
        holder.btnEdit.setOnClickListener(v -> {

            int pos = holder.getAdapterPosition();
            if (pos == RecyclerView.NO_POSITION) return;

            Task currentTask = taskList.get(pos);

            EditText input = new EditText(v.getContext());
            input.setText(currentTask.getTitle());

            new AlertDialog.Builder(v.getContext())
                    .setTitle("Edit Task")
                    .setView(input)
                    .setPositiveButton("Save", (dialog, which) -> {

                        String newTitle = input.getText().toString().trim();

                        if (!newTitle.isEmpty()) {
                            currentTask.setTitle(newTitle);
                            notifyItemChanged(pos);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        // DELETE
        holder.btnDelete.setOnClickListener(v -> {

            int pos = holder.getAdapterPosition();
            if (pos == RecyclerView.NO_POSITION) return;

            taskList.remove(pos);
            notifyItemRemoved(pos);
            notifyItemRangeChanged(pos, taskList.size());
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}