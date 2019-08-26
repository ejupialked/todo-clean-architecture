package com.ejupialked.todoapp.view.presenter;


import com.ejupialked.todoapp.domain.model.TypeTask;

public class TasksPresenter extends Presenter<TasksPresenter.View>{




    public interface View extends Presenter.View {
        void showTasks(TypeTask typeTask);

    }
}
