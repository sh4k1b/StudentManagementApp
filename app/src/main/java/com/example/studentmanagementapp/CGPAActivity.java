package com.example.studentmanagementapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CGPAActivity extends AppCompatActivity {

    TextView cgpaText;
    Button backToDashboard;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa);

        cgpaText = findViewById(R.id.cgpaText);
        backToDashboard = findViewById(R.id.backToDashboard);
        db = new DBHelper(this);

        SharedPreferences sp = getSharedPreferences("StudentPrefs", MODE_PRIVATE);
        String studentId = sp.getString("studentId", "");
        double cgpa = db.getCGPA(studentId);
        cgpaText.setText("Your CGPA: " + cgpa);

        backToDashboard.setOnClickListener(v -> {
            startActivity(new Intent(CGPAActivity.this, DashboardActivity.class));
            finish();
        });
    }
}