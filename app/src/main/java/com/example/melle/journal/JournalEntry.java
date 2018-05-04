package com.example.melle.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable {
    // variabelen instantiëren
    int id;
    String title;
    String content;
    String mood;
    String timestamp;

    // functie Journalentry gemaakt waarin title content en mood gezet worden
    public JournalEntry(String title, String content, String mood) {
        this.title = title;
        this.content = content;
        this.mood = mood;
    }

    // returnfuncties voor title, content, mood en time gemaakt
    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return timestamp;
    }

    // set functies voo title en time gemaakt
    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.timestamp = time;
    }
}
