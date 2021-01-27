package com.bielik.quwitestapp.screen.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.bielik.quwitestapp.R;
import com.bielik.quwitestapp.base.BaseActivity;
import com.bielik.quwitestapp.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainActivityView{

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView(R.layout.activity_main);

        viewModel = new MainViewModel();
        viewModel.attachView(this);
    }

    @Override
    public void OnDataLoad(String items) {

    }

    @Override
    public void onError(String err) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}