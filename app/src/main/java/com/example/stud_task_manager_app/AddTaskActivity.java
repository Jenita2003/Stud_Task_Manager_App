package com.example.stud_task_manager_app;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    EditText etTitle, etDesc, etDate;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDesc);
        etDate = findViewById(R.id.etDate);
        btnSave = findViewById(R.id.btnSave);

        // Date Picker
        etDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();

            DatePickerDialog datePicker = new DatePickerDialog(
                    this,
                    (view, year, month, dayOfMonth) -> {
                        String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                        etDate.setText(date);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );

            datePicker.show();
        });

        // Save Task
        btnSave.setOnClickListener(v -> {

            String title = etTitle.getText().toString().trim();
            String desc = etDesc.getText().toString().trim();
            String date = etDate.getText().toString().trim();

            if (title.isEmpty()) {
                etTitle.setError("Enter title");
            } else if (desc.isEmpty()) {
                etDesc.setError("Enter description");
            } else if (date.isEmpty()) {
                etDate.setError("Select date");
            } else {
                HomeActivity.taskList.add(new Task(title, date, "Pending"));

                Toast.makeText(this, "Task Saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}