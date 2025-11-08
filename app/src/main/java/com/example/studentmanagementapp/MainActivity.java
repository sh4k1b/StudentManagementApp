package com.example.studentmanagementapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText studentId;
    Button login, register;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentId = findViewById(R.id.studentId);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        db = new DBHelper(this);

        login.setOnClickListener(v -> {
            String id = studentId.getText().toString();
            if (db.studentExists(id)) {
                SharedPreferences sp = getSharedPreferences("StudentPrefs", MODE_PRIVATE);
                sp.edit().putString("studentId", id).apply();
                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
            } else {
                Toast.makeText(MainActivity.this, "Student not found", Toast.LENGTH_SHORT).show();
            }
        });

        register.setOnClickListener(v -> {
            String id = studentId.getText().toString();
            if (db.registerStudent(id)) {
                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Student already exists", Toast.LENGTH_SHORT).show();
            }
        });
    }
}