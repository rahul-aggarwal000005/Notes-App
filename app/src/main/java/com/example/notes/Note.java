package com.example.notes;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    /* The text to be written in each note */
    @NonNull
    @ColumnInfo(name = "text")
    private String mText;

    /* Id for each note */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public int id;

    public Note(String text) {
        this.mText = text;
    }

    public String getText() {
        return mText;
    }


}
