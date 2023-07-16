package com.example.dwp;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class TeacherProgress extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<MainModem> list;
    DatabaseReference databaseReference;
    MainAdapter mainAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_progress);





        //receive phone no from x
        Bundle phoneNo = getIntent().getExtras();
        TextView pNo = (TextView) findViewById(R.id.savephoneTeacher);
        pNo.setText(phoneNo.getString("PHONEIDNUMM").toString());

        Bundle phoneNoo = getIntent().getExtras();
        TextView pNoo = (TextView) findViewById(R.id.tvPhoneTeachqprogress);
        pNoo.setText(phoneNoo.getString("PHONEIDNUMMMM").toString());



        String phoneAA = pNo.getText().toString();

        recyclerView = findViewById(R.id.rv);
        databaseReference = FirebaseDatabase.getInstance().getReference(phoneAA);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainAdapter = new MainAdapter(this, list);
        recyclerView.setAdapter(mainAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    MainModem mainModem = dataSnapshot.getValue(MainModem.class);
                    list.add(mainModem);
                }
                mainAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }


}