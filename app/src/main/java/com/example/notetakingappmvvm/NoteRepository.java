package com.example.notetakingappmvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NotesDatabase database = NotesDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }
    // Here all these information will be passed to ViewModel
    public void insert(Note note){
        new InsertNoteAsync(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateNoteAsync(noteDao).execute(note);
    }

    public void delete(Note note){
        new DeleteNoteAsync(noteDao).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNoteAsync(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    /*
    These async classes take care of all operation.
    All these operations must execute in the background thread, otherwise it will freeze our UI (Main thread)
     */
    private static class InsertNoteAsync extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private InsertNoteAsync(NoteDao noteDao){
            this.noteDao = noteDao;  // we need noteDao for db operations
        }

        @Override
        protected Void doInBackground(Note... notes) {
            // Here in this method we execute things that we want in background thread
            noteDao.insert(notes[0]); // since there are only one note so I have passed 0 as the index
            return null;
        }
    }

    private static class UpdateNoteAsync extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private UpdateNoteAsync(NoteDao noteDao){
            this.noteDao = noteDao;  // we need noteDao for db operations
        }

        @Override
        protected Void doInBackground(Note... notes) {
            // Here in this method we execute things that we want in background thread
            noteDao.update(notes[0]); // since there are only one note so I have passed 0 as the index
            return null;
        }
    }

    private static class DeleteNoteAsync extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private DeleteNoteAsync(NoteDao noteDao){
            this.noteDao = noteDao;  // we need noteDao for db operations
        }

        @Override
        protected Void doInBackground(Note... notes) {
            // Here in this method we execute things that we want in background thread
            noteDao.delete(notes[0]); // since there are only one note so I have passed 0 as the index
            return null;
        }
    }

    private static class DeleteAllNoteAsync extends AsyncTask<Void,Void,Void>{

        private NoteDao noteDao;

        private DeleteAllNoteAsync(NoteDao noteDao){
            this.noteDao = noteDao;  // we need noteDao for db operations
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Here in this method we execute things that we want in background thread
            noteDao.deleteAll();
            return null;
        }
    }
}
