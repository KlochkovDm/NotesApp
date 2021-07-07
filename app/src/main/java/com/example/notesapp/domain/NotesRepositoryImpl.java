package com.example.notesapp.domain;

import com.example.notesapp.R;

import java.util.ArrayList;
import java.util.List;

public class NotesRepositoryImpl implements NotesRepository {

    @Override
    public List<Note> getNotes() {
        ArrayList<Note> result = new ArrayList<>();

        result.add(new Note(R.string.test_text, R.string.test_text, R.string.test_text));
        result.add(new Note(R.string.test_text2, R.string.test_text2, R.string.test_text2));
        result.add(new Note(R.string.test_text3, R.string.test_text3, R.string.test_text3));
        result.add(new Note(R.string.test_text, R.string.test_text, R.string.test_text));
        result.add(new Note(R.string.test_text2, R.string.test_text2, R.string.test_text2));
        result.add(new Note(R.string.test_text3, R.string.test_text3, R.string.test_text3));
        result.add(new Note(R.string.test_text, R.string.test_text, R.string.test_text));
        result.add(new Note(R.string.test_text2, R.string.test_text2, R.string.test_text2));
        result.add(new Note(R.string.test_text3, R.string.test_text3, R.string.test_text3));
        result.add(new Note(R.string.test_text, R.string.test_text, R.string.test_text));
        result.add(new Note(R.string.test_text2, R.string.test_text2, R.string.test_text2));
        result.add(new Note(R.string.test_text3, R.string.test_text3, R.string.test_text3));
        result.add(new Note(R.string.test_text, R.string.test_text, R.string.test_text));
        result.add(new Note(R.string.test_text2, R.string.test_text2, R.string.test_text2));
        result.add(new Note(R.string.test_text3, R.string.test_text3, R.string.test_text3));
        result.add(new Note(R.string.test_text, R.string.test_text, R.string.test_text));
        result.add(new Note(R.string.test_text2, R.string.test_text2, R.string.test_text2));
        result.add(new Note(R.string.test_text3, R.string.test_text3, R.string.test_text3));
        result.add(new Note(R.string.test_text, R.string.test_text, R.string.test_text));
        result.add(new Note(R.string.test_text2, R.string.test_text2, R.string.test_text2));
        result.add(new Note(R.string.test_text3, R.string.test_text3, R.string.test_text3));
        result.add(new Note(R.string.test_text, R.string.test_text, R.string.test_text));
        result.add(new Note(R.string.test_text2, R.string.test_text2, R.string.test_text2));
        result.add(new Note(R.string.test_text3, R.string.test_text3, R.string.test_text3));

        return result;
    }
}
