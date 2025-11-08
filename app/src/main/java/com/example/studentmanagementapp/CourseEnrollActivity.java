package com.example.studentmanagementapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CourseEnrollActivity extends AppCompatActivity {

    Spinner courseSpinner;
    Button enrollBtn, backToDashboard;
    DBHelper db;
    String[] courseList = {"Course 1", "Course 2", "Course 3", "Course 4", "Course 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_enroll);

        courseSpinner = findViewById(R.id.courseSpinner);
        enrollBtn = findViewById(R.id.enrollBtn);
        backToDashboard = findViewById(R.id.backToDashboard);
        db = new DBHelper(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, courseList);
        courseSpinner.setAdapter(adapter);

        SharedPreferences sp = getSharedPreferences("StudentPrefs", MODE_PRIVATE);
        String studentId = sp.getString("studentId", "");

        enrollBtn.setOnClickListener(v -> {
            String selectedCourse = courseSpinner.getSelectedItem().toString();
            if (db.enrollCourse(studentId, selectedCourse)) {
                Toast.makeText(this, "Enrolled in " + selectedCourse, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Already enrolled in " + selectedCourse, Toast.LENGTH_SHORT).show();
            }
        });

        backToDashboard.setOnClickListener(v -> {
            startActivity(new Intent(CourseEnrollActivity.this, DashboardActivity.class));
            finish();
        });
    }
}