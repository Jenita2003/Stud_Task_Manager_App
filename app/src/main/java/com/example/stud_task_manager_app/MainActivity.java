package com.example.stud_task_manager_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText email = findViewById(R.id.etEmail);
        EditText password = findViewById(R.id.etPassword);
        Button loginBtn = findViewById(R.id.btnLogin);

        loginBtn.setOnClickListener(v -> {

            String emailText = email.getText().toString().trim();
            String passwordText = password.getText().toString().trim();

            // 🔹 Email validation
            if (emailText.isEmpty()) {
                email.setError("Email cannot be empty");
                email.requestFocus();
            }
            else if (!emailText.contains("@")) {
                email.setError("Enter valid email");
                email.requestFocus();
            }

            // Password validation
            else if (passwordText.isEmpty()) {
                password.setError("Password cannot be empty");
                password.requestFocus();
            }
            else if (passwordText.length() < 6) {
                password.setError("Password must be at least 6 characters");
                password.requestFocus();
            }

            else {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}