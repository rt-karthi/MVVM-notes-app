package com.example.mvvvmnotesapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = Note.class, version = 1)
public abstract class NoteDataBase extends RoomDatabase {
    private static NoteDataBase instance;

    public abstract NoteDAO noteDAO();

    // synchronized -> provide thread safe
    public static synchronized NoteDataBase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
            ,NoteDataBase.class, "note_database")
                    .fallbackToDestructiveMigration() // it will delete the database if update the version
                    .build();
        }
        return instance;
    }
}
