package com.ejupialked.todoapp.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.view.presenter.TasksPresenter;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class SwipeToDeleteCallBackTasks extends ItemTouchHelper.SimpleCallback {

    private final TasksPresenter presenter;
    private final ItemTouchHelperAdapter mAdapter;

    Context context;


    public SwipeToDeleteCallBackTasks(TasksPresenter presenter, ItemTouchHelperAdapter mAdapter, Context context) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.presenter = presenter;
        this.mAdapter = mAdapter;
        this.context = context;
    }


    @Override
    public void onChildDraw(@NonNull Canvas c,
                            @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {

        new RecyclerViewSwipeDecorator.Builder(c, recyclerView,
                viewHolder, dX, dY, actionState, isCurrentlyActive)

                .addSwipeLeftBackgroundColor(getContext().getColor(R.color.green))
                .addSwipeLeftActionIcon(R.drawable.ic_mode_edit_black_24dp)
                .addSwipeRightBackgroundColor(getContext().getColor(R.color.red))
                .addSwipeRightActionIcon(R.drawable.ic_delete_sweep_black_24dp)
                .addSwipeRightLabel("Remove")
                .setSwipeRightLabelColor(Color.WHITE)
                .addSwipeLeftLabel("Edit")
                .setSwipeLeftLabelColor(Color.WHITE)
                .create()
                .decorate();


        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

    }


    public Context getContext() {
        return context;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {

        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        if(direction == 4){
            presenter.getView().openDialogEditTask(position);
        }else {
            presenter.onTaskRemoved(position);
        }
    }

    //drag and drop
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    public interface ItemTouchHelperAdapter {

        //drag and drop
        void onItemMove(int fromPosition, int toPosition);

       // void onItemDismiss(int position);
    }
}
