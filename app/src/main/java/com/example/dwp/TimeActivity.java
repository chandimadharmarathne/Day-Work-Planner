package com.example.dwp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class TimeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView btnDatePicker, btnTimePicker, btnEventPicker, btnDesPicker,descriptionAded;
    ImageButton btnEventSave;
    TextView txtDate, txtTime, txtEvent, textDatep, textMonthp, textYearp, txtEventt, txtDesSet;
    private int mYear, mMonth, mDay, mHour, mMinute;

    DatabaseReference rootDatabasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dayworkplan-default-rtdb.firebaseio.com/");

    DatabaseReference databaseUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);



        databaseUsers = FirebaseDatabase.getInstance().getReference();

        //receive phone user
        Bundle phoneNo = getIntent().getExtras();
        TextView pNoo = (TextView) findViewById(R.id.tvPhonexzy);
        pNoo.setText(phoneNo.getString("HELLOQW").toString());




        btnDatePicker = (TextView) findViewById(R.id.btn_date);
        btnTimePicker = (TextView) findViewById(R.id.btn_time);
        btnEventPicker = (TextView) findViewById(R.id.btn_addEvent);
        btnDesPicker = (TextView) findViewById(R.id.btn_addDes);
        btnEventSave = (ImageButton) findViewById(R.id.saveEvent);
        descriptionAded = (TextView) findViewById(R.id.btn_addDes);

        txtDate = (TextView) findViewById(R.id.btn_date);
        txtTime = (TextView) findViewById(R.id.btn_time);
        txtEvent = (TextView) findViewById(R.id.btn_addEvent);
        txtEventt = (TextView) findViewById(R.id.btn_addDes);
        txtDesSet = (TextView) findViewById(R.id.desexample);

        textDatep = (TextView) findViewById(R.id.datep);
        textMonthp = (TextView) findViewById(R.id.monthp);
        textYearp = (TextView) findViewById(R.id.yearp);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnEventPicker.setOnClickListener(this);
        btnDesPicker.setOnClickListener(this);
        btnEventSave.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        final TextView phoneA = findViewById(R.id.tvPhonexzy);
        String phoneAA = phoneA.getText().toString();




        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            textDatep.setText(dayOfMonth +"");

                            textYearp.setText(year +"");

                            if((monthOfYear + 1)==1){
                                textMonthp.setText("Jan");
                            }else if((monthOfYear + 1)==2){
                                textMonthp.setText("Feb");
                            }else if((monthOfYear + 1)==3){
                                textMonthp.setText("Mar");
                            }else if((monthOfYear + 1)==4){
                                textMonthp.setText("Apr");
                            }else if((monthOfYear + 1)==5){
                                textMonthp.setText("May");
                            }else if((monthOfYear + 1)==6){
                                textMonthp.setText("Jun");
                            }else if((monthOfYear + 1)==7){
                                textMonthp.setText("Jul");
                            }else if((monthOfYear + 1)==8){
                                textMonthp.setText("Aug");
                            }else if((monthOfYear + 1)==9){
                                textMonthp.setText("Sep");
                            }else if((monthOfYear + 1)==10){
                                textMonthp.setText("Oct");
                            }else if((monthOfYear + 1)==11){
                                textMonthp.setText("Nov");
                            }else{
                                textMonthp.setText("Dec");
                            }
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        final TextView dateA = findViewById(R.id.btn_date);
        String dateAA = dateA.getText().toString();

        final TextView dateB = findViewById(R.id.datep);
        String dateBB = dateB.getText().toString();
        final TextView dateC = findViewById(R.id.monthp);
        String dateCC = dateC.getText().toString();
        final TextView dateD = findViewById(R.id.yearp);
        String dateDD = dateD.getText().toString();






        //////////////////////////////////////////////////////////////////////////
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();

        }

        final TextView timeA = findViewById(R.id.btn_time);
        String timeAA = timeA.getText().toString();
        int z = mHour;
        /////////////////////////////////////////////////////////////////////////
        if(v==btnEventPicker){



            AlertDialog.Builder myDislog = new AlertDialog.Builder(TimeActivity.this);
            myDislog.setTitle("Add your Title");

            final EditText eventInput = new EditText(TimeActivity.this);
            eventInput.setInputType(InputType.TYPE_CLASS_TEXT);
            myDislog.setView(eventInput);

            myDislog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    final String myText = eventInput.getText().toString();
                    txtEvent.setText(String.valueOf(myText));
                }
            });

            myDislog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            myDislog.show();


        }

        final TextView eventA = findViewById(R.id.btn_addEvent);
        String eventAA = eventA.getText().toString();

        //////////////////////////////////////////////////////////////////////////////////////////////
        if(v==btnDesPicker){



            AlertDialog.Builder myDislog = new AlertDialog.Builder(TimeActivity.this);
            myDislog.setTitle("Add Description");

            final EditText eventInputt = new EditText(TimeActivity.this);
            eventInputt.setInputType(InputType.TYPE_CLASS_TEXT);
            myDislog.setView(eventInputt);

            myDislog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    final String myTextt = eventInputt.getText().toString();
                    txtEventt.setText(String.valueOf(myTextt));

                }
            });

            myDislog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            myDislog.show();


        }

        final TextView eventAB = findViewById(R.id.desexample);
        String eventAAB = eventAB.getText().toString();

        //////////////////////////////////////////////////////////////////////////////////////////////

        if(v==btnEventSave){

            String id = rootDatabasereference.push().getKey();

            rootDatabasereference.child(phoneAA).addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    rootDatabasereference.child(phoneAA).child(id).child("description").setValue(eventAAB);
                    rootDatabasereference.child(phoneAA).child(id).child("title").setValue(eventAA);
                    rootDatabasereference.child(phoneAA).child(id).child("date").setValue(dateAA);
                    rootDatabasereference.child(phoneAA).child(id).child("timex").setValue(timeAA);
                    if(z<12) {
                        rootDatabasereference.child(phoneAA).child(id).child("time").setValue("Time: "+timeAA+" a.m.");
                    }else{
                        rootDatabasereference.child(phoneAA).child(id).child("time").setValue("Time: "+timeAA+" p.m.");
                    }
                    rootDatabasereference.child(phoneAA).child(id).child("day").setValue(dateBB);
                    rootDatabasereference.child(phoneAA).child(id).child("month").setValue(dateCC);
                    rootDatabasereference.child(phoneAA).child(id).child("year").setValue(dateDD);
                    rootDatabasereference.child(phoneAA).child(id).child("bywhome").setValue(" ");
                    rootDatabasereference.child(phoneAA).child(id).child("id").setValue(id);
                    rootDatabasereference.child(phoneAA).child(id).child("phone").setValue(phoneAA);
                    rootDatabasereference.child(phoneAA).child(id).child("d").setValue("Upcoming");
                    rootDatabasereference.child(id).child("phone").setValue(phoneAA);




                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            rootDatabasereference.child("events").addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    rootDatabasereference.child("events").child(phoneAA).child(dateAA).child(timeAA).child("description").setValue(eventAA);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            Toast.makeText(TimeActivity.this,"Saved your Event",Toast.LENGTH_SHORT).show();
        }



    }
}