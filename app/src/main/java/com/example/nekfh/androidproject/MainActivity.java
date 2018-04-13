package com.example.nekfh.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void movToNewStd(View v){
        Intent i = new Intent(this, new_student.class);
        startActivity(i);
        finish();
    }

    public void movToStdGPA(View v){
        Intent i = new Intent(this, student_query.class);
        startActivity(i);
        finish();
    }
}
