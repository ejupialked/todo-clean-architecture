package com.ejupialked.todoapp.data.repository.datasource;

import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;

import java.util.List;

import io.reactivex.Observable;

public interface DataSource {

    Observable<List<TypeTask>> typeTaskList();

    Observable<List<Task>> tasks(TypeTask typeTask);

}
