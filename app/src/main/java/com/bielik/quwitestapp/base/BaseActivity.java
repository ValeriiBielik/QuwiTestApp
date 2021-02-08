package com.bielik.quwitestapp.base;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import dmax.dialog.SpotsDialog;

public abstract class BaseActivity<B extends ViewDataBinding, T extends BaseViewModel> extends AppCompatActivity {

    protected B binding;
    protected T viewModel;
    protected AlertDialog dialog;

    protected void bindView(int layoutId) {
        binding = DataBindingUtil.setContentView(this, layoutId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new SpotsDialog.Builder().setContext(this).build();
    }

    @Override
    protected void onDestroy() {
        viewModel.detachView();
        super.onDestroy();
    }

    protected void showLoader() {
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    protected void hideLoader() {
        if (dialog.isShowing()) {
            dialog.hide();
        }
    }
}
