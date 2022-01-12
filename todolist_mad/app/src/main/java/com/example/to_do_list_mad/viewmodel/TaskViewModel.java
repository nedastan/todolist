package com.example.to_do_list_mad.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.to_do_list_mad.repository.TaskRepository;
import com.example.to_do_list_mad.model.Task;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;
    private LiveData<List<Task>> tasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
        tasks = taskRepository.getAllTasks();
    }

    public void insert(Task task) {
        taskRepository.insert(task);
    }

    public LiveData<List<Task>> getAllTasks() {
        return tasks;
    }

    public void deleteAll() {
        taskRepository.deleteAll();
    }

    public void deleteTask(int id) {
        taskRepository.deleteTask(id);
    }

    public void update(int id, String taskName, String priority, String date) {
        taskRepository.update(id, taskName, priority, date);
    }
}
