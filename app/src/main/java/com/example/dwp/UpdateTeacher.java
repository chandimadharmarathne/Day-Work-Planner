package com.example.dwp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UpdateTeacher extends AppCompatActivity {

    TextView txtdate, txttime, txttitle, txtdes, phonetxt, txtuserid;
    TextView btnDatePicker, btnTimePicker, btnEventPicker, btnDesPicker, btnIdPicker;
    Button btnEventSave;
    TextView txtDate, txtTime, txtEvent, textDatep, textMonthp, textYearp, txtEventt, txtDesSet, txtday, txtyear, txtmonth, txttimeampm, txtUserIdP;
    private int mYear, mMonth, mDay, mHour, mMinute;

    DatabaseReference rootDatabasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dayworkplan-default-rtdb.firebaseio.com/");

    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teacher);

        btnDatePicker = (TextView) findViewById(R.id.teacherbtn_dateTeacher);
        btnTimePicker = (TextView) findViewById(R.id.teacherbtn_timeTeacher);
        btnEventPicker = (TextView) findViewById(R.id.teacherbtn_addEventTeacher);
        btnDesPicker = (TextView) findViewById(R.id.teacherbtn_addDesTexcher);
        btnIdPicker = (TextView) findViewById(R.id.teacherbtn_addUserIDTeacher);
        btnEventSave = (Button) findViewById(R.id.teacherbtnTeacherSend);


        txtDate = (TextView) findViewById(R.id.teacherbtn_dateTeacher);
        txtTime = (TextView) findViewById(R.id.teacherbtn_timeTeacher);
        txtUserIdP = (TextView) findViewById(R.id.teacherbtn_addUserIDTeacher);
        txtEvent = (TextView) findViewById(R.id.teacherbtn_addEventTeacher);
        txtDesSet = (TextView) findViewById(R.id.teacherbtn_addDesTexcher);

        textDatep = (TextView) findViewById(R.id.teacherdateteacher);
        textMonthp = (TextView) findViewById(R.id.teachermonthteacher);
        textYearp = (TextView) findViewById(R.id.teacheryearteacher);
        txtEventt = (TextView) findViewById(R.id.teacherdesexampleteacher);


        databaseUsers = FirebaseDatabase.getInstance().getReference();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dayworkplan-default-rtdb.firebaseio.com/");


        Bundle getDescription = getIntent().getExtras();
        TextView descriptionText = (TextView) findViewById(R.id.teacherdateteacher);
        descriptionText.setText(getDescription.getString("DESCRIPTIONT").toString());


        String idKEY = descriptionText.getText().toString();

        txtdate = (TextView) findViewById(R.id.teacherbtn_dateTeacher);
        txttime = (TextView) findViewById(R.id.teacherbtn_timeTeacher);
        txtuserid = (TextView) findViewById(R.id.teacherbtn_addUserIDTeacher);
        txttitle = (TextView) findViewById(R.id.teacherbtn_addEventTeacher);
        txtdes = (TextView) findViewById(R.id.teacherbtn_addDesTexcher);

        txtday = (TextView) findViewById(R.id.teacherdesexampleteacher);
        txtmonth = (TextView) findViewById(R.id.teachermonthteacher);
        txtyear = (TextView) findViewById(R.id.teacheryearteacher);
        phonetxt = (TextView) findViewById(R.id.teacherphonetextupdate);
        txttimeampm = (TextView) findViewById(R.id.teachernameset);

        databaseReference.child(idKEY).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                final String getphone = snapshot.child("phone").getValue(String.class);
                phonetxt.setText(String.valueOf(getphone));
                //secon database start
                databaseReference.child(getphone).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String datear = snapshot.child(idKEY).child("date").getValue(String.class);
                        String timear = snapshot.child(idKEY).child("timex").getValue(String.class);
                        String timear2 = snapshot.child(idKEY).child("timex").getValue(String.class);
                        String titlear = snapshot.child(idKEY).child("title").getValue(String.class);
                        String descriptionar = snapshot.child(idKEY).child("description").getValue(String.class);
                        String dayar = snapshot.child(idKEY).child("day").getValue(String.class);
                        String monthar = snapshot.child(idKEY).child("month").getValue(String.class);
                        String yearar = snapshot.child(idKEY).child("year").getValue(String.class);
                        String useridar = snapshot.child(idKEY).child("phoneTeacher").getValue(String.class);

                        txtdate.setText(String.valueOf(datear));
                        txttime.setText(String.valueOf(timear));
                        txttimeampm.setText(String.valueOf(timear2));
                        txttitle.setText(String.valueOf(titlear));
                        txtdes.setText(String.valueOf(descriptionar));
                        txtday.setText(String.valueOf(dayar));
                        txtmonth.setText(String.valueOf(monthar));
                        txtyear.setText(String.valueOf(yearar));
                        txtuserid.setText(String.valueOf(useridar));


                        final TextView idd = findViewById(R.id.teacherdateteacher);
                        String id = idd.getText().toString();

                        final TextView phoneA = findViewById(R.id.teacherphonetextupdate);
                        String phoneAA = phoneA.getText().toString();

