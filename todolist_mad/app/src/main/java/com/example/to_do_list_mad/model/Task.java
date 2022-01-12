package com.example.to_do_list_mad.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "task")
public class Task implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="_id")
    private int id;

    private String taskName;
    private String priority;
    private String date;
    private String deadline;

    public Task(String taskName, String priority, String date, String deadline) {
        this.taskName = taskName;
        this.priority = priority;
        this.date = date;
        this.deadline = deadline;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }


    public String getPriority() {
        return priority;
    }

    public String getDate() {
        return date;
    }

    public String getDeadline() {
        return deadline;
    }
}
