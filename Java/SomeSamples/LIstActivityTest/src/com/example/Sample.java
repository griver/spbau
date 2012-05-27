package com.example;

import android.app.ListActivity;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.Settings;

import android.provider.MediaStore.Audio.Media;
import android.widget.SimpleCursorAdapter;

public class  extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String[] projection = new String[] {Browser.BookmarkColumns._ID,
                Browser.BookmarkColumns.TITLE,
                Browser.BookmarkColumns.URL};
        String[] displayFields = new String[] {Browser.BookmarkColumns.TITLE,
                Browser.BookmarkColumns.URL};
        int[] displayViews = new int[] { android.R.id.text1,
                android.R.id.text2 };

        Cursor cur = managedQuery(android.provider.Browser.BOOKMARKS_URI,
                projection, null, null, null);
        setListAdapter(new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, cur,
                displayFields, displayViews));
    }
}

