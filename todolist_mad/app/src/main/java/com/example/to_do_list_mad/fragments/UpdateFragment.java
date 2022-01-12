package com.example.to_do_list_mad.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.to_do_list_mad.R;
import com.example.to_do_list_mad.viewmodel.TaskViewModel;
import com.example.to_do_list_mad.model.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class UpdateFragment extends Fragment {
    private Button update;
    private RadioButton low, medium, high;
    private TaskViewModel taskViewModel;
    private EditText editText;
    private Task taskToUpdate;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
    public UpdateFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_update, container, false);
        //get Task
        taskToUpdate = (Task) getArguments().getSerializable("todo");
        //taskViewModel initialization
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);

        editText = rootView.findViewById(R.id.update_task_name);
        editText.setText(taskToUpdate.getTaskName());
        update = rootView.findViewById(R.id.update_button);
        //priority buttons
        low = rootView.findViewById(R.id.low);
        medium = rootView.findViewById(R.id.medium);
        high = rootView.findViewById(R.id.high);
        setPriority();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTask();
                //back to ListFragment
                NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment);
                navController.navigate(R.id.action_updateFragment_to_listFragment);
                hideKeyboard();
            }
        });
        return rootView;
    }

    private void setPriority() {
        String checkedButton = taskToUpdate.getPriority();
        switch (checkedButton) {
            case "низкий":
                low.setChecked(true);
                break;
            case "средний":
                medium.setChecked(true);
                break;
            case "высокий":
                high.setChecked(true);
                break;
        }
    }

    //updating the task
    private void updateTask() {
        String todo = editText.getText().toString();
        String priority = "";

        if (low.isChecked()) {
            priority = "низкий";
        } else if (medium.isChecked()) {
            priority = "средний";
        } else {
            priority = "высокий";
        }
        taskViewModel.update(taskToUpdate.getId(), todo, priority, formatter.format(Calendar.getInstance().getTime()));
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
}