package com.jiwon.a2wordoftoday2;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.TABLE_NAME;
import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.WordsOfTodayColumns.DATE;
import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.WordsOfTodayColumns.NAME;
import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.WordsOfTodayColumns.WORDS;

public class WordsOfTodayDbHelper extends SQLiteOpenHelper {

    private static final String TAG = WordsOfToday.class.getSimpleName();
    public static final String DB_NAME = "WordsOfToday.db";
    public static final int DB_VERSION = 1;

    private static final String SQL_CREATE_TABLE =
            String.format("CREATE TABLE %s (\n", TABLE_NAME)+
                    String.format("%s INTEGER PRIMARY KEY AUTOINCREMENT,\n",_ID)+
                    String.format("%s TEXT \n", NAME)+
                    String.format("%s TEXT \n",WORDS)+
                    String.format("%s TEXT",DATE);
    private static final String[] SQL_INSERT_INITIAL_DATA = {
            String.format("INSERT INTO %s (%s, %s, %s)"+"VALUES('Donghan','goood','20200914')",TABLE_NAME,NAME,WORDS,DATE),
            String.format("INSERT INTO %s (%s, %s, %s)"+"VALUES('Jiwon','Tired','20200917')",TABLE_NAME,NAME,WORDS,DATE),
            String.format("INSERT INTO %s (%s, %s, %s)"+"VALUES('Gomdol','Happy','20200918')",TABLE_NAME,NAME,WORDS,DATE),
    };
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
