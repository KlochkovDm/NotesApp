package com.example.notesapp.domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;
import java.util.List;

public interface NotesRepository {

    void getNotes(Callback<List<Note>> callback);

    void clear(Callback<Object> callback);

    void add(String title, String description, Callback<Note> callback);

    void remove (Note note, Callback<Object> callback);

    void update (@NonNull Note note, @Nullable String title, @Nullable String description, @Nullable Date date, Callback<Note> callback);
}
