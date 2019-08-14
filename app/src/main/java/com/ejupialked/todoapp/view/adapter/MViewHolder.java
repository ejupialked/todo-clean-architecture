package com.ejupialked.todoapp.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.view.presenter.TaskTypesPresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MViewHolder extends RecyclerView.ViewHolder{

    private final TaskTypesPresenter presenter;

    @BindView(R.id.imageTaskType)  ImageView imageView;
    @BindView(R.id.taskType)       TextView taskTypeName;
    @BindView(R.id.numberTasks)    TextView numberOfTasks;
    @BindView(R.id.parent_layout)  RelativeLayout parentLayout;



    public MViewHolder(@NonNull View itemView, @NonNull TaskTypesPresenter presenter) {
        super(itemView);

        this.presenter = presenter;
        ButterKnife.bind(this, itemView);
    }

    void render(TypeTask typeTask) {
        renderImage();
        renderTypeTaskName(typeTask.getName());
        renderTypeTaskNumber(typeTask.getTasks());

    }

    private void renderTypeTaskNumber(int tasks) {
        numberOfTasks.setText(tasks);
    }

    private void renderTypeTaskName(String name) {
        taskTypeName.setText(name);
    }


    private void renderImage() {
        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/d/db/Android_robot_2014.svg").fit().centerCrop().into(imageView);
    }

    private Context getContext() {
        return itemView.getContext();
    }
}