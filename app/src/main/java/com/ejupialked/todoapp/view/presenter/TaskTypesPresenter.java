package com.ejupialked.todoapp.view.presenter;

import androidx.annotation.NonNull;

import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.domain.usecase.AddTaskType;
import com.ejupialked.todoapp.domain.usecase.EditTaskType;
import com.ejupialked.todoapp.domain.usecase.GetTaskTypes;
import com.ejupialked.todoapp.domain.usecase.RemoveTaskType;
import com.ejupialked.todoapp.view.activity.TaskTypeActivity;
import com.ejupialked.todoapp.view.customview.CustomDialogTaskType;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.observers.DisposableObserver;

public class TaskTypesPresenter extends Presenter<TaskTypesPresenter.View> {

    private GetTaskTypes getTaskTypes;
    private AddTaskType addTaskType;
    private RemoveTaskType removeTaskType;
    private EditTaskType editTaskType;

    @Inject
    public TaskTypesPresenter(@NonNull GetTaskTypes getTaskTypes,
                              @NonNull AddTaskType addTaskType,
                              @NonNull RemoveTaskType removeTaskType,
                              @NonNull EditTaskType editTaskType) {
        this.getTaskTypes = getTaskTypes;
        this.removeTaskType = removeTaskType;
        this.addTaskType = addTaskType;
        this.editTaskType = editTaskType;
    }


    @Override
    public void initialize() {
        super.initialize();
        getTaskTypes();
    }

    private void getTaskTypes() {


        getTaskTypes.execute(new DisposableObserver<List<TypeTask>>() {

        @Override
        public void onNext(List<TypeTask> typeTasks) {
            getView().showTaskTypes(typeTasks);
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onComplete() {

        }
    });
    }

    public void onTaskTypeRemoved(Integer i){

        TypeTask t = ((TaskTypeActivity) getView())
                .getRecyclerViewAdapter()
                .getTaskTypeAtPosition(i);

        removeTaskType.removeTaskType(t);

        removeTaskType.execute(new DisposableObserver<TypeTask>() {
            @Override
            public void onNext(TypeTask typeTask) {
                getView().showRemovedTypeTask(i);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });

    }

    public void onRestart(){
        getTaskTypes();
    }

    public void onTaskTypeCreated(TypeTask t){

        addTaskType.createTaskType(t);

        addTaskType.execute(new DisposableObserver<TypeTask>() {
            @Override
            public void onNext(TypeTask typeTask) {
                getView().showCreatedTypeTask(typeTask);
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

    public void onTaskTypeClicked(TypeTask typeTask){
        getView().openTasksScreen(typeTask);
    }

    public void onTaskTaskEdited(TypeTask typeTask) {

        editTaskType.editTaskType(typeTask);

        editTaskType.execute(new DisposableObserver<TypeTask>() {
            @Override
            public void onNext(TypeTask typeTask) {
                getView().showEditedTypeTask(typeTask);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });

    }


    public void destroy(){
        this.getTaskTypes.dispose();
        bind(null);
    }


    public interface View extends Presenter.View, CustomDialogTaskType.CustomDialogListener {
        void showTaskTypes(List<TypeTask> typeTaskList);
        void showCreatedTypeTask(TypeTask t);
        void showEditedTypeTask(TypeTask t);
        void showRemovedTypeTask(Integer p);

        void openTasksScreen(TypeTask typeTask);
        void openDialogEditTypeTask(int position);
        void openDialogCreateNewTypeTask();
    }
}
