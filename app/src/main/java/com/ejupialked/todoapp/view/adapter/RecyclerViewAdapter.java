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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private final TaskTypesPresenter presenter;
    private final List<TypeTask> taskList;

    private Context context;

    private TypeTask recentlyDeletedTypeTask;
    private int recentlyDeletedPosition;

    public RecyclerViewAdapter(TaskTypesPresenter presenter) {
        this.presenter = presenter;
        this.taskList = new ArrayList<>();
        this.context = getContext();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_tasktype, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TypeTask t = taskList.get(position);
        holder.render(t);
    }

    public TypeTask getTaskTypeAtPosition(int index){
        return taskList.get(index);
    }

    public TypeTask getRecentlyDeletedTypeTask() {
        return recentlyDeletedTypeTask;
    }

    public List<TypeTask> getTaskList() {
        return taskList;
    }

    public void updateEditedTypeTask(TypeTask t){
        for (TypeTask ta: taskList) {
            if(t.equals(ta)){
                ta = t;
            }
        }
    }

    public void undoDelete(){
        taskList.add(recentlyDeletedPosition, recentlyDeletedTypeTask);
        notifyItemInserted(recentlyDeletedPosition);
        notifyDataSetChanged();
    }

    public void removeTaskTypeAtPosition(int p){
        recentlyDeletedPosition = p;
        recentlyDeletedTypeTask = taskList.remove(p);
    }

    public TaskTypesPresenter getPresenter() {
        return presenter;
    }

    public Context getContext() {
        return context;
    }

    //todo fix repositioning
    public void clearAll() {
        int size = taskList.size();
        taskList.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(Collection<TypeTask> collection) {
        taskList.addAll(collection);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nameTaskType) TextView typeTask;
        @BindView(R.id.numberOfTasks) TextView numberOfTasks;
        @BindView(R.id.imageTaskType) ImageView image;
        @BindView(R.id.parent_layout) RelativeLayout parentLayout;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void render(TypeTask t) {
            onItemClick(t);
            renderName(t.getName());
            renderNumber(t.getNoTasks());
            renderImage(t);
        }

        private void renderImage(TypeTask t) {
            image.setImageResource(t.getImageID());
        }

        private void renderNumber(int tasks) {
            numberOfTasks.setText(String.valueOf(tasks));
        }

        private void onItemClick(final TypeTask typeTask) {
            itemView.setOnClickListener(v -> presenter.onTaskTypeClicked(typeTask));
        }

        private void renderName(String name) {
            typeTask.setText(name);
        }
    }
}

