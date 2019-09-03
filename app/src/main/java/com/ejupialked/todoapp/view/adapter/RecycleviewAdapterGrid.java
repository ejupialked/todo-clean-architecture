package com.ejupialked.todoapp.view.adapter;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.data.repository.datasource.DataSourceTasks;
import com.ejupialked.todoapp.view.activity.customcomponents.SquareImageView;
import com.ejupialked.todoapp.view.presenter.TaskTypesPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleviewAdapterGrid extends RecyclerView.Adapter<RecycleviewAdapterGrid.GridItemViewHolder> {

    private final List<Integer> images;
    private Context context;


    public RecycleviewAdapterGrid(Context context) {
        this.images = DataSourceTasks.getImages();
        this.context = context;
    }

    @NonNull
    @Override
    public RecycleviewAdapterGrid.GridItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_dialogtypetask, parent, false);
        return new GridItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridItemViewHolder holder, int position) {
        int imageID = images.get(position);
        holder.render(imageID);
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public Context getContext() {
        return context;
    }



    class GridItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.siv)
        SquareImageView siv;




        public GridItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void render(int imageID) {
            siv.setImageResource(imageID);

            siv.setOnClickListener(view -> {



            });

        }
    }
}
