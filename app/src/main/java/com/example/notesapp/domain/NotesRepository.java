package com.example.notesapp.domain;

import java.util.List;

public interface NotesRepository {

    List<Note> getNotes();
}
