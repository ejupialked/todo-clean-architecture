package com.ejupialked.todoapp.view.customview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ejupialked.todoapp.R;

import java.util.Calendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomDialogTask extends AppCompatDialogFragment {

    private Context context;

    @BindView(R.id.edit_taskdescription) EditText editDescription;
    @BindView(R.id.radiogroup_task) RadioGroup radioGroup;
    @BindView(R.id.date_button)
    ImageButton dateButton;

    @BindView(R.id.txt_date)
    TextView txtDate;
    @BindView(R.id.txt_time)
    TextView txtTime;


    private RadioButton radioButton;

    private CustomDialogTask.CustomDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();

        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.layout_dialogtask, null);

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


        dateButton.setOnClickListener(view1 -> {
            chooseDate();
        });
        return builder.create();
    }



    private void chooseDate(){
        Calendar calendar = Calendar.getInstance();


        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (datePicker, i, i1, i2) -> {

            String date = i + "/" + i2 + "/" + i2;
            txtDate.setText(date);
            chooseTime();


        }, YEAR, MONTH, DATE);

        datePickerDialog.show();

    }

    private void chooseTime() {
        Calendar calendar = Calendar.getInstance();


        int MINUTE = calendar.get(Calendar.MINUTE);
        int HOUR = calendar.get(Calendar.HOUR);

        TimePickerDialog timePickerDialog = new TimePickerDialog(context, (timePicker, i, i1) -> {

            String time = i+ ":" + i1;

            txtTime.setText(time);


        }, HOUR, MINUTE, true);

        timePickerDialog.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
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
