package com.bielik.quwitestapp.screen.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bielik.quwitestapp.R;
import com.bielik.quwitestapp.adapter.ProjectsAdapter;
import com.bielik.quwitestapp.base.BaseActivity;
import com.bielik.quwitestapp.databinding.ActivityMainBinding;
import com.bielik.quwitestapp.model.Project;
import com.bielik.quwitestapp.screen.project_info.TicketsActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements MainActivityView, ProjectsAdapter.OnProjectClickListener {

    private static final String TAG = "MainActivity";
    private ProjectsAdapter projectsAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView(R.layout.activity_main);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLoader();
        viewModel.loadProjects();
    }

    private void init() {
        projectsAdapter = new ProjectsAdapter(this);
        initViewModel();
        binding.rvProjects.setLayoutManager(new LinearLayoutManager(this));
        binding.rvProjects.setAdapter(projectsAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        binding.rvProjects.addItemDecoration(dividerItemDecoration);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.attachView(this);
        viewModel.getProjectsLiveData().observe(this, projects -> {
            if (!projects.isEmpty()) {
                projectsAdapter.updateDataList(projects);
            } else {
                Toast.makeText(this, getString(R.string.no_projects), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSuccess() {
        hideLoader();
    }

    @Override
    public void onError(String err) {
        hideLoader();
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
        Log.e(TAG, err);
    }

    @Override
    public void onProjectClick(Project project) {
        TicketsActivity.start(this, project);
    }
}