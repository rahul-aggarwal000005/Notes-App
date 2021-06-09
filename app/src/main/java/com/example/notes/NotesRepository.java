package com.example.notes;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class NotesRepository {

    /* Setting the Dao object */
    private NotesDao mNotesDao;

    /* Getting the LiveData */
    private LiveData<List<Note>> mAllNotes;

    NotesRepository(Application application) {
        NotesRoomDatabase db = NotesRoomDatabase.getDatabase(application);
        this.mNotesDao = db.getNotesDao();
        this.mAllNotes = mNotesDao.getAllNotes();
    }

    /*
     * Room executes all queries on a separate thread. Observed LiveData will notify the observer when the data has changed.
     * */
    LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Note note) {
        NotesRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNotesDao.insert(note);
        });
    }
    void Delete(Note note){
        NotesRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNotesDao.Delete(note);
        });
    }

}
