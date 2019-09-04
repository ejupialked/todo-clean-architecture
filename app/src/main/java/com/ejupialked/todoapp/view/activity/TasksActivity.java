package com.ejupialked.todoapp.view.activity;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.TODOApplication;
import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.view.customview.CustomDialogTask;
import com.ejupialked.todoapp.view.customview.SwipeToDeleteCallBackTasks;
import com.ejupialked.todoapp.view.adapter.RecycleViewAdapterTasks;
import com.ejupialked.todoapp.view.base.BaseActivity;
import com.ejupialked.todoapp.view.presenter.TasksPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;

public class TasksActivity extends BaseActivity implements TasksPresenter.View {

    private final static String TYPE_TASK_KEY = "type_task_key"; //intent

    @Inject
    TasksPresenter presenter;

    @BindView(R.id.recycle_view_tasks)
    RecyclerView recyclerView;

    @BindView(R.id.floatingActionButtonCreateTask)
    FloatingActionButton floatingActionButtonCreateTask;

    private RecycleViewAdapterTasks recyclerViewAdapter;

    @Override
    public void initView() {
        super.initView();

        initializeDagger();
        initializePresenter();
        initAdapter();
        initFAB();
        initToolbar();
        initRecycleViewer();
        initSwipeToDelete();
    }

    private void initToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getTypeTaskExtra().getName());
        }
    }

    private void openDialog() {
        CustomDialogTask customDialogTask = new CustomDialogTask();
        customDialogTask.show(getSupportFragmentManager(), "example");
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
    public void updateTasks(List<Task> tasks) {
        recyclerViewAdapter.clear();
        recyclerViewAdapter.addAll(tasks);
        recyclerViewAdapter.notifyDataSetChanged();
    }


    private void initSwipeToDelete() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallBackTasks(presenter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initializePresenter() {
        presenter.setView(this);
        TypeTask typeTask = getTypeTaskExtra();
        presenter.setTypeTask(typeTask);
        presenter.initialize();
    }

    private void initializeDagger() {
        TODOApplication todoApplication = (TODOApplication) getApplication();
        todoApplication.getMainComponent().inject(this);
    }

    private void initRecycleViewer() {
        recyclerViewAdapter = new RecycleViewAdapterTasks(presenter);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initAdapter() {
        recyclerViewAdapter = new RecycleViewAdapterTasks(presenter);
    }

    private void initFAB() {
        floatingActionButtonCreateTask.setOnClickListener(v -> openDialog());
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
    public void applyTask(String description, String priority) {
        presenter.onTaskCreated(new Task(description, priority, "no"));
    }
}
