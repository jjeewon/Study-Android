package com.jiwon.a2wordoftoday2;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.TABLE_NAME;
import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.WordsOfTodayColumns.DAtE;
import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.WordsOfTodayColumns.NAME;
import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.WordsOfTodayColumns.WORDS;

public class WordsOfTodayDbHelper extends SQLiteOpenHelper {

    private static final String TAG = WordsOfToday.class.getSimpleName();
    public static final String DB_NAME = "WordsOfToday.db";
    public static final int DB_VERSION = 1;


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
