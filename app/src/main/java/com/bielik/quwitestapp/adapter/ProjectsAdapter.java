package com.bielik.quwitestapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bielik.quwitestapp.databinding.ItemProjectBinding;
import com.bielik.quwitestapp.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder> {

    private final List<Project> dataList = new ArrayList<>();
    private final OnProjectClickListener onProjectClickListener;

    public ProjectsAdapter(OnProjectClickListener onProjectClickListener) {
        this.onProjectClickListener = onProjectClickListener;
    }

    public void updateDataList(List<Project> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProjectBinding binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProjectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.bind(dataList.get(position), onProjectClickListener);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {

        private final ItemProjectBinding mBinding;

        public ProjectViewHolder(@NonNull ItemProjectBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Project project, OnProjectClickListener onProjectClickListener) {
            mBinding.setProject(project);
            mBinding.setListener(onProjectClickListener);
            mBinding.executePendingBindings();
        }
    }

    public interface OnProjectClickListener {
        void onProjectClick(Project project);
    }
}
