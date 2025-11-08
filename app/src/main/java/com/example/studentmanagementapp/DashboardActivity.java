package com.example.studentmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    Button enrollCourse, sendApplication, viewCGPA, viewEnrolledCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        enrollCourse = findViewById(R.id.enrollCourse);
        sendApplication = findViewById(R.id.sendApplication);
        viewCGPA = findViewById(R.id.viewCGPA);
        viewEnrolledCourses = findViewById(R.id.viewEnrolledCourses);

        enrollCourse.setOnClickListener(v ->
                startActivity(new Intent(DashboardActivity.this, CourseEnrollActivity.class))
        );

        sendApplication.setOnClickListener(v ->
                startActivity(new Intent(DashboardActivity.this, ApplicationActivity.class))
        );

        viewCGPA.setOnClickListener(v ->
                startActivity(new Intent(DashboardActivity.this, CGPAActivity.class))
        );

        viewEnrolledCourses.setOnClickListener(v ->
                startActivity(new Intent(DashboardActivity.this, EnrolledCoursesActivity.class))
        );
    }
}
