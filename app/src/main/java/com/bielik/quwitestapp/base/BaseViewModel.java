package com.bielik.quwitestapp.base;

public class BaseViewModel<T extends BaseView> {

    public T view;

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }
}
