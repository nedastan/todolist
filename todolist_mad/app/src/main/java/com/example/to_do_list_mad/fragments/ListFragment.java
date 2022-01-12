package com.example.to_do_list_mad.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.to_do_list_mad.R;
import com.example.to_do_list_mad.TaskAdapter;
import com.example.to_do_list_mad.viewmodel.TaskViewModel;
import com.example.to_do_list_mad.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment  {

    private FloatingActionButton addTask;
    private TaskViewModel taskViewModel;
    private ArrayList<Task> tasks = new ArrayList<>();
    private RecyclerView recyclerView;
    private TaskAdapter adapter;

    public ListFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final View rootView = inflater.inflate(R.layout.fragment_list, container, false);
       //set the view
        setView(rootView);
        addTask = rootView.findViewById(R.id.fab);
        //taskViewModel initialization
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        //setting the observer
        taskViewModel.getAllTasks().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
               adapter.setTasks(tasks);
            }
        });
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(rootView).navigate(R.id.action_listFragment_to_addFragment);
            }
        });

        adapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemDelete(int position,Task task) {
                taskViewModel.deleteTask(task.getId());
            }

            @Override
            public void onItemEdit(int position, Task task) {
            }});
       return rootView;
    }


    private void setView(View rootView) {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        adapter = new TaskAdapter(tasks, getContext());
        setHasOptionsMenu(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                showAlertDialogDelete();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlertDialogDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getString(R.string.delete_all_items_dialog));
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                taskViewModel.deleteAll();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}