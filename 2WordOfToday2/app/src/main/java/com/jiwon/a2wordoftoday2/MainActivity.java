package com.jiwon.a2wordoftoday2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.WordsOfTodayColumns._ID;
import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.WordsOfTodayColumns.NAME;
import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.WordsOfTodayColumns.WORDS;
import static com.jiwon.a2wordoftoday2.WordsOfTodayContract.WordsOfTodayColumns.DATE;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int LOADER_ID = 1;

    private static final String[] PROJECTIONS = new String[]{
        _ID, NAME, WORDS, DATE
    };

    private CursorAdapter mAdapter;
    private ContentObserver mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(BuildConfig.DEBUG){
            Context context = getApplicationContext();
            Stetho.initializeWithDefaults(this);
        }
        setContentView(R.layout.activity_main);

        mAdapter = new CursorAdapter(getApplicationContext(),null,false) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                Log.d(TAG,"new View");
                return View.inflate(context,R.layout.list_item,null);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
                String words = cursor.getString(cursor.getColumnIndexOrThrow(WORDS));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(DATE));
                TextView nameText = (TextView)findViewById(R.id.nameText);
                TextView wordsText = (TextView)findViewById(R.id.wordsText);
                TextView dateText = (TextView)findViewById(R.id.dateText);
                date = date.substring(0,4)+"/"+date.substring(4,6)+"/"+date.substring(6);
                Log.d(TAG, "bindView name : "+name+", date Text= "+date);
                nameText.setText(name);
                wordsText.setText(words);
                dateText.setText(date);
            }
        };
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onResume(){
        super.onResume();
        getSupportLoaderManager().restartLoader(LOADER_ID,null,this);
    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader");
        return new CursorLoader(
                MainActivity.this, WordsOfTodayContract.CONTENT_URI,
                PROJECTIONS, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "onLoadFinished");
        // 변경을 감시하는 Uri를 설정한다
        data.setNotificationUri(getContentResolver(), WordsOfTodayContract.CONTENT_URI);
        mAdapter.swapCursor(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
