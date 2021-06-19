package com.example.notesapp.ui;

import com.example.notesapp.domain.Note;

public interface Observer {

    void updateNote(Note note);
}
