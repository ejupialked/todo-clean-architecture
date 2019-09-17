package com.ejupialked.todoapp.view.activity;

import android.content.Context;
import android.content.Intent;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.TodoApp;
import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.utils.Utils;
import com.ejupialked.todoapp.view.customview.CustomDialogTask;
import com.ejupialked.todoapp.view.customview.SwipeToDeleteCallBackTasks;
import com.ejupialked.todoapp.view.adapter.RecycleViewAdapterTasks;
import com.ejupialked.todoapp.view.presenter.TaskTypesPresenter;
import com.ejupialked.todoapp.view.presenter.TasksPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;

public class TasksActivity extends BaseActivity implements TasksPresenter.View {

    private final static String TYPE_TASK_KEY = "type_task_key"; //intent

    @Inject
    TasksPresenter presenter;

    @BindView(R.id.recycle_view_tasks) RecyclerView recyclerView;
    @BindView(R.id.floatingActionButtonCreateTask) FloatingActionButton floatingActionButtonCreateTask;
    @BindView(R.id.coordinator_tasks) CoordinatorLayout coordinatorLayout;



    private RecycleViewAdapterTasks recyclerViewAdapter;

    @Override
    public void initView() {
        super.initView();
        initializeDagger();
        initializePresenter();
        initAdapter();
        initFAB();
        initToolbar();
        initBackButtonToolbar();
        initRecycleViewer();
        initSwipeToDelete();
    }

    private void initToolbar() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getTypeTaskExtra().getName());
        }
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_tasks;
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }


    @Override
    public void showTasks(List<Task> tasks) {
        recyclerViewAdapter.clear();
        recyclerViewAdapter.addAll(tasks);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeTask(int i) {
        recyclerViewAdapter.notifyItemRemoved(i);
        recyclerViewAdapter.removeTaskTypeAtPosition(i);
        recyclerViewAdapter.notifyDataSetChanged();
        showSnackBarUndo(recyclerViewAdapter.getRecentlyDeletedTask());
    }

    @Override
    public void editTask(Task t) {

    }

    @Override
    public void openDialogCreateNewTask() {
        CustomDialogTask customDialogTask = new CustomDialogTask();
        customDialogTask.show(getSupportFragmentManager(), "example");
    }

    @Override
    public void openDialogEditTask(Task t) {

    }

    @Override
    public void addTask(Task t) {
        recyclerViewAdapter.addAll(Collections.singleton(t));

        recyclerViewAdapter.notifyDataSetChanged();
        Utils.showSnackBarMessage(t.getDescription(), coordinatorLayout);

    }


    public RecycleViewAdapterTasks getRecyclerViewAdapter() {
        return recyclerViewAdapter;
    }

    public void showSnackBarUndo(Task t){

        Snackbar snackbar = Snackbar.make(coordinatorLayout, t.getDescription(), Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", view -> {
            recyclerViewAdapter.undoDelete();
        });

        snackbar.show();
    }


    private void initSwipeToDelete() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallBackTasks(presenter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initializePresenter() {
        presenter.bind(this);
        TypeTask typeTask = getTypeTaskExtra();
        presenter.setTypeTask(typeTask);
        presenter.initialize();
    }

    private void initializeDagger() {
        TodoApp todoApp = (TodoApp) getApplication();
        todoApp.getMainComponent().inject(this);
    }

    private void initRecycleViewer() {
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initAdapter() {
        recyclerViewAdapter = new RecycleViewAdapterTasks(presenter, this);
    }

    private void initFAB() {
        floatingActionButtonCreateTask.setOnClickListener(v -> openDialogCreateNewTask());
    }

    public TypeTask getTypeTaskExtra() {
        return (TypeTask) Objects.requireNonNull(getIntent().getExtras()).get(TYPE_TASK_KEY);
    }

    public static void open(Context context, TypeTask typeTask) {
        Intent intent = new Intent(context, TasksActivity.class);
        intent.putExtra("type_task_key", typeTask);

        context.startActivity(intent);

    }

    @Override
    public void applyTask(String description, String priority, String date) {
        presenter.onTaskCreated(description, priority, date);
    }
}
