package com.jiwon.a2wordoftoday2;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public interface WordsOfTodayContract {

    public static final String AUTORITY = "com.jiwon.a2wordoftoday2";
    public static final String TABLE_NAME = "WordsOfToday";
    public static Uri CONTENT_URI = Uri.parse(ContentResolver.SCHEME_CONTENT+"://"+AUTORITY+"/"+TABLE_NAME);

    public static final String MIME_TYPE_DIR = "vnd.android.cursor.dir/"+AUTORITY+"."+TABLE_NAME;
    public static final String MIME_TYPE_ITEM = "vnd.android.cursor.item/"+AUTORITY+"."+TABLE_NAME;

    public interface WordsOfTodayColumns extends BaseColumns{
        public static final String NAME = "name";
        public static final String WORDS = "words";
        public static final String DAtE = "date";
    }
}
