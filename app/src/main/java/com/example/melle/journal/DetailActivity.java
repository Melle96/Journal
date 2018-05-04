package com.example.melle.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    // informatie verkrijgen van ListClickListener en dit neer zetten naar activity_detail
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        // tekst Journal opvragen
        JournalEntry journal = (JournalEntry) intent.getSerializableExtra("rightJournal");

        // alles juiste tekst meegeven
        TextView title = findViewById(R.id.title);
        TextView content = findViewById(R.id.content);
        TextView mood = findViewById(R.id.mood);
        TextView time = findViewById(R.id.time);

        title.setText(journal.getTitle());
        content.setText(journal.getContent());
        mood.setText(journal.getMood());
        time.setText(journal.getTime());
    }

    // als de plus wordt aangeklikt naar inputactivity
    public void ButtonClicked(View view) {
        Intent intentInput = new Intent(DetailActivity.this, InputActivity.class);
        startActivity(intentInput);
    }
}
