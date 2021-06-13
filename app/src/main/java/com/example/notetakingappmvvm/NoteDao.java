package com.example.notetakingappmvvm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/*
 Dao stands for Data Access Object
 It saves us from writing so much boiler plate codes of SQLiteOpenHelper class
 */
@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAll();

    /*
    Here you can see we don't have to use the cursor to get data from table
    this will return the note_table directly as List (return type of method)
     */
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();
}
