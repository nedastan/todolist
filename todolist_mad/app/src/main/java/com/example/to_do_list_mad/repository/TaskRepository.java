package com.example.to_do_list_mad.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.to_do_list_mad.data.TaskDao;
import com.example.to_do_list_mad.data.TaskDatabase;
import com.example.to_do_list_mad.model.Task;

import java.util.List;

public class TaskRepository {
    private LiveData<List<Task>> tasks;
    private TaskDao taskDao;

    public TaskRepository(Application application) {
        TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
        taskDao = taskDatabase.taskDao();
        tasks = taskDao.getAllTasks();

    }

    public void insert(Task task) {
        new InsertTaskAsyncTask(taskDao).execute(task);

    }

    public void update(int id, String taskName, String priority, String date) {
        taskDao.update(id, taskName, priority, date);

    }

    public void deleteTask(int id) {
        new DeleteTaskAsyncTask(taskDao).execute(id);

    }

    public void deleteAll() {
        new DeleteAllAsyncTask(taskDao).execute();
    }

    public LiveData<List<Task>> getAllTasks() {
        return tasks;
    }

    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;

        private InsertTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return null;
        }
    }

    private class DeleteAllAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;

        public DeleteAllAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.deleteAll();
            return null;
        }
    }

    private class DeleteTaskAsyncTask extends AsyncTask<Integer, Void, Void> {
        private TaskDao taskDao;

        public DeleteTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            taskDao.deleteTask(integers[0]);
            return null;
        }
    }


}

