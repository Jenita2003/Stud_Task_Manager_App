package com.example.stud_task_manager_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    TextView welcome;
    Button btnAddTask;
    RecyclerView recyclerView;
    EditText etSearch;

    public static ArrayList<Task> taskList = new ArrayList<>();
    ArrayList<Task> filteredList = new ArrayList<>();

    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcome = findViewById(R.id.tvWelcome);
        btnAddTask = findViewById(R.id.btnAddTask);
        recyclerView = findViewById(R.id.recyclerView);
        etSearch = findViewById(R.id.etSearch);

        welcome.setText("Welcome to Task Manager");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // initially show all tasks
        filteredList.addAll(taskList);

        adapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(adapter);

        // Add Task button
        btnAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AddTaskActivity.class);
            startActivity(intent);
        });

        // SEARCH LOGIC
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterTasks(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterTasks(String text) {
        filteredList.clear();

        if (text.isEmpty()) {
            filteredList.addAll(taskList);
        } else {
            for (Task task : taskList) {
                if (task.getTitle().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(task);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // refresh list after returning from AddTaskActivity
        filterTasks(etSearch.getText().toString());
    }
}