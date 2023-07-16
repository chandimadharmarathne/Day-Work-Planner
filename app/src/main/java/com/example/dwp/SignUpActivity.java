package com.example.dwp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class SignUpActivity extends AppCompatActivity {

    // Create object of Databasereference class to acess the realtime database

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dayworkplan-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText fullname = findViewById(R.id.fullName);
        final EditText email = findViewById(R.id.email);
        final EditText phone = findViewById(R.id.phone);
        final EditText password = findViewById(R.id.password);
        final EditText conpassword = findViewById(R.id.conpassword);

        final Button registerBtn = findViewById(R.id.buttonSignUp);



        TextView textView = findViewById(R.id.text_to_signInActivityformSignUp);
        String text = "Already have an account? Sign in";

        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent1);
            }
        };

        ss.setSpan(clickableSpan1,25,32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get data from Edit text into String Variables
                final String fullnameText = fullname.getText().toString();
                final String emailText = email.getText().toString();
                final String phoneText = phone.getText().toString();
                final String passwordText = password.getText().toString();
                final String conpasswordText = conpassword.getText().toString();


                // check user fill all the field before sending data
                if(fullnameText.isEmpty() || emailText.isEmpty() || phoneText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(SignUpActivity.this,"Please fill the all fields", Toast.LENGTH_SHORT).show();
                }
                // check if password are matching with each other

                else if(!passwordText.equals(conpasswordText)){
                    Toast.makeText(SignUpActivity.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                else{

                    databaseReference.child("usersstudent").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            // check if phone number registerd before

                            if(snapshot.hasChild(phoneText)){
                                Toast.makeText(SignUpActivity.this,"Phone is already registerd",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                // Sending data to firebase

                                databaseReference.child("usersstudent").child(phoneText).child("fullname").setValue(fullnameText);
                                databaseReference.child("usersstudent").child(phoneText).child("email").setValue(emailText);
                                databaseReference.child("usersstudent").child(phoneText).child("password").setValue(passwordText);

                                //Show sucsess message
                                Toast.makeText(SignUpActivity.this,"User registered Successfully",Toast.LENGTH_SHORT).show();
                                //send the phone number to MainActvity 2
                                Intent phoneNoSend = new Intent(SignUpActivity.this,MainActivity3.class);
                                phoneNoSend.putExtra("PTEXT", phone.getText().toString());
                                startActivity(phoneNoSend);
                                finish();
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