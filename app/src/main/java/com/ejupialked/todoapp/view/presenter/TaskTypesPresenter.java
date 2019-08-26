package com.ejupialked.todoapp.view.presenter;

import androidx.annotation.NonNull;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.domain.usecase.AddTaskType;
import com.ejupialked.todoapp.domain.usecase.GetTaskTypes;
import com.ejupialked.todoapp.domain.usecase.RemoveTaskType;
import com.ejupialked.todoapp.view.activity.customcomponents.CustomDialog;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.observers.DisposableObserver;

public class TaskTypesPresenter extends Presenter<TaskTypesPresenter.View> {

    private GetTaskTypes getTaskTypes;
    private AddTaskType addTaskType;
    private RemoveTaskType removeTaskType;

    @Inject
    public TaskTypesPresenter(@NonNull GetTaskTypes getTaskTypes,
                              @NonNull AddTaskType addTaskType,
                              @NonNull RemoveTaskType removeTaskType) {
        this.getTaskTypes = getTaskTypes;
        this.removeTaskType = removeTaskType;
        this.addTaskType = addTaskType;
    }


    @Override
    public void initialize() {
        super.initialize();

        getTaskTypes.execute(new DisposableObserver<List<TypeTask>>() {
            @Override
            public void onNext(List<TypeTask> typeTasks) {
                getView().showTaskTypes(typeTasks);
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        });
    }

    public void onTaskTypeRemoved(Integer p){

        removeTaskType.removeTaskTypeAtPosition(p);

        removeTaskType.execute(new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                getView().removeTypeTask(integer);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });

    }

    public void onTaskTypeCreated(TypeTask t){

        addTaskType.createTaskType(t);

        addTaskType.execute(new DisposableObserver<TypeTask>() {
            @Override
            public void onNext(TypeTask typeTask) {
                getView().updateTypeTasks(typeTask);
            }

            @Override
            public void onError(Throwable e) {
                // show error
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                // show ui
            }
        });
    }


    public void destroy(){
        this.getTaskTypes.dispose();
        setView(null);
    }

    public void onTaskTypeClicked(TypeTask typeTask){
        getView().openTasksScreen(typeTask);
    }



    public interface View extends Presenter.View, CustomDialog.CustomDialogListener {
        void showTaskTypes(List<TypeTask> typeTaskList);
        void updateTypeTasks(TypeTask t);
        void removeTypeTask(Integer p);
        void showNameTaskType(String name);

        void openTasksScreen(TypeTask typeTask);

    }
}
