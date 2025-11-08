package com.example.studentmanagementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "StudentDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE students(id TEXT PRIMARY KEY, cgpa REAL)");
        db.execSQL("CREATE TABLE courses(studentId TEXT, courseName TEXT)");
        db.execSQL("CREATE TABLE applications(id INTEGER PRIMARY KEY AUTOINCREMENT, studentId TEXT, type TEXT, content TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS students");
        db.execSQL("DROP TABLE IF EXISTS courses");
        db.execSQL("DROP TABLE IF EXISTS applications");
        onCreate(db);
    }

    public boolean registerStudent(String id) {
        if (studentExists(id)) return false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("cgpa", 4.0); // default CGPA
        db.insert("students", null, cv);
        return true;
    }

    public boolean studentExists(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM students WHERE id = ?", new String[]{id});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    public boolean isCourseAlreadyEnrolled(String studentId, String courseName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM courses WHERE studentId = ? AND courseName = ?", new String[]{studentId, courseName});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    public boolean enrollCourse(String studentId, String courseName) {
        if (!isCourseAlreadyEnrolled(studentId, courseName)) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("studentId", studentId);
            cv.put("courseName", courseName);
            db.insert("courses", null, cv);
            return true;
        }
        return false;
    }

    public void sendApplication(String studentId, String type, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("studentId", studentId);
        cv.put("type", type);
        cv.put("content", content);
        db.insert("applications", null, cv);
    }

    public double getCGPA(String studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT cgpa FROM students WHERE id = ?", new String[]{studentId});
        if (cursor.moveToFirst()) {
            return cursor.getDouble(0);
        }
        return 0.0;
    }

    public List<String> getEnrolledCourses(String studentId) {
        List<String> courses = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT courseName FROM courses WHERE studentId = ?", new String[]{studentId});
        while (cursor.moveToNext()) {
            courses.add(cursor.getString(0));
        }
        cursor.close();
        return courses;
    }
}
