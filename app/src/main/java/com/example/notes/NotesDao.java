package com.example.notes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);

    /* in order to delete the note */
    @Delete
    void Delete(Note note);

    /* In order to select all the notes */
    @Query("SELECT * FROM note_table ORDER BY _id ASC")
    LiveData<List<Note>> getAllNotes();
}