// button date picker
                        btnDatePicker.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Get Current Date
                                final Calendar c = Calendar.getInstance();
                                mYear = c.get(Calendar.YEAR);
                                mMonth = c.get(Calendar.MONTH);
                                mDay = c.get(Calendar.DAY_OF_MONTH);


                                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateTeacher.this,
                                        new DatePickerDialog.OnDateSetListener() {

                                            @Override
                                            public void onDateSet(DatePicker view, int year,
                                                                  int monthOfYear, int dayOfMonth) {

                                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                                txtEventt.setText(dayOfMonth + "");

                                                textYearp.setText(year + "");

                                                if ((monthOfYear + 1) == 1) {
                                                    textMonthp.setText("Jan");
                                                } else if ((monthOfYear + 1) == 2) {
                                                    textMonthp.setText("Feb");
                                                } else if ((monthOfYear + 1) == 3) {
                                                    textMonthp.setText("Mar");
                                                } else if ((monthOfYear + 1) == 4) {
                                                    textMonthp.setText("Apr");
                                                } else if ((monthOfYear + 1) == 5) {
                                                    textMonthp.setText("May");
                                                } else if ((monthOfYear + 1) == 6) {
                                                    textMonthp.setText("Jun");
                                                } else if ((monthOfYear + 1) == 7) {
                                                    textMonthp.setText("Jul");
                                                } else if ((monthOfYear + 1) == 8) {
                                                    textMonthp.setText("Aug");
                                                } else if ((monthOfYear + 1) == 9) {
                                                    textMonthp.setText("Sep");
                                                } else if ((monthOfYear + 1) == 10) {
                                                    textMonthp.setText("Oct");
                                                } else if ((monthOfYear + 1) == 11) {
                                                    textMonthp.setText("Nov");
                                                } else {
                                                    textMonthp.setText("Dec");
                                                }
                                            }
                                        }, mYear, mMonth, mDay);
                                datePickerDialog.show();
                            }


                        });

                        final TextView dateA = findViewById(R.id.teacherbtn_dateTeacher);
                        String dateAA = dateA.getText().toString();

                        final TextView dateB = findViewById(R.id.teacherdesexampleteacher);
                        String dateBB = dateB.getText().toString();
                        final TextView dateC = findViewById(R.id.teachermonthteacher);
                        String dateCC = dateC.getText().toString();
                        final TextView dateD = findViewById(R.id.teacheryearteacher);
                        String dateDD = dateD.getText().toString();
