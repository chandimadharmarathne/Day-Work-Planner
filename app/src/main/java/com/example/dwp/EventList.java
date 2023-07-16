package com.example.dwp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EventList extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<User> list;
    DatabaseReference databaseReference;
    MyAdapter adapter;

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(EventList.this,MainActivity2.class));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        //receive phone user
        Bundle phoneNo = getIntent().getExtras();
        TextView pNo = (TextView) findViewById(R.id.tvPhone2);
        pNo.setText(phoneNo.getString("PTEXTTT").toString());


        ImageButton btnback = (ImageButton) findViewById(R.id.btnBack);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMain2Activity = new Intent(EventList.this,MainActivity2.class);
                startActivity(goToMain2Activity);
            }
        });



        String phoneAA = pNo.getText().toString();

        recyclerView = findViewById(R.id.recycleView);
        databaseReference = FirebaseDatabase.getInstance().getReference(phoneAA);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, list);
        recyclerView.setAdapter(adapter);




        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}