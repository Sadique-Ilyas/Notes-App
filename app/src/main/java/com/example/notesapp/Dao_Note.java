package com.example.notesapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Dao_Note {
    @Insert
    void addNote(Entity_Note entityNote);

    @Query("select * from notes")
    List<Entity_Note> readNote();

    @Delete
    void deleteNote(Entity_Note entityNote);

    @Update
    void updateNote(Entity_Note entityNote);
}
