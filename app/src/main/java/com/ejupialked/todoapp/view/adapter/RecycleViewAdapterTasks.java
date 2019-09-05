package com.ejupialked.todoapp.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.view.presenter.TasksPresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewAdapterTasks extends RecyclerView.Adapter<RecycleViewAdapterTasks.ViewHolder> {

    private final TasksPresenter presenter;
    private final List<Task> tasks;

    private Task recentlyDeletedTask;
    private int recentlyDeletedPosition;

    public RecycleViewAdapterTasks(TasksPresenter presenter) {
        this.presenter = presenter;
        this.tasks = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new RecycleViewAdapterTasks.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.render(task);
    }

    public void removeTaskTypeAtPosition(int p){
        recentlyDeletedPosition = p;
        recentlyDeletedTask = tasks.remove(p);
    }


    public Task getRecentlyDeletedTask() {
        return recentlyDeletedTask;
    }

    public TasksPresenter getPresenter() {
        return presenter;
    }

    public Task getTaskTypeAtPosition(int index){
        return tasks.get(index);
    }

    public void undoDelete(){
        tasks.add(recentlyDeletedPosition, recentlyDeletedTask);
        notifyItemInserted(recentlyDeletedPosition);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void clear() {
        int size = tasks.size();
        tasks.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(Collection<Task> collection) {
        tasks.addAll(collection);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txt_description) TextView txt_description;
        @BindView(R.id.txt_priority)    TextView txt_priority;
        @BindView(R.id.txt_isComplete)  TextView txt_isComplete;

       private ViewHolder(@NonNull View itemView) {
           super(itemView);
           ButterKnife.bind(this, itemView);
       }

        private void render(Task task) {
           renderDescription(task.getDescription());
           renderPriority(task.getPriority());
           renderCompleted(task.getIsCompleted());
        }

        private void renderDescription(String description) {
           txt_description.setText(description);
        }

        private void renderPriority(String priority) {
           switch (priority) {
               case "high":
                   txt_priority.setTextColor(Color.RED);
                   break;
               case "medium":
                   txt_priority.setTextColor(Color.YELLOW);
                   break;

               case "low":
                   txt_priority.setTextColor(Color.GREEN);
                   break;
           }
           txt_priority.setText(priority);
        }


        private void renderCompleted(String completed) {
           txt_isComplete.setText(completed);
        }
    }
}
