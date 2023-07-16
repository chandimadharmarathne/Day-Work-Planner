package com.example.dwp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

import org.w3c.dom.Text;

import java.util.Calendar;

public class TeacherTime extends AppCompatActivity implements View.OnClickListener {

    TextView btnDatePicker, btnTimePicker, btnEventPicker, btnDesPicker,descriptionAded, btnID;
    Button btnEventSave;
    TextView txtDate, txtTime, txtEvent, textDatep, textMonthp, textYearp, txtEventt, txtDesSet, txtId, setName;
    private int mYear, mMonth, mDay, mHour, mMinute;
    DatabaseReference rootDatabasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dayworkplan-default-rtdb.firebaseio.com/");
    DatabaseReference databaseUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_time);

        Button btn_goto_main = (Button) findViewById(R.id.btnTeacherView);
        btn_goto_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenta = new Intent(TeacherTime.this,TeacherActivity1.class);
                startActivity(intenta);
            }
        });

        databaseUsers = FirebaseDatabase.getInstance().getReference();

        Bundle phonoid = getIntent().getExtras();
        TextView userId = (TextView) findViewById(R.id.testuseridea);
        userId.setText(phonoid.getString("PHONEIDNUM").toString());

        Bundle phonoid2 = getIntent().getExtras();
        TextView userId2 = (TextView) findViewById(R.id.phonenumberteacher);
        userId2.setText(phonoid2.getString("PHONEIDNUMMM").toString());


        btnDatePicker = (TextView) findViewById(R.id.btn_dateTeacher);
        btnTimePicker = (TextView) findViewById(R.id.btn_timeTeacher);
        btnEventPicker = (TextView) findViewById(R.id.btn_addEventTeacher);
        btnDesPicker = (TextView) findViewById(R.id.btn_addDesTexcher);
        btnEventSave = (Button) findViewById(R.id.btnTeacherSend);
        btnID = (TextView)findViewById(R.id.btn_addUserIDTeacher);
        descriptionAded = (TextView) findViewById(R.id.btn_addDes);

        txtDate = (TextView) findViewById(R.id.btn_dateTeacher);
        txtTime = (TextView) findViewById(R.id.btn_timeTeacher);
        txtEvent = (TextView) findViewById(R.id.btn_addEventTeacher);
        txtEventt = (TextView) findViewById(R.id.btn_addDesTexcher);
        txtDesSet = (TextView) findViewById(R.id.desexampleteacher);
        txtId = (TextView) findViewById(R.id.btn_addUserIDTeacher);
        setName = (TextView) findViewById(R.id.nameset);

        textDatep = (TextView) findViewById(R.id.dateteacher);
        textMonthp = (TextView) findViewById(R.id.monthteacher);
        textYearp = (TextView) findViewById(R.id.yearteacher);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnEventPicker.setOnClickListener(this);
        btnID.setOnClickListener(this);
        btnDesPicker.setOnClickListener(this);
        btnEventSave.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        final TextView phonetxt = findViewById(R.id.testuseridea);
        String phonetxtA = phonetxt.getText().toString();

        final TextView teacheID = findViewById(R.id.phonenumberteacher);
        String teacherIDD = teacheID.getText().toString();




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

        final TextView dateA = findViewById(R.id.btn_dateTeacher);
        String dateAA = dateA.getText().toString();

        final TextView dateB = findViewById(R.id.dateteacher);
        String dateBB = dateB.getText().toString();
        final TextView dateC = findViewById(R.id.monthteacher);
        String dateCC = dateC.getText().toString();
        final TextView dateD = findViewById(R.id.yearteacher);
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

        final TextView timeA = findViewById(R.id.btn_timeTeacher);
        String timeAA = timeA.getText().toString();
        int z = mHour;
        /////////////////////////////////////////////////////////////////////////
        if(v==btnID){



            AlertDialog.Builder myDislogid = new AlertDialog.Builder(TeacherTime.this);
            myDislogid.setTitle("Add Student Id");

            final EditText eventInputid = new EditText(TeacherTime.this);
            eventInputid.setInputType(InputType.TYPE_CLASS_TEXT);
            myDislogid.setView(eventInputid);

            myDislogid.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    final String myTextid = eventInputid.getText().toString();
                    txtId.setText(String.valueOf(myTextid));
                }
            });

            myDislogid.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            myDislogid.show();


        }

        final TextView idNumberA = findViewById(R.id.btn_addUserIDTeacher);
        String idAA = idNumberA.getText().toString();

        //////////////////////////////////////////////////////////////////////////////////////////////


        if(v==btnEventPicker){



            AlertDialog.Builder myDislog = new AlertDialog.Builder(TeacherTime.this);
            myDislog.setTitle("Add your Title");

            final EditText eventInput = new EditText(TeacherTime.this);
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

        final TextView eventA = findViewById(R.id.btn_addEventTeacher);
        String eventAA = eventA.getText().toString();

        //////////////////////////////////////////////////////////////////////////////////////////////
        if(v==btnDesPicker){



            AlertDialog.Builder myDislog = new AlertDialog.Builder(TeacherTime.this);
            myDislog.setTitle("Add Description");

            final EditText eventInputt = new EditText(TeacherTime.this);
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

        final TextView eventAB = findViewById(R.id.btn_addDesTexcher);
        String eventAAB = eventAB.getText().toString();

        //////////////////////////////////////////////////////////////////////////////////////////////

        if(v==btnEventSave){


            //////////////////////////////////////////////////////////
            final TextView teacherNme = findViewById(R.id.nameset);
            String teachername = teacherNme.getText().toString();
            String id = rootDatabasereference.push().getKey();

            rootDatabasereference.child(idAA).child(id).addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    rootDatabasereference.child(idAA).child(id).child("description").setValue(eventAAB);
                    rootDatabasereference.child(idAA).child(id).child("title").setValue(eventAA);
                    rootDatabasereference.child(idAA).child(id).child("date").setValue(dateAA);
                    rootDatabasereference.child(idAA).child(id).child("timex").setValue(timeAA);
                    if(z<12) {
                        rootDatabasereference.child(idAA).child(id).child("time").setValue("Time: "+timeAA+" a.m.");
                    }else{
                        rootDatabasereference.child(idAA).child(id).child("time").setValue("Time: "+timeAA+" p.m.");
                    }
                    rootDatabasereference.child(idAA).child(id).child("day").setValue(dateBB);
                    rootDatabasereference.child(idAA).child(id).child("month").setValue(dateCC);
                    rootDatabasereference.child(idAA).child(id).child("year").setValue(dateDD);
                    rootDatabasereference.child(idAA).child(id).child("bywhome").setValue(phonetxtA);
                    rootDatabasereference.child(idAA).child(id).child("id").setValue(id);
                    rootDatabasereference.child(idAA).child(id).child("phone").setValue(idAA);
                    rootDatabasereference.child(idAA).child(id).child("phoneTeacher").setValue(teacherIDD);
                    rootDatabasereference.child(idAA).child(id).child("a").setValue("Not");
                    rootDatabasereference.child(idAA).child(id).child("b").setValue("Complete");
                    rootDatabasereference.child(idAA).child(id).child("c").setValue("yet");
                    rootDatabasereference.child(idAA).child(id).child("d").setValue("Upcoming");
                    rootDatabasereference.child(id).child("phone").setValue(idAA);
                    rootDatabasereference.child(id).child("phoneTeacher").setValue(teacherIDD);




                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            // for teacher

            rootDatabasereference.child(teacherIDD).child(id).addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    rootDatabasereference.child(teacherIDD).child(id).child("description").setValue(eventAAB);
                    rootDatabasereference.child(teacherIDD).child(id).child("title").setValue(eventAA);
                    rootDatabasereference.child(teacherIDD).child(id).child("date").setValue(dateAA);
                    rootDatabasereference.child(teacherIDD).child(id).child("timex").setValue(timeAA);
                    if(z<12) {
                        rootDatabasereference.child(teacherIDD).child(id).child("time").setValue("Time: "+timeAA+" a.m.");
                    }else{
                        rootDatabasereference.child(teacherIDD).child(id).child("time").setValue("Time: "+timeAA+" p.m.");
                    }
                    rootDatabasereference.child(teacherIDD).child(id).child("day").setValue(dateBB);
                    rootDatabasereference.child(teacherIDD).child(id).child("month").setValue(dateCC);
                    rootDatabasereference.child(teacherIDD).child(id).child("year").setValue(dateDD);
                    rootDatabasereference.child(teacherIDD).child(id).child("bywhome").setValue(phonetxtA);
                    rootDatabasereference.child(teacherIDD).child(id).child("id").setValue(id);
                    rootDatabasereference.child(teacherIDD).child(id).child("phone").setValue(idAA);
                    rootDatabasereference.child(teacherIDD).child(id).child("phoneTeacher").setValue(teacherIDD);
                    rootDatabasereference.child(teacherIDD).child(id).child("a").setValue("Not");
                    rootDatabasereference.child(teacherIDD).child(id).child("b").setValue("Complete");
                    rootDatabasereference.child(teacherIDD).child(id).child("c").setValue("yet");
                    rootDatabasereference.child(teacherIDD).child(id).child("d").setValue("Upcoming");





                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



            ///

            rootDatabasereference.child("notification").addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    rootDatabasereference.child("notification").child(idAA).child("teacher_name").setValue(phonetxtA);
                    rootDatabasereference.child("notification").child(idAA).child("date").setValue(dateAA);
                    rootDatabasereference.child("notification").child(idAA).child("title").setValue(eventAA);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            Toast.makeText(TeacherTime.this,"Saved your Event",Toast.LENGTH_SHORT).show();
        }



    }
}