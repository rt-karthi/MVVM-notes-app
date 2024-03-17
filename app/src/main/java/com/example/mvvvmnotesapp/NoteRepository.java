package com.example.mvvvmnotesapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDAO noteDAO;
    private LiveData<List<Note>> notesList;

    public NoteRepository(Application application) {
        NoteDataBase noteDataBase = NoteDataBase.getInstance(application);
        noteDAO = noteDataBase.noteDAO();
        notesList = noteDAO.getAllNotes();
    }

    /* For CRUD operations create a new thread for the operations,because ROOM does not allow db operations
       perform in main thread */

    public void insetData(Note note) {
        new InsertAsyncTask(noteDAO).execute(note);
    }

    public void deleteData(Note note) {
        new DeleteAsyncTask(noteDAO).execute(note);
    }

    public void updateData(Note note) {
        new UpdateAsyncTask(noteDAO).execute(note);
    }

    public void deleteAllData() {
        new DeleteAllAsyncTask(noteDAO).execute();
    }

    public LiveData<List<Note>> getAllData() {
        return notesList;
    }

    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDAO noteDAO;
        public InsertAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.insert(notes[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDAO noteDAO;
        public DeleteAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.delete(notes[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDAO noteDAO;
        public UpdateAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.update(notes[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDAO noteDAO;
        public DeleteAllAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.deleteAll();
            return null;
        }
    }


}
