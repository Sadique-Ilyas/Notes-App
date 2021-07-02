package com.example.notesapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Entity_Note.class}, version = 3)
public abstract class Database_Note extends RoomDatabase {
    public abstract Dao_Note daoNote();
}
