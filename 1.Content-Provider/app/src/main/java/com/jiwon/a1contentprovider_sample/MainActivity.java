package com.jiwon.a1contentprovider_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.text.format.DateFormat;
import java.util.Calendar;

import static android.provider.MediaStore.Images.ImageColumns;
import static android.provider.MediaStore.Images.Media;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText("촬!!");
        try{
            Cursor cursor = getImage();
            if(cursor.moveToFirst()){
                // 1. 각 컬럼의 열 인덱스 취득
                int idColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID);
                int titleColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.TITLE);
                int dateTakenColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN);

                // 2. 인덱스를 바탕으로 데이터를 Cursor로부터 취득
                long id = cursor.getLong(idColNum);
                String title =  cursor.getString(titleColNum);
                long dateTaken = cursor.getLong(dateTakenColNum);
                Uri imageUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,id);

                // 3. 데이터를 View로 설정

                ImageView imageView = (ImageView)findViewById(R.id.imageView);
                Calendar calendar= Calendar.getInstance();
                calendar.setTimeInMillis(dateTaken);
                CharSequence text = DateFormat.format("yyyy:MM:dd(E) hh:mm:ss",calendar);
                textView.setText("촬영일시: "+text);
                imageView.setImageURI(imageUri);

            }
            cursor.close();

        }catch(SecurityException e) {
            Toast.makeText(this, "스토리지에 접근 권한을 허가로해주세요,", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private Cursor getImage(){
        ContentResolver contentResolver = getContentResolver();
        Uri queriUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        // 가져올 칼럼명
        String[] projection = {
                MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.TITLE,
                MediaStore.Images.ImageColumns.DATE_TAKEN,
        };
        //정렬
        String sortOrder = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC";
        // 1건만 가져옴
        queriUri = queriUri.buildUpon().appendQueryParameter("limit","1").build();
        // selection.selectionArgs는 지정하지 않는다.
        return contentResolver.query(queriUri,projection,null,null,sortOrder);

    }
}
