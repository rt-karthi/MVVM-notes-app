package com.example.mvvvmnotesapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note { // By default it will ne note(table name), then change the table name using annotation

    private String title;
    private String description;
    private int priority;
    @PrimaryKey(autoGenerate = true)
    private int id;


    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
