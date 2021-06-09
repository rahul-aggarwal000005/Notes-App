package com.example.notes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract class NotesRoomDatabase extends RoomDatabase {

    /* get the NotesDao in order to access the sql queries */
    public abstract NotesDao getNotesDao();


    /* Set the instance for the NotesRoomDatabase */
    private static volatile NotesRoomDatabase INSTANCE;

    /* Number of thread so that these queries are not run on the main thread */
    private static final int NUMBER_OF_THREADS = 4;

    /*
    *  use to run database operations asynchronously on a background thread.
    *  */
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /* Setup for the singleton for the database */
    static NotesRoomDatabase getDatabase(final Context context) {
        /* if the Instance is not created yet, then a new Instance in initialized otherwise the the previous will be invoked */
        if (INSTANCE == null) {
            synchronized (NotesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotesRoomDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
