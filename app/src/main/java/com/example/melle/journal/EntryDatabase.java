package com.example.melle.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    // database instantiëren
    public static EntryDatabase instance;

    public EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // als er nog geen database is, wordt er eentje gemaakt met naam db
    public static EntryDatabase getInstance(Context context){
        if (instance == null){
            instance = new EntryDatabase(context, "db", null,1);
        }
        return instance;
    }

    // er wordt een tabel gemaakt
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table db (_id INTEGER PRIMARY KEY, Title TEXT, Content TEXT, Mood TEXT, Time DATETIME default current_timestamp);");
    }

    // tabel wordt verwijderd en opnieuw gecreated
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if exists" + " db");
        onCreate(db);
    }

    // hele database wordt geselecteerd
    public Cursor selectAll(){
        SQLiteDatabase db = getWritableDatabase();
        String everything = "SELECT * FROM" + " db";
        Cursor cr = db.rawQuery(everything,null);
        return cr;
    }

    // er wordt een nieuwe Journal aan de de database toegevoegd
    public void insert(JournalEntry journal) {
        ContentValues values = new ContentValues();
        values.put("title", journal.getTitle());
        values.put("content", journal.getContent());
        values.put("mood", journal.getMood());

        getWritableDatabase().insert("db", null, values);
    }

    // er wordt een journal uit de database verwijderd
    static void delete(long id) {
        SQLiteDatabase db = instance.getWritableDatabase();
        db.delete("db", "_id = " + id, null);
    }
}


