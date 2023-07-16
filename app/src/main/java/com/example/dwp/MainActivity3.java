package com.example.dwp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        //receive phone no from x
        Bundle phoneNo = getIntent().getExtras();
        TextView pNo = (TextView) findViewById(R.id.userphonestudent);
        pNo.setText(phoneNo.getString("PTEXT").toString());


        TextView textViewone = (TextView) findViewById(R.id.btngoStudentrActivity);
        textViewone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent textone = new Intent(MainActivity3.this, TimeActivity.class);
                textone.putExtra("HELLOQW",pNo.getText().toString());
                startActivity(textone);
            }
        });

        TextView textViewtwo = (TextView) findViewById(R.id.btnchechStudent);
        textViewtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent texttwo = new Intent(MainActivity3.this, MainActivity2.class);
                texttwo.putExtra("HELLOW",pNo.getText().toString());
                startActivity(texttwo);
            }
        });



    }
}