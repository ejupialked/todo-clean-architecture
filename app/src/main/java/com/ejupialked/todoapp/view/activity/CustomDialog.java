package com.ejupialked.todoapp.view.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ejupialked.todoapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomDialog extends AppCompatDialogFragment {


    @BindView(R.id.edit_tasktype)
    EditText editText;


    private CustomDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialogtypetask, null);

        ButterKnife.bind(this, view);

        builder.setView(view)
                .setTitle("Create new task type")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String taskName = editText.getText().toString();
                        listener.applyTask(taskName);
                    }
                });


        return  builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (CustomDialogListener) context;

        }catch (ClassCastException c){
            throw new ClassCastException(context.toString() + "Must implement listener ");
        }
    }

    public interface  CustomDialogListener{
        void applyTask(String taskName);
    }
}
