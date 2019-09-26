package com.ejupialked.todoapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ejupialked.todoapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewAdapterIcons extends RecyclerView.Adapter<RecycleViewAdapterIcons.ViewHolder> {


    private ArrayList<Integer> icons;


    public RecycleViewAdapterIcons() {
        this.icons = new ArrayList<>();

        icons.add(R.drawable.briefcase);
        icons.add(R.drawable.cup);
        icons.add(R.drawable.briefcase);
        icons.add(R.drawable.cup);
        icons.add(R.drawable.briefcase);
        icons.add(R.drawable.cup);
        icons.add(R.drawable.briefcase);
        icons.add(R.drawable.cup);
        icons.add(R.drawable.briefcase);
        icons.add(R.drawable.cup);
        icons.add(R.drawable.briefcase);
        icons.add(R.drawable.cup);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_icon, parent, false);
        return new RecycleViewAdapterIcons.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int img = icons.get(position);
        holder.render(img);
    }

    @Override
    public int getItemCount() {
        return icons.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon_item) ImageView imageView;


        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void render(int image) {
            renderImage(image);
        }

        private void renderImage(int image) {
            imageView.setImageResource(image);
        }


    }

}
