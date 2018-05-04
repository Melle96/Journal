package com.example.melle.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    // als de button wordt aangeklikt worden alle gegevens opgeslagen in database
    public void ButtonClicked(View view) {

        EditText title = findViewById(R.id.title);
        EditText content = findViewById(R.id.content);
        TextView mood = findViewById(R.id.mood);

        String titlee = title.getText().toString();
        String moodd = mood.getText().toString();
        String contentt = content.getText().toString();

        // title, content en mood in Journalentry opslaan
        JournalEntry journal = new JournalEntry(titlee, contentt, moodd);

        // journal opslaan in database
        EntryDatabase.getInstance(this).insert(journal);
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // als plaatje boos aangeklikt mood op mad zetten
    public void boos(View view) {
        TextView mood = findViewById(R.id.mood);
        mood.setText("mad");
    }

    public void blij(View view) {
        TextView mood = findViewById(R.id.mood);
        mood.setText("happy");
    }

    public void neutraal(View view) {
        TextView mood = findViewById(R.id.mood);
        mood.setText("neutral");
    }

    // bij rotatie opslaan mood
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        TextView mood = findViewById(R.id.mood);
        outState.putString("mooddd", String.valueOf(mood.getText()));

    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView mood = findViewById(R.id.mood);
        String moodd = savedInstanceState.getString(("mooddd"));
        mood.setText(moodd);
    }
}

