package com.jiwon.a2wordoftoday2;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WordsOfTodayDbHelper extends SQLiteOpenHelper {

    public WordsOfTodayDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler){
        super(context, name, factory, version, errorHandler);
    }
    @Override
    public void onCreate(SQLiteDatabase db){

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }

    private void execSQL(SQLiteDatabase db, String sql){

        db.execSQL(sql);
    }
}
