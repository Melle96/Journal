package com.example.melle.journal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    // instantiëren floatbutton en list
    FloatingActionButton floatingActionButton;
    ListView listt;

    @Override

    // hierbij wordt de layout gecreëerd
    // ook de list met bijbehorende journal verwijzingen worden opgehaald
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listt = findViewById(R.id.list);
        update();


        listt.setOnItemClickListener(new ListClickListener());
        listt.setOnItemLongClickListener(new ListLongClickListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }

    // haalt data van database en stopt het in de list
    private void update() {

        // database context opgehaald
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();
        EntryAdapter adapter = new EntryAdapter(MainActivity.this, cursor);

        // database context wordt in list gezet
        listt = (ListView) findViewById(R.id.list);
        listt.setAdapter(adapter);

    }

    // als de floatbutton wordt aangeklikt, gaat hij naar sherm journal toevoegen
    public void FloatButtonClicked(View view) {
        Intent intentInput = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intentInput);
    }


    // als er op een journal uit list geklikt wordt, nieuwe window instantiëren
    private class ListClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);

            // juiste journal verkrijgen
            Cursor journal = (Cursor) adapterView.getItemAtPosition(i);

            // tekst voor journal zetten
            String title = journal.getString(journal.getColumnIndex("Title"));
            String content = journal.getString(journal.getColumnIndex("Content"));
            String mood = journal.getString(journal.getColumnIndex("Mood"));
            JournalEntry rightJournal = new JournalEntry(title, content, mood);

            intent.putExtra("rightJournal", rightJournal);

            // toegang tot nieuwe window
            startActivity(intent);
        }
    }

    // als er lang wordt geklikt wordt journal verwijderd uit lijst
    private class ListLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

            ListView listview = findViewById(R.id.list);
            int listelement = listview.getPositionForView(view);

            // juiste listelement wordt gedeleted
            EntryDatabase.delete(listelement + 1);
            update();
            return false;
        }
    }

    // options menu wordt gemaakt
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // alle journals verwijderen wordt als functie toegevoegd aan menu
    public void remove_journals_method(MenuItem item) {
        SQLiteDatabase db = EntryDatabase.instance.getWritableDatabase();
        db.delete("db", null, null);
        update();
    }

    // alle journals verwijderen wordt als functie toegevoegd aan menu
    public void add_standard_journal(MenuItem item) {
        String title = "busy";
        String mood = "neutral";
        String content = "I don't have any time to add a story at the moment. I'm very busy.";

        // title, content en mood in Journalentry opslaan
        JournalEntry journal = new JournalEntry(title, content, mood);

        // journal opslaan in database
        EntryDatabase.getInstance(this).insert(journal);

        update();
    }

}
