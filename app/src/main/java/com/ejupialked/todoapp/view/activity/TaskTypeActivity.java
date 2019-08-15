package com.ejupialked.todoapp.view.activity;


import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.widget.Toast;

import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.TODOApplication;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.view.adapter.RecyclerViewAdapter;
import com.ejupialked.todoapp.view.base.BaseActivity;
import com.ejupialked.todoapp.view.presenter.TaskTypesPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class TaskTypeActivity extends BaseActivity implements TaskTypesPresenter.View {

    @Inject TaskTypesPresenter presenter;
    RecyclerViewAdapter recyclerViewAdapter;

    @BindView(R.id.recycle)
    RecyclerView recyclerView;

    @Override
    public void initView() {
        super.initView();
        initializeDagger();
        initializePresenter();
        initRecycleView();

        presenter.initialize();

    }

    private void initRecycleView(){
        recyclerViewAdapter = new RecyclerViewAdapter(presenter);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initializePresenter() {
        presenter.setView(this);
    }

    private void initializeDagger() {
        TODOApplication app = (TODOApplication) getApplication();
        app.getMainComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tasktypes;
    }




    @Override
    public void showTaskTypes(List<TypeTask> typeTaskList) {
        recyclerViewAdapter.addAll(typeTaskList);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNameTaskType(String name) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }
}
