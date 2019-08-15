package com.ejupialked.todoapp.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private final TaskTypesPresenter presenter;
    private final List<TypeTask> taskList;

    private Context mContext;

    public RecyclerViewAdapter(TaskTypesPresenter presenter, Context context) {
        this.presenter = presenter;
        this.taskList = new ArrayList<>();
        mContext = context;
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

        TextView imageName;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView, TaskTypesPresenter presenter) {
            super(itemView);
            this.presenter = presenter;
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

        public void render(TypeTask t) {
            onItemClick(t);
            renderName(t.getName());
        }

        private void onItemClick(final TypeTask typeTask) {
            itemView.setOnClickListener(v -> presenter.onTaskTypeClicked(typeTask.getName()));
        }


        private void renderName(String name) {
            imageName.setText(name);
        }
    }
}

