package com.bielik.quwitestapp.screen.project_info;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bielik.quwitestapp.R;
import com.bielik.quwitestapp.adapter.TicketsAdapter;
import com.bielik.quwitestapp.base.BaseActivity;
import com.bielik.quwitestapp.databinding.ActivityTicketsBinding;
import com.bielik.quwitestapp.dialog.EditProjectNameDialog;
import com.bielik.quwitestapp.model.Project;

public class TicketsActivity extends BaseActivity<ActivityTicketsBinding, TicketsViewModel>
        implements TicketsActivityView {

    private static final String TAG = "TicketsActivity";
    private static final String EXTRA_PROJECT = "extra_project";

    private TicketsAdapter ticketsAdapter;

    public static void start(Context context, Project project) {
        Intent starter = new Intent(context, TicketsActivity.class);
        starter.putExtra(EXTRA_PROJECT, project);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView(R.layout.activity_tickets);

        setUpActionBar();
        init();
        getData();
    }

    private void setUpActionBar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getData() {
        if (getIntent() != null) {
            viewModel.setProject(getIntent().getParcelableExtra(EXTRA_PROJECT));
        }
    }

    private void init() {
        ticketsAdapter = new TicketsAdapter();
        initViewModel();
        binding.rvTickets.setLayoutManager(new LinearLayoutManager(this));
        binding.rvTickets.setAdapter(ticketsAdapter);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(TicketsViewModel.class);
        viewModel.attachView(this);
        viewModel.getTicketsLiveData().observe(this, tickets -> {
            if (!tickets.isEmpty()) {
                ticketsAdapter.updateDataList(tickets);
            } else {
                Toast.makeText(this, getString(R.string.no_tickets), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit: {
                editProjectName();
                return true;
            }
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void editProjectName() {
        EditProjectNameDialog dialog = EditProjectNameDialog.newInstance(viewModel.getProjectId());
        dialog.show(getSupportFragmentManager(), EditProjectNameDialog.TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.loadTickets();
    }

    @Override
    public void onError(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
        Log.e(TAG, err);
    }
}