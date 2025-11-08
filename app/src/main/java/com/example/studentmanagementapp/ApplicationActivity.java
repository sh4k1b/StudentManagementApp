package com.example.studentmanagementapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ApplicationActivity extends AppCompatActivity {

    EditText appType, appContent;
    Button sendApp, backToDashboard;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        appType = findViewById(R.id.appType);
        appContent = findViewById(R.id.appContent);
        sendApp = findViewById(R.id.sendApp);
        backToDashboard = findViewById(R.id.backToDashboard);
        db = new DBHelper(this);

        SharedPreferences sp = getSharedPreferences("StudentPrefs", MODE_PRIVATE);
        String studentId = sp.getString("studentId", "");

        sendApp.setOnClickListener(v -> {
            db.sendApplication(studentId, appType.getText().toString(), appContent.getText().toString());
            Toast.makeText(this, "Application sent", Toast.LENGTH_SHORT).show();
        });

        backToDashboard.setOnClickListener(v -> {
            startActivity(new Intent(ApplicationActivity.this, DashboardActivity.class));
            finish();
        });
    }
}
