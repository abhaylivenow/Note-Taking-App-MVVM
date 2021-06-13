package com.example.notetakingappmvvm;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
/*
 We have to define this class as Singleton
 */
@Database(entities = {Note.class},version = 1) // here we define what entity we have to put in NoteDb
public abstract class NotesDatabase extends RoomDatabase {

    public static NotesDatabase instance;
    public abstract NoteDao noteDao();

    public static synchronized NotesDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),NotesDatabase.class
            ,"note_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
