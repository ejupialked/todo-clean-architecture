package com.ejupialked.todoapp.view.activity.customcomponents;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.ejupialked.todoapp.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomDialogTaskType extends DialogFragment {


    @BindView(R.id.edit_tasktype)
    EditText editText;

    private CustomDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();


        View view = inflater.inflate(R.layout.layout_dialogtypetask, null);

        ButterKnife.bind(this, view);



        builder.setView(view)
                .setTitle("Create new task type")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

                })
                .setPositiveButton("Create", (dialogInterface, i) -> {
                    String taskName = editText.getText().toString();
                    listener.applyTask(taskName);
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