//Button time picker
                        btnTimePicker.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Get Current Time
                                final Calendar c = Calendar.getInstance();
                                mHour = c.get(Calendar.HOUR_OF_DAY);
                                mMinute = c.get(Calendar.MINUTE);

                                // Launch Time Picker Dialog
                                TimePickerDialog timePickerDialog = new TimePickerDialog(UpdateTeacher.this,
                                        new TimePickerDialog.OnTimeSetListener() {

                                            @Override
                                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                                  int minute) {

                                                txtTime.setText(hourOfDay + ":" + minute);
                                                txttimeampm.setText(hourOfDay + ":" + minute);
                                            }
                                        }, mHour, mMinute, true);
                                timePickerDialog.show();


                            }
                        });

                        final TextView timeA = findViewById(R.id.teacherbtn_timeTeacher);
                        final TextView timeB = findViewById(R.id.teachernameset);


                        String timeAA = timeB.getText().toString();


                        int z = mHour;

// button User id picker
                        btnIdPicker.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder myDislogi = new AlertDialog.Builder(UpdateTeacher.this);
                                myDislogi.setTitle("Sorry!!!");
                                myDislogi.setMessage("You can't update student ID. If you want send activity to new student, Please go through 'Add a new Activity' ");




                                myDislogi.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                                myDislogi.show();
                            }
                        });

                        final TextView eventI = findViewById(R.id.teacherbtn_addUserIDTeacher);
                        String eventII = eventI.getText().toString();



// button Title picker
                        btnEventPicker.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder myDislog = new AlertDialog.Builder(UpdateTeacher.this);
                                myDislog.setTitle("Add your Title");

                                final EditText eventInput = new EditText(UpdateTeacher.this);
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
                        });

                        final TextView eventA = findViewById(R.id.teacherbtn_addEventTeacher);
                        String eventAA = eventA.getText().toString();


// Button DEscription picker
                        btnDesPicker.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                AlertDialog.Builder myDislog = new AlertDialog.Builder(UpdateTeacher.this);
                                myDislog.setTitle("Add Description");

                                final EditText eventInputt = new EditText(UpdateTeacher.this);
                                eventInputt.setInputType(InputType.TYPE_CLASS_TEXT);
                                myDislog.setView(eventInputt);

                                myDislog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        final String myTextt = eventInputt.getText().toString();
                                        txtDesSet.setText(String.valueOf(myTextt));

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
                        });

                        final TextView eventAB = findViewById(R.id.teacherbtn_addDesTexcher);
                        String eventAAB = eventAB.getText().toString();


// save data in firebase
                        btnEventSave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Map<String, Object> map = new HashMap<>();
                                map.put("date", dateA.getText().toString());
                                map.put("timex", timeA.getText().toString());

                                String timeZZ = "Time : " + timeA.getText().toString() + " a.m.";
                                String timeYY = "Time : " + timeA.getText().toString() + " p.m.";

                                if (z < 12) {
                                    map.put("time", timeZZ);
                                } else {
                                    map.put("time", timeYY);
                                }
                                map.put("title", eventA.getText().toString());
                                map.put("description", eventAB.getText().toString());
                                map.put("day", dateB.getText().toString());
                                map.put("month", dateC.getText().toString());
                                map.put("year", dateD.getText().toString());

                                FirebaseDatabase.getInstance().getReference().child(phoneAA)
                                        .child(id).updateChildren(map)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(UpdateTeacher.this, "Success", Toast.LENGTH_SHORT).show();

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(UpdateTeacher.this, "Failed update", Toast.LENGTH_SHORT).show();

                                            }
                                        });

                                FirebaseDatabase.getInstance().getReference().child(eventII)
                                        .child(id).updateChildren(map)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(UpdateTeacher.this, "Success", Toast.LENGTH_SHORT).show();

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(UpdateTeacher.this, "Failed update", Toast.LENGTH_SHORT).show();

                                            }
                                        });


                                //Toast.makeText(UpdateActivity.this, "Saved your Event", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });//end second

                //Toast.makeText(LoginActivity.this,"Successfully Logged in", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}

