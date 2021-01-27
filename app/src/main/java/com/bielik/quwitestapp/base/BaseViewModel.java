package com.bielik.quwitestapp.base;

import androidx.lifecycle.ViewModel;

public class BaseViewModel<T extends BaseView> extends ViewModel {

    public T view;

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }
}
