package com.ejupialked.todoapp.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.view.presenter.TaskTypesPresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class RecycleViewAdapter extends RecyclerView.Adapter<MViewHolder> {

    private static final String TAG = "RecycleViewAdapter";

    private List<TypeTask> taskTypes;

    private final TaskTypesPresenter presenter;

    public RecycleViewAdapter(@NonNull TaskTypesPresenter presenter){
        this.presenter = presenter;
        taskTypes = new ArrayList<>();
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_tasktypeitem, parent, false);

        return new MViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called." + position);
        TypeTask t = taskTypes.get(position);
        holder.render(t);

    }



    @Override
    public int getItemCount() {
        return taskTypes.size();
    }

    public void addAll(Collection<TypeTask> collection) {
        taskTypes.addAll(collection);
    }




}
