package com.ejupialked.todoapp.view.presenter;

public abstract class Presenter<T extends Presenter.View> {
    private T view;

    public T getView() {
        return view;
    }

    public void bind(T view) {
        this.view = view;
    }

    public void initialize() {

    }

    public interface View {

    }
}
