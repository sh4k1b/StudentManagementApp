package com.example.studentmanagementapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class EnrolledCoursesActivity extends AppCompatActivity {

    ListView enrolledCoursesList;
    Button backToDashboard;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_courses);

        enrolledCoursesList = findViewById(R.id.enrolledCoursesList);
        backToDashboard = findViewById(R.id.backToDashboard);
        db = new DBHelper(this);

        SharedPreferences sp = getSharedPreferences("StudentPrefs", MODE_PRIVATE);
        String studentId = sp.getString("studentId", "");

        List<String> courses = db.getEnrolledCourses(studentId);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);
        enrolledCoursesList.setAdapter(adapter);

        backToDashboard.setOnClickListener(v -> {
            startActivity(new Intent(EnrolledCoursesActivity.this, DashboardActivity.class));
            finish();
        });
    }
}
