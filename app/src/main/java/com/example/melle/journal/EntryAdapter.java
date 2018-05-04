package com.example.melle.journal;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.sql.Timestamp;

public class EntryAdapter extends ResourceCursorAdapter {

    EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }


    // maakt de title, mood  en time voor list in mainactivity
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // string title, time, mood verkrijgen
        int title =  cursor.getColumnIndex("Title");
        String myTitle = cursor.getString(title);

        int time =  cursor.getColumnIndex("Time");
        String timee = (Timestamp.valueOf(cursor.getString(time))).toString();

        int mood =  cursor.getColumnIndex("Mood");
        String Mood = cursor.getString(mood);

        // title, time en mood zetten
        TextView timeStamp = view.findViewById(R.id.time);
        timeStamp.setText(timee);

        TextView Moodd = view.findViewById(R.id.mood);
        Moodd.setText(Mood);

        TextView titlee = view.findViewById(R.id.title);
        titlee.setText(myTitle);
    }
}

