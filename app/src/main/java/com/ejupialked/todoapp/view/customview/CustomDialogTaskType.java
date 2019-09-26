package com.ejupialked.todoapp.view.customview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.view.adapter.RecycleViewAdapterIcons;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomDialogTaskType extends DialogFragment {


    @BindView(R.id.edit_tasktype)
    EditText editText;


    @BindView(R.id.icon_recycle_view)
    RecyclerView recyclerViewIcons;


    RecycleViewAdapterIcons recycleViewAdapterIcons;


    private CustomDialogListener listener;

    private String title;
    private String positiveButton;

    TypeTask t;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();


        View view = inflater.inflate(R.layout.layout_dialogtypetask, null);

        ButterKnife.bind(this, view);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewIcons.setLayoutManager(linearLayoutManager);

        recycleViewAdapterIcons = new RecycleViewAdapterIcons();
        recyclerViewIcons.setAdapter(recycleViewAdapterIcons);

        if(t == null){
            title = "Create a task type";
            positiveButton = "Create";

        }else {
            title = "Edit task type";
            positiveButton = "Edit";
            editText.setText(t.getName());
        }

        builder.setView(view)
                .setTitle(title)
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                });
        if(t == null) {
            builder.setPositiveButton(positiveButton, (dialogInterface, i) -> {
                String taskName = editText.getText().toString();
                listener.createTypeTask(taskName);
            });
        }else{
            builder.setPositiveButton(positiveButton, (dialogInterface, i) -> {
                t.setName(editText.getText().toString());
                listener.editTypeTask(t);
            });
        }

        return  builder.create();
    }

    public void setT(TypeTask t) {
        this.t = t;
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

    public interface CustomDialogListener{
        void createTypeTask(String newTaskName);
        void editTypeTask(TypeTask t);
    }
}
