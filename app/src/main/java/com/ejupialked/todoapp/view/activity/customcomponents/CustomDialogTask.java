package com.ejupialked.todoapp.view.activity.customcomponents;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ejupialked.todoapp.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomDialogTask extends AppCompatDialogFragment {


    @BindView(R.id.edit_taskdescription) EditText editDescription;

    @BindView(R.id.radiogroup_task)
    RadioGroup radioGroup;
    private RadioButton radioButton;
    private CustomDialogTask.CustomDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.layout_dialogtask, null);

        ButterKnife.bind(this, view);

        builder.setView(view)
                .setTitle("Create a new task")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                })
                .setPositiveButton("Create", (dialogInterface, i) -> {


                    int ID = radioGroup.getCheckedRadioButtonId();
                    radioButton = view.findViewById(ID);

                    String taskDescription = editDescription.getText().toString();
                    String taskPriority = radioButton.getText().toString();

                    listener.applyTask(taskDescription, taskPriority);
                });
        return  builder.create();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (CustomDialogTask.CustomDialogListener) context;
        }catch (ClassCastException c){
            throw new ClassCastException(context.toString() + "Must implement listener ");
        }
    }

    public interface CustomDialogListener{
        void applyTask(String description, String priority);
    }
}
