package com.example.dwp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText phoneTextt;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dayworkplan-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText phone = findViewById(R.id.phone);
        final EditText password = findViewById(R.id.password);
        final Button loginbtn = findViewById(R.id.buttonSignin);



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification();
                final String phonetext = phone.getText().toString();
                final String passwordtext = password.getText().toString();



                if(phonetext.isEmpty() || passwordtext.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Please Enter your mobile No: or Password",Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("usersstudent").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            // check if mobile/phone is exist
                            if(snapshot.hasChild(phonetext)){

                                //mobile is exist in firebase database

                                final String getPassword = snapshot.child(phonetext).child("password").getValue(String.class);

                                if(getPassword.equals(passwordtext)){
                                    Toast.makeText(LoginActivity.this,"Successfully Logged in", Toast.LENGTH_SHORT).show();

                                    //open activity when sucess

                                    //send the phone number to MainActvity 3
                                    Intent phoneNoSend = new Intent(LoginActivity.this,MainActivity3.class);
                                    phoneNoSend.putExtra("PTEXT", phone.getText().toString());
                                    startActivity(phoneNoSend);
                                    finish();
                                }

                                else{
                                    Toast.makeText(LoginActivity.this,"Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(LoginActivity.this,"Wrong Mobile Number", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        TextView textView = findViewById(R.id.text_to_signInActivity);
        String text = "Do you have an Account? Sign Up";

        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent1 = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent1);
            }
        };



        ss.setSpan(clickableSpan1,24,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }

    private void notification(){

        databaseReference.child("notification").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                final EditText phone = findViewById(R.id.phone);
                final String phonetext = phone.getText().toString();

                final String getDate = snapshot.child(phonetext).child("date").getValue(String.class);
                final String getTeacherName = snapshot.child(phonetext).child("teacher_name").getValue(String.class);
                final String getTitle = snapshot.child(phonetext).child("title").getValue(String.class);




                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel channel = new NotificationChannel("n","n", NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager manager = getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel);

                }
                NotificationCompat.Builder builder = new NotificationCompat.Builder(LoginActivity.this,"n")
                        .setContentText("Code sphare")
                        .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                        .setAutoCancel(true)
                        .setContentText(getTeacherName +" "+ "Send a new "+ getTitle+".\n" + "Last day: "+ getDate);
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(LoginActivity.this);
                managerCompat.notify(999,builder.build());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}