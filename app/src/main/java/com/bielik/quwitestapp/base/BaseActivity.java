package com.bielik.quwitestapp.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<B extends ViewDataBinding, T extends BaseViewModel> extends AppCompatActivity {

    protected B binding;
    protected T viewModel;

    protected void bindView(int layoutId) {
        binding = DataBindingUtil.setContentView(this, layoutId);
    }

    @Override
    protected void onDestroy() {
        viewModel.detachView();
        super.onDestroy();
    }
}
