package com.example.dwp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<User> list;



    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.year.setText(user.getYear());
        holder.day.setText(user.getDay());
        holder.month.setText(user.getMonth());
        holder.description.setText(user.getDescription());
        holder.title.setText(user.getTitle());
        holder.bywhome.setText(user.getBywhome());
        holder.time.setText(user.getTime());
        holder.id.setText(user.getId());
        holder.phone.setText(user.getPhone());
        holder.phoneTeacher.setText(user.getPhoneTeacher());
        holder.d.setText(user.getD());

        holder.option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        PopupMenu popup = new PopupMenu(holder.option.getContext(),v);
                        popup.getMenuInflater().inflate(R.menu.popup_menu,popup.getMenu());

                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                                switch (item.getItemId()){
                                    case R.id.item1:
                                        Intent senddeta1 = new Intent(holder.id.getContext(),UpdateActivity.class);
                                        senddeta1.putExtra("DESCRIPTIONT",holder.id.getText().toString());
                                        holder.id.getContext().startActivity(senddeta1);




                                        Toast.makeText(holder.option.getContext(), "Update",Toast.LENGTH_SHORT).show();
                                        return true;

                                    case R.id.item2:
                                        AlertDialog.Builder alertDialogBuilderr = new AlertDialog.Builder(holder.option.getContext());
                                        alertDialogBuilderr.setTitle("Congratulation!!!");
                                        alertDialogBuilderr.setMessage("You,ve successfully completed your work");
                                        alertDialogBuilderr.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                

                                                String phoneS = holder.phone.getText().toString();
                                                String phoneT = holder.phoneTeacher.getText().toString();
                                                String idST = holder.id.getText().toString();
                                                String dateA = " ";
                                                String timeA = "Done!";
                                                String eventA = " ";
                                                String d = "Completed";
                                                        Map<String, Object> map = new HashMap<>();
                                                        map.put("a", dateA.toString());
                                                        map.put("b", timeA.toString());
                                                        map.put("c", eventA.toString());
                                                        map.put("d", d.toString());

                                                        FirebaseDatabase.getInstance().getReference().child(phoneS)
                                                                .child(idST).updateChildren(map)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void unused) {
                                                                        Toast.makeText(holder.option.getContext(), "Success", Toast.LENGTH_SHORT).show();

                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        Toast.makeText(holder.option.getContext(), "Failed update", Toast.LENGTH_SHORT).show();

                                                                    }
                                                                });

                                                        FirebaseDatabase.getInstance().getReference().child(phoneT)
                                                                .child(idST).updateChildren(map)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void unused) {
                                                                        Toast.makeText(holder.option.getContext(), "Success", Toast.LENGTH_SHORT).show();

                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        Toast.makeText(holder.option.getContext(), "Failed update", Toast.LENGTH_SHORT).show();

                                                                    }
                                                                });




                                            }
                                        });
                                        alertDialogBuilderr.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(holder.option.getContext(),"Cancel",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        AlertDialog alertDialogr = alertDialogBuilderr.create();

                                        alertDialogr.show();

                                        return true;

                                    case R.id.item3:
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(holder.option.getContext());
                                        alertDialogBuilder.setTitle("Delete");
                                        alertDialogBuilder.setMessage("Are you sure you want to delete your work?");
                                        alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int i) {
                                                //delete functions here

                                                FirebaseDatabase.getInstance().getReference().child(user.phone)
                                                                .child(user.id).removeValue();
                                                FirebaseDatabase.getInstance().getReference().child(user.id).removeValue();

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

        private MyAdapter adapter;
        Button btndone;

        TextView date, description, time, day, month, year, title, bywhome,id,phone, phoneTeacher, d;
        ImageView option;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            btndone = (Button)itemView.findViewById(R.id.done);


            year = itemView.findViewById(R.id.yearc);
            day = itemView.findViewById(R.id.dayc);
            month = itemView.findViewById(R.id.monthc);
            description = itemView.findViewById(R.id.descriptionx);
            title = itemView.findViewById(R.id.titlex);
            bywhome = itemView.findViewById(R.id.status);
            time = itemView.findViewById(R.id.time);
            option = (ImageView) itemView.findViewById(R.id.options);
            id = itemView.findViewById(R.id.statusc);
            phone = itemView.findViewById(R.id.phonestatus);
            phoneTeacher = itemView.findViewById(R.id.phoneteacheruserentry);
            d = itemView.findViewById(R.id.upcoming);

        }
    }

}
