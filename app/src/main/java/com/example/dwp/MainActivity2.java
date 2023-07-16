package com.example.dwp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    RecyclerView recyclerView;
    ArrayList<User> list;
    DatabaseReference databaseReference;
    MyAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);





        //receive phone no from x
        Bundle phoneNo = getIntent().getExtras();
        TextView pNo = (TextView) findViewById(R.id.tvPhoneTeachq);
        pNo.setText(phoneNo.getString("HELLOW").toString());


/*
        Button btnViewEvent = findViewById(R.id.btnViewEventTeach);
        btnViewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send phone no to eventlistActivity
                Intent phoneNoSend2 = new Intent(TeacherActivity1.this,TeacherTime.class);
                phoneNoSend2.putExtra("PTEXTTT", pNo.getText().toString());
                startActivity(phoneNoSend2);
                finish();
            }
        });*/


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

    public void showPopup(View view){
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
       switch (item.getItemId()){
           case R.id.item1:
               Toast.makeText(this,"item1",Toast.LENGTH_SHORT).show();
               return true;

           case R.id.item2:
                Dialog completeDialog = new Dialog(this);
                completeDialog.setContentView(R.layout.dialog_congradulation);
                completeDialog.show();
               Toast.makeText(this,"item2",Toast.LENGTH_SHORT).show();
               return true;

           case R.id.item3:
               Toast.makeText(this,"item3",Toast.LENGTH_SHORT).show();
               return true;

           default:
               return false;
       }
    }
}