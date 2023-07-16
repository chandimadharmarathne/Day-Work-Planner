package com.example.dwp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView teacher = (TextView) findViewById(R.id.btn_teacher);

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teacher = new Intent(MainActivity.this,LoginTeaching.class);
                startActivity(teacher);
            }
        });

        TextView student = (TextView) findViewById(R.id.btn_student);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent student = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(student);
            }
        });
    }
}