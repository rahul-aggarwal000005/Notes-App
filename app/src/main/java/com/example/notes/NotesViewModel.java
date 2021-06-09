package com.example.notes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    /* Get the object of NotesRepository */
    private NotesRepository mNotesRepository;

    /* All the cached list of notes will be stored here */
    private LiveData<List<Note>> mAllNotes;


    /* Here is the constructor */
    public NotesViewModel(Application application){
        super(application);
        mNotesRepository = new NotesRepository(application);
        mAllNotes = mNotesRepository.getAllNotes();
    }

    /* Fetch the All Notes */
    LiveData<List<Note>> getAllNotes(){
        return mAllNotes;
    }

    /* Insert the new notes */
    public void insert(Note note){
        mNotesRepository.insert(note);
    }

    /* Delete the new Notes */
    public void delete(Note note){
        mNotesRepository.Delete(note);
    }
}
