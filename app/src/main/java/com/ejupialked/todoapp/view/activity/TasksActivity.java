package com.ejupialked.todoapp.view.activity;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.TODOApplication;
import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.view.activity.customcomponents.CustomDialogTask;
import com.ejupialked.todoapp.view.activity.customcomponents.CustomDialogTaskType;
import com.ejupialked.todoapp.view.adapter.RecycleViewAdapterTasks;
import com.ejupialked.todoapp.view.base.BaseActivity;
import com.ejupialked.todoapp.view.presenter.TasksPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class TasksActivity extends BaseActivity implements TasksPresenter.View {

    @Inject
    TasksPresenter presenter;

    @BindView(R.id.recycle_view_tasks)
     RecyclerView recyclerView;

    @BindView(R.id.floatingActionButtonCreateTask)
    FloatingActionButton floatingActionButtonCreateTask;

    private RecycleViewAdapterTasks recyclerViewAdapter;


    private final static String TYPE_TASK_KEY = "type_task_key";


    public static void open(Context context, TypeTask typeTask) {
        Intent intent = new Intent(context, TasksActivity.class);
        intent.putExtra("type_task_key", typeTask);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        super.initView();
        initializeDagger();
        initializePresenter();
        initAdapter();
        initFAB();
        initRecycleViewer();

    }

    private void initFAB() {
        floatingActionButtonCreateTask.setOnClickListener(v -> openDialog());

    }

    private void openDialog() {
        CustomDialogTask customDialogTask = new CustomDialogTask();
        customDialogTask.show(getSupportFragmentManager(), "example");
    }
    private void initAdapter() {
        recyclerViewAdapter = new RecycleViewAdapterTasks(presenter);

    }

    private void initRecycleViewer() {
        recyclerViewAdapter = new RecycleViewAdapterTasks(presenter);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_tasks;
    }

    private void initializeDagger() {
        TODOApplication todoApplication = (TODOApplication) getApplication();
        todoApplication.getMainComponent().inject(this);
    }



    public TypeTask getTypeTaskExtra() {
        return (TypeTask) getIntent().getExtras().get(TYPE_TASK_KEY);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
    private void initializePresenter() {
        presenter.setView(this);
        TypeTask typeTask = getTypeTaskExtra();
        presenter.setTypeTask(typeTask);
        presenter.initialize();
    }


    @Override
    public void showTasks(List<Task> tasks) {
        recyclerViewAdapter.addAll(tasks);
        recyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    public void updateTasks(List<Task> tasks) {
        recyclerViewAdapter.addAll(tasks);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void applyTask(String description, String priority) {
        presenter.onTaskCreated(new Task(description, priority, "no"));
    }
}
