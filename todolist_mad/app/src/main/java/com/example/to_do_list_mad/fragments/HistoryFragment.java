package com.example.to_do_list_mad.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_do_list_mad.HistoryAdapter;
import com.example.to_do_list_mad.R;
import com.example.to_do_list_mad.TaskAdapter;
import com.example.to_do_list_mad.model.Task;
import com.example.to_do_list_mad.viewmodel.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private ArrayList<Task> tasks = new ArrayList<>();
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        //set the view
       tasks.add(new Task("Купить молоко", "средний", "21/12/2020","30/12/2020" ));
        tasks.add(new Task("Закончить приложение", "средний", "21/12/2020","25/12/2020" ));
        setView(rootView);
        return rootView;
    }


    private void setView(View rootView) {
        recyclerView = rootView.findViewById(R.id.history_recycler_view);
        adapter = new HistoryAdapter(tasks, getContext());
        recyclerView.setAdapter(adapter);
    }
}
