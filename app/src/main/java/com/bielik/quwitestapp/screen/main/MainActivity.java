package com.bielik.quwitestapp.screen.main;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.bielik.quwitestapp.R;
import com.bielik.quwitestapp.base.BaseActivity;
import com.bielik.quwitestapp.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainActivityView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView(R.layout.activity_main);

        baseViewModel = new MainViewModel();
        baseViewModel.attachView(this);
    }

    @Override
    public void OnDataLoad(String items) {

    }

    @Override
    public void OnError(Throwable throwable) {

    }
}