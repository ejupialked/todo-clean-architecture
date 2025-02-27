package com.ejupialked.todoapp.view.activity;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.TodoApp;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.utils.Utils;
import com.ejupialked.todoapp.view.customview.CustomDialogTaskType;
import com.ejupialked.todoapp.view.customview.SwipeToDeleteCallbackTypeTasks;
import com.ejupialked.todoapp.view.adapter.RecyclerViewAdapterTaskType;
import com.ejupialked.todoapp.view.presenter.TaskTypesPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;


public class TaskTypeActivity extends BaseActivity implements TaskTypesPresenter.View {

    @Inject
    TaskTypesPresenter presenter;

    @BindView(R.id.coordinatorTaskTypes) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.recycle) RecyclerView recyclerView;
    @BindView(R.id.floatingActionButtonCreate) FloatingActionButton floatingActionButton;

    public RecyclerViewAdapterTaskType recyclerViewAdapter;


    @Override
    public void initView() {
        super.initView();

        initializeDagger();
        initializePresenter();
        initRecycleView();
        initSwipeToDelete();
        initToolbar();
        initFAB();
    }

    private void initToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("TODO by ejupialked");
        }
    }

    private void initSwipeToDelete() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new SwipeToDeleteCallbackTypeTasks(presenter, recyclerViewAdapter, this));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initFAB() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy){
                if (dy > 0)
                    floatingActionButton.hide();
                else if (dy < 0)
                    floatingActionButton.show();
            }
        });

        floatingActionButton.setOnClickListener(v -> openDialogCreateNewTypeTask());
    }

    private void initRecycleView(){
        recyclerViewAdapter = new RecyclerViewAdapterTaskType(presenter);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initializePresenter() {
        presenter.bind(this);
        presenter.initialize();
    }

    private void initializeDagger() {
        TodoApp app = (TodoApp) getApplication();
        app.getMainComponent().inject(this);
    }



    @Override
    public void showTaskTypes(List<TypeTask> typeTaskList) {
        recyclerViewAdapter.clearAll();
        recyclerViewAdapter.addAll(typeTaskList);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCreatedTypeTask(TypeTask typeTask) {
        recyclerViewAdapter.addAll(Collections.singleton(typeTask));
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEditedTypeTask(TypeTask t) {
        recyclerViewAdapter.updateEditedTypeTask(t);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showRemovedTypeTask(Integer p) {
        recyclerViewAdapter.notifyItemRemoved(p);
        recyclerViewAdapter.removeTaskTypeAtPosition(p);
        recyclerViewAdapter.notifyDataSetChanged();
        showSnackBarUndo();
    }


    @Override
    public void openTasksScreen(TypeTask typeTask) {
        TasksActivity.open(TaskTypeActivity.this, typeTask);
    }

    @Override
    public void openDialogEditTypeTask(int position) {
        TypeTask t = recyclerViewAdapter.getTaskTypeAtPosition(position);
        CustomDialogTaskType customDialogTaskType = new CustomDialogTaskType();
        customDialogTaskType.setT(t);
        customDialogTaskType.show(getSupportFragmentManager(), "example");
        //restore swipe back
        recyclerViewAdapter.notifyItemChanged(position);

    }

    @Override
    public void openDialogCreateNewTypeTask() {
        CustomDialogTaskType customDialogTaskType = new CustomDialogTaskType();
        customDialogTaskType.show(getSupportFragmentManager(), "example");    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    public void showSnackBarUndo(){
        Snackbar snackbar = Snackbar.make(
                coordinatorLayout,
                recyclerViewAdapter.getRecentlyDeletedTypeTask().getName(),
                Snackbar.LENGTH_LONG);

        snackbar.setAction("UNDO", view -> recyclerViewAdapter.undoDelete());
        snackbar.show();
    }

    @Override
    public void createTypeTask(String taskName) {
        presenter.onTaskTypeCreated(new TypeTask(taskName));
        Utils.showSnackBarMessage(taskName + " created!", coordinatorLayout);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tasktypes;
    }

    public RecyclerViewAdapterTaskType getRecyclerViewAdapter() {
        return recyclerViewAdapter;
    }

    @Override
    public void editTypeTask(TypeTask typeTask) {
        presenter.onTaskTaskEdited(typeTask);
    }
}
