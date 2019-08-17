package com.ejupialked.todoapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.view.presenter.TaskTypesPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    private final TaskTypesPresenter presenter;
    private final List<TypeTask> taskList;

    public RecyclerViewAdapter(TaskTypesPresenter presenter) {
        this.presenter = presenter;
        this.taskList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tasktype, parent, false);
        return new ViewHolder(view, presenter);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TypeTask t = taskList.get(position);
        holder.render(t);

    }


    public void addAll(Collection<TypeTask> collection) {
        taskList.addAll(collection);
    }



    @Override
    public int getItemCount() {
        return taskList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TaskTypesPresenter presenter;

        @BindView(R.id.nameTaskType) TextView typeTask;
        @BindView(R.id.numberOfTasks) TextView numberOfTasks;
        @BindView(R.id.imageTaskType) ImageView image;
        @BindView(R.id.parent_layout) RelativeLayout parentLayout;
        @BindView(R.id.floatingActionButtonCreate) FloatingActionButton floatingActionButton;

        public ViewHolder(View itemView, TaskTypesPresenter presenter) {
            super(itemView);
            this.presenter = presenter;

            ButterKnife.bind(this, itemView);
        }

        public void render(TypeTask t) {
            onItemClick(t);
            onCreateTask();
            renderName(t.getName());
            renderNumber(t.getTasks());

            renderImage();
        }

        private void renderImage() {
            image.setImageResource(R.mipmap.ic_launcher);
        }

        private void renderNumber(int tasks) {
            numberOfTasks.setText(String.valueOf(tasks));
        }

        private void onItemClick(final TypeTask typeTask) {
            itemView.setOnClickListener(v -> presenter.onTaskTypeClicked(typeTask.getName()));
        }

        private void onCreateTask() {
            floatingActionButton.setOnClickListener(v -> presenter.onTaskTypeCreated());
        }

        private void renderName(String name) {
            typeTask.setText(name);
        }
    }
}

