package com.example.to_do_list_mad.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.to_do_list_mad.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);

    @Query("UPDATE task SET taskName = :name, priority = :priority, date = :date WHERE _id = :id ")
    void update(int id, String name, String priority, String date);

    @Query("DELETE FROM task")
    void deleteAll();

    @Query("DELETE FROM task WHERE _id = :id")
    void deleteTask(int id);

    @Query("SELECT * FROM task ORDER BY _id ASC")
    LiveData<List<Task>> getAllTasks();

}
