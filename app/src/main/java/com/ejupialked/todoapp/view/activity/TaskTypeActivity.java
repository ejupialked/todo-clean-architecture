package com.ejupialked.todoapp.view.activity;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.TodoApp;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.view.customview.CustomDialogTaskType;
import com.ejupialked.todoapp.view.customview.SwipeToDeleteCallbackTypeTasks;
import com.ejupialked.todoapp.view.adapter.RecyclerViewAdapter;
import com.ejupialked.todoapp.view.presenter.TaskTypesPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;


public class TaskTypeActivity extends BaseActivity implements TaskTypesPresenter.View {

    @Inject TaskTypesPresenter presenter;
    @BindView(R.id.recycle) RecyclerView recyclerView;
    @BindView(R.id.floatingActionButtonCreate) FloatingActionButton floatingActionButton;
    RecyclerViewAdapter recyclerViewAdapter;



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
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallbackTypeTasks(presenter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initFAB() {
        floatingActionButton.setOnClickListener(v -> openDialogCreateNewTask());
    }





    private void initRecycleView(){
        recyclerViewAdapter = new RecyclerViewAdapter(presenter);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initializePresenter() {
        presenter.bindView(this);
        presenter.initialize();
    }

    private void initializeDagger() {
        TodoApp app = (TodoApp) getApplication();
        app.getMainComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tasktypes;
    }

    @Override
    public void showTaskTypes(List<TypeTask> typeTaskList) {
        recyclerViewAdapter.clearAll();
        recyclerViewAdapter.addAll(typeTaskList);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void addTypeTask(TypeTask typeTask) {
        recyclerViewAdapter.addAll(Collections.singleton(typeTask));
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEditTypeTask(TypeTask t) {
        recyclerViewAdapter.updateEditedTypeTask(t);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeTypeTask(Integer p) {
        recyclerViewAdapter.notifyItemRemoved(p);
        recyclerViewAdapter.removeTaskTypeAtPosition(p);
        recyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    public void openTasksScreen(TypeTask typeTask) {
        TasksActivity.open(TaskTypeActivity.this, typeTask);
    }

    @Override
    public void openDialogEditTask(int position) {
        TypeTask t = recyclerViewAdapter.getTaskTypeAtPosition(position);
        CustomDialogTaskType customDialogTaskType = new CustomDialogTaskType();
        customDialogTaskType.setT(t);
        customDialogTaskType.show(getSupportFragmentManager(), "example");

    }

    @Override
    public void openDialogCreateNewTask() {
        CustomDialogTaskType customDialogTaskType = new CustomDialogTaskType();
        customDialogTaskType.show(getSupportFragmentManager(), "example");    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.onRestart();
    }

    @Override
    public void createTypeTask(String taskName) {
        presenter.onTaskTypeCreated(new TypeTask(taskName));
    }


    @Override
    public void editTypeTask(TypeTask typeTask) {
        presenter.onTaskTaskEdited(typeTask);
    }
}
