package com.example.notetakingappmvvm;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/*
 We have to define this class as Singleton
 */
@Database(entities = {Note.class},version = 1) // here we define what entity we have to put in NoteDb
public abstract class NotesDatabase extends RoomDatabase {

    public static NotesDatabase instance;
    public abstract NoteDao noteDao();

    // This is how we define singleton class for database
    public static synchronized NotesDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),NotesDatabase.class
            ,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(instance).execute();
        }
    };
    // Fill the database with some default note when the app opens up for the first time
    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        private PopulateDbAsync(NotesDatabase db){
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title1","Desc1",1));
            noteDao.insert(new Note("Title2","Desc2",2));
            noteDao.insert(new Note("Title3","Desc3",3));
            return null;
        }
    }
}
