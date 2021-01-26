package com.bielik.quwitestapp.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<B extends ViewDataBinding, T extends BaseViewModel> extends AppCompatActivity {

    protected B dataBinding;
    protected T baseViewModel;

    protected void bindView(int layoutId) {
        dataBinding = DataBindingUtil.setContentView(this, layoutId);
    }

    @Override
    protected void onDestroy() {
        baseViewModel.detachView();
        super.onDestroy();
    }
}
