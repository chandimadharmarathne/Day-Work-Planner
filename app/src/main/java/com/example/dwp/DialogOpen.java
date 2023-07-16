package com.example.dwp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogOpen extends AppCompatDialogFragment {
    private EditText editTexttitle;
    private EditText editTextDescription;
    private OpenDialogListner listner;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_show,null);

        builder.setView(view)
                .setTitle("Login")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String title = editTexttitle.getText().toString();
                        String description = editTextDescription.getText().toString();
                        listner.applyTexts(title, description);
                    }
                });
        editTexttitle = view.findViewById(R.id.titleD);
        editTextDescription = view.findViewById(R.id.desD);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listner = (OpenDialogListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "Must implement OpenDialogListner");
        }
    }

    public interface OpenDialogListner{
        void applyTexts(String title, String description);
    }
}
