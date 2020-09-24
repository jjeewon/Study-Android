package com.jiwon.a2wordoftoday2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.AUTORITY;
import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.MIME_TYPE_DIR;
import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.MIME_TYPE_ITEM;
import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.TABLE_NAME;

/**
 * SQLite를 백엔드로 하여 동작하는 ContentProvider
 */
public class WordOfTodayProvider extends ContentProvider {

    private static final String TAG = WordsOfToday.class.getSimpleName();
    private static final UriMatcher uriMatcher;

    private static final int ROW_DIR = 1;
    private static final int ROW_ITEM =  2;

    private static int sLastId = 0;
    static{
        uriMatcher = new UriMatcher(
                UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTORITY,TABLE_NAME,ROW_DIR);
        uriMatcher.addURI(AUTORITY,TABLE_NAME+"/#",ROW_ITEM);
    }

    private WordsOfTodayDbHelper mDbHelper;

    public WordOfTodayProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case ROW_ITEM:
                int id =  (int) ContentUris.parseId(uri);
                synchronized (mDbHelper) {
                    Log.d(TAG, "delete(item) id=" + id);
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    count = db.delete(TABLE_NAME, _ID+"=?", new String[]{ Long.toString(id) });
                    if (count > 0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                    }
                }
                break;
            case ROW_DIR:
                synchronized (mDbHelper) {
                    Log.d(TAG, "delete(dir)");
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    count = db.delete(TABLE_NAME, selection, selectionArgs);
                    if (count > 0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("인수의 URI가 틀렸습니다.");
        }
        return count;
    }

    @Override
    public String getType(Uri uri) {
       switch(uriMatcher.match(uri)){
           case ROW_DIR:
               return MIME_TYPE_DIR;
           case ROW_ITEM:
               return MIME_TYPE_ITEM;
           default:
               return null;
       }

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
       Uri resultUri = null;
       switch(uriMatcher.match(uri)){
           case ROW_DIR:
               synchronized (mDbHelper){
                   SQLiteDatabase db = mDbHelper.getWritableDatabase();
                   long lastId = db.insert(TABLE_NAME,null,values);
                   resultUri = ContentUris.withAppendedId(uri,lastId);
                   Log.d(TAG,"WordsOfTOdayProvider insert "+ values);
                   //변경 통지
                   getContext().getContentResolver().notifyChange(resultUri,null);
               }
               break;
           default:
               throw new IllegalArgumentException("인수 uri 틀림");
       }
       return resultUri;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case ROW_DIR:
                Log.d(TAG,"queary(dir) uri = "+uri.toString());
                synchronized (mDbHelper){
                    SQLiteDatabase db= mDbHelper.getReadableDatabase();
                    cursor = db.query(TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                }
                return cursor;

            case ROW_ITEM:
                Log.d(TAG,"query(item) uri = "+uri.toString());
                synchronized (mDbHelper){
                    long id = ContentUris.parseId(uri);
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    cursor = db.query(TABLE_NAME,projection,_ID,new String[] {Long.toString(id)},null,null,null);
                }
                break;
            default:
                throw new IllegalArgumentException("인수의 URI가 틀렸음");
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case ROW_DIR:
                Log.d(TAG,"update (dir) values = "+values);
                synchronized (mDbHelper){
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    count = db.update(TABLE_NAME,values,selection,selectionArgs);
                    if(count > 0){
                        // 업데이트 통지
                        getContext().getContentResolver().notifyChange(uri,null);
                    }
                    break;
                }
            case ROW_ITEM:
                Log.d(TAG,"update(item) values"+values);
                int id = (int)ContentUris.parseId(uri);
                synchronized (mDbHelper){
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    count =  db.update(TABLE_NAME,values,_ID+"=?",new String[] {Long.toString(id)});
                    if(count > 0){
                        // 업데이트 통지
                        getContext().getContentResolver().notifyChange(uri,null);
                    }
                    break;
                }
            default:
                throw new IllegalArgumentException("인수의 uri 틀림");
        }
        return count;
    }
}
