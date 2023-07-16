package com.example.dwp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class TeacherActivity1 extends AppCompatActivity {
    TextView setName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher1);

        Bundle getPhoneId = getIntent().getExtras();
        TextView setPhoneId = (TextView) findViewById(R.id.userphone);
        setPhoneId.setText(getPhoneId.getString("IDNUMBER"));

        setName = (TextView) findViewById(R.id.useridtest);
        final TextView phonetxt = findViewById(R.id.userphone);
        String phonetxtA = phonetxt.getText().toString();


        DatabaseReference rootDatabasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dayworkplan-default-rtdb.firebaseio.com/");
        DatabaseReference databaseUsers;

        rootDatabasereference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                //mobile is exist in firebase database

                final String getNeme = snapshot.child(phonetxtA).child("fullname").getValue(String.class);
                setName.setText(String.valueOf(getNeme));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        final TextView teNameA = findViewById(R.id.useridtest);
        String tNAmeB = teNameA.getText().toString();

        TextView btnGoToTeachrtActivity = (TextView) findViewById(R.id.btngoTeacherActivity);
        btnGoToTeachrtActivity.setOnClickListener(v -> {
            Intent intent = new Intent(TeacherActivity1.this,TeacherTime.class);
            intent.putExtra("PHONEIDNUM",teNameA.getText().toString());
            intent.putExtra("PHONEIDNUMMM",phonetxt.getText().toString());
            startActivity(intent);
        });

        TextView btnViewProgress = (TextView) findViewById(R.id.btnchech);
        btnViewProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewProgress = new Intent(TeacherActivity1.this,TeacherProgress.class);
                viewProgress.putExtra("PHONEIDNUMMMM",teNameA.getText().toString());
                viewProgress.putExtra("PHONEIDNUMM",phonetxt.getText().toString());
                startActivity(viewProgress);
            }
        });






    }
}