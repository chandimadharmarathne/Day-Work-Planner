package com.example.dwp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginTeaching extends AppCompatActivity {

    private EditText phoneTextt;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dayworkplan-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teaching);


        final EditText phoneTeacher = findViewById(R.id.phoneTeacher);
        final EditText passwordTeacher = findViewById(R.id.passwordTeacher);
        final Button loginbtnTeacher = findViewById(R.id.buttonSigninTeacher);


        loginbtnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String phonetextTeacher = phoneTeacher.getText().toString();
                final String passwordtextTeacher = passwordTeacher.getText().toString();


                if (phonetextTeacher.isEmpty() || passwordtextTeacher.isEmpty()) {
                    Toast.makeText(LoginTeaching.this, "Please Enter your mobilr No or Password", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            // check if mobile/phone is exist
                            if (snapshot.hasChild(phonetextTeacher)) {

                                //mobile is exist in firebase database

                                final String getPassword = snapshot.child(phonetextTeacher).child("password").getValue(String.class);

                                if (getPassword.equals(passwordtextTeacher)) {
                                    Toast.makeText(LoginTeaching.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();

                                    //open activity when sucess

                                    //send the phone number to MainActvity 2
                                    Intent phoneNoSend = new Intent(LoginTeaching.this, TeacherActivity1.class);
                                    phoneNoSend.putExtra("IDNUMBER", phoneTeacher.getText().toString());
                                    startActivity(phoneNoSend);
                                    finish();
                                } else {
                                    Toast.makeText(LoginTeaching.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginTeaching.this, "Wrong Mobile Number", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }
}