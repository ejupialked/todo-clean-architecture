package com.ejupialked.todoapp.view.activity;


import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.TODOApplication;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.view.adapter.RecycleViewAdapter;
import com.ejupialked.todoapp.view.base.BaseActivity;
import com.ejupialked.todoapp.view.presenter.TaskTypesPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class TaskTypeActivity extends BaseActivity implements TaskTypesPresenter.View {

    @Inject
    TaskTypesPresenter presenter;

    @BindView(R.id.list_taskTypes)
    RecyclerView taskTypesLists;

    private RecycleViewAdapter adapter;


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initView() {
        super.initView();
        initializeDagger();
        initializePresenter();
        initializeAdapter();
        initializeRecyclerView();
        presenter.initialize();

    }

    private void initializeAdapter() {
        adapter = new RecycleViewAdapter(presenter);
    }

    private void initializePresenter() {
        presenter.setView(this);
    }

    private void initializeDagger() {
        TODOApplication app = (TODOApplication) getApplication();
        app.getMainComponent().inject(this);
    }

    private void initializeRecyclerView() {
        taskTypesLists.setLayoutManager(new LinearLayoutManager(this));
        taskTypesLists.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        taskTypesLists.setHasFixedSize(true);
        taskTypesLists.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_types);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_types;
    }

    @Override
    public void showTaskTypes(List<TypeTask> typeTaskList) {
        adapter.addAll(typeTaskList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showTasks(TypeTask typeTask) {

    }

    @Override
    public void showLoading() {
        taskTypesLists.setVisibility(View.GONE);


    }

    @Override
    public void hideLoading() {

    }
}
