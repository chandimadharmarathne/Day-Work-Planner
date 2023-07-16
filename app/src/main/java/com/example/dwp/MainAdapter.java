package com.example.dwp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    Context context;
    ArrayList<MainModem> list;

    public MainAdapter(Context context, ArrayList<MainModem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MainAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.teacherprogress,parent,false);

        return new MainAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MainModem mainModem = list.get(position);
        holder.a.setText(mainModem.getA());
        holder.b.setText(mainModem.getB());
        holder.c.setText(mainModem.getC());
        holder.date.setText(mainModem.getDate());
        holder.title.setText(mainModem.getTitle());
        holder.phone.setText(mainModem.getPhone());
        holder.phoneTeacher.setText(mainModem.getPhoneTeacher());
        holder.id.setText(mainModem.getId());

        holder.option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PopupMenu popup = new PopupMenu(holder.option.getContext(),v);
                popup.getMenuInflater().inflate(R.menu.popup_mene_teacher,popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.itemteacher1:
                                Intent senddeta1 = new Intent(holder.id.getContext(),UpdateTeacher.class);
                                senddeta1.putExtra("DESCRIPTIONT",holder.id.getText().toString());
                                holder.id.getContext().startActivity(senddeta1);
                                Toast.makeText(holder.option.getContext(), "Edit",Toast.LENGTH_SHORT).show();
                                return true;


                            case R.id.itemteacher2:
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(holder.option.getContext());
                                alertDialogBuilder.setTitle("Delete");
                                alertDialogBuilder.setMessage("Are you sure you want to delete this work?");
                                alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        //delete functions here

                                        FirebaseDatabase.getInstance().getReference().child(mainModem.phoneTeacher)
                                                .child(mainModem.id).removeValue();
                                        FirebaseDatabase.getInstance().getReference().child(mainModem.phone)
                                                .child(mainModem.id).removeValue();
                                        FirebaseDatabase.getInstance().getReference().child(mainModem.id).removeValue();


                                        Toast.makeText(holder.option.getContext(),"Deleted",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        Toast.makeText(holder.option.getContext(),"Cancel",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                AlertDialog alertDialog = alertDialogBuilder.create();

                                alertDialog.show();
                                Toast.makeText(holder.option.getContext(),"Delete?",Toast.LENGTH_SHORT).show();
                                return true;

                            default:
                                return false;
                        }

                        //Toast.makeText(holder.option.getContext(),"You vlicked :" + item.getTitle(),Toast.LENGTH_SHORT).show();
                        //return false;
                    }
                });
                popup.show();





            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private MainAdapter mainAdapter;
        Button btndone;

        TextView date, a, b, c, title, phone, phoneTeacher,id;
        ImageView option;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            btndone = (Button)itemView.findViewById(R.id.done);


            a = itemView.findViewById(R.id.notTeacher);
            b = itemView.findViewById(R.id.completedTeacher);
            c = itemView.findViewById(R.id.yetTeacher);
            date = itemView.findViewById(R.id.titleTeacherp);
            title = itemView.findViewById(R.id.idteacher);
            phone = itemView.findViewById(R.id.nameTeacher);
            phoneTeacher = itemView.findViewById(R.id.teacherphonestatus);
            id = itemView.findViewById(R.id.teacherupcoming);

            option = (ImageView) itemView.findViewById(R.id.optionsteacher);


        }
    }
}
