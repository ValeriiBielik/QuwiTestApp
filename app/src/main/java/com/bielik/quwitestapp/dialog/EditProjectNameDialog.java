package com.bielik.quwitestapp.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.bielik.quwitestapp.R;
import com.bielik.quwitestapp.databinding.DialogEditProjectNameBinding;

public class EditProjectNameDialog extends DialogFragment implements EditProjectNameDialogView {

    public static final String TAG = "EditProjectNameDialog";
    private static final String EXTRA_PROJECT_ID = "project_id";
    private static final String EXTRA_PROJECT_NAME = "project_name";

    private EditProjectNameViewModel viewModel;
    private DialogEditProjectNameBinding binding;
    private OnProjectNameChangedListener onProjectNameChangedListener;

    public static EditProjectNameDialog newInstance(long projectId, String projectName) {
        Bundle args = new Bundle();
        args.putLong(EXTRA_PROJECT_ID, projectId);
        args.putString(EXTRA_PROJECT_NAME, projectName);
        EditProjectNameDialog fragment = new EditProjectNameDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public void setOnProjectNameChangedListener(OnProjectNameChangedListener onProjectNameChangedListener) {
        this.onProjectNameChangedListener = onProjectNameChangedListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        getData();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(EditProjectNameViewModel.class);
        viewModel.attachView(this);
    }

    private void getData() {
        if (getArguments() != null) {
            viewModel.setProjectId(getArguments().getLong(EXTRA_PROJECT_ID));
            viewModel.setCurrentProjectName(getArguments().getString(EXTRA_PROJECT_NAME));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogEditProjectNameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.etName.setText(viewModel.getCurrentProjectName());
        binding.btnCancel.setOnClickListener(v -> dismiss());
        binding.btnChange.setOnClickListener(v -> viewModel.editProjectName(binding.etName.getText()));
    }

    @Override
    public void onProjectNameEdited(String newName) {
        Toast.makeText(requireActivity(), getString(R.string.project_name_changed), Toast.LENGTH_SHORT).show();
        onProjectNameChangedListener.onProjectNameChanged(newName);
        dismiss();
    }

    @Override
    public void onProjectNameEmpty() {
        Toast.makeText(requireActivity(), getString(R.string.value_empty), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProjectNameNotChanged() {
        Toast.makeText(requireActivity(), getString(R.string.project_name_not_changed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String err) {
        Toast.makeText(requireActivity(), err, Toast.LENGTH_SHORT).show();
        Log.e(TAG, err);
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(params);
    }

    @Override
    public void onDestroy() {
        viewModel.detachView();
        super.onDestroy();
    }
}
