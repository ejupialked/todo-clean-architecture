package com.ejupialked.todoapp.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
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

    private Context context;

    public RecycleViewAdapterTasks(TasksPresenter presenter, Context context) {
        this.context = context;
        this.presenter = presenter;
        this.tasks = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
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

    public Context getContext() {
        return context;
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

    public void updateEditedTask(Task task) {
        for (Task ta: tasks) {
            if(task.equals(ta)){
                ta = task;
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txt_description)    TextView txt_description;
        @BindView(R.id.txt_date)           TextView txt_date;
        @BindView(R.id.colorbar)           View colorBar;
        @BindView(R.id.checkbox_completed) CheckBox checkBox_completed;
        @BindView(R.id.date_image)          ImageView date_image;


       private ViewHolder(@NonNull View itemView) {
           super(itemView);
           ButterKnife.bind(this, itemView);
       }

        private void render(Task task) {
           renderDescription(task.getDescription());
           renderPriority(task.getPriority());
           renderDate(task.getDate());
           renderCompletion(task);
           onCheckBoxTicked(task);
        }

        private void renderCompletion(Task isCompleted) {
           checkCompleted(isCompleted);
        }

        private void onCheckBoxTicked(Task task) {
           checkBox_completed.setOnClickListener(view ->{
            boolean checked = checkBox_completed.isChecked();
            task.setIsCompleted(checked);
            checkCompleted(task);
           });
        }


        private void checkCompleted(Task task){
            if(!task.getIsCompleted()){
                txt_date.setTextColor(getContext().getColor(R.color.black));
                txt_description.setTextColor(getContext().getColor(R.color.black));
                txt_description.setPaintFlags(0);
                date_image.setImageResource(R.drawable.ic_date_range_notcompleted);
                renderPriority(task.getPriority());

            }else{
                checkBox_completed.setChecked(true);
                txt_description.setTextColor(Color.GRAY);
                txt_description.setPaintFlags(txt_description.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                txt_date.setTextColor(Color.GRAY);
                date_image.setImageResource(R.drawable.ic_date_range_completed);
                colorBar.setBackgroundColor(Color.GRAY);
            }
        }

        private void renderDate(String date) {
           txt_date.setText(date);
        }

        private void renderDescription(String description) {
           txt_description.setText(description);
        }

        private void renderPriority(String priority) {
           switch (priority) {
               case "high":
                   colorBar.setBackgroundColor(getContext().getColor(R.color.high_color));
                   break;
               case "medium":
                   colorBar.setBackgroundColor(getContext().getColor(R.color.medium_color));
                   break;

               case "low":
                   colorBar.setBackgroundColor(getContext().getColor(R.color.low_color));
                   break;
           }
        }


    }
}
