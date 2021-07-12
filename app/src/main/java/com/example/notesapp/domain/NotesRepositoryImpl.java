package com.example.notesapp.domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.notesapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NotesRepositoryImpl implements NotesRepository {

    public static final NotesRepository INSTANCE = new NotesRepositoryImpl();

    private final ArrayList<Note> notes = new ArrayList<>();

    public NotesRepositoryImpl() {
        notes.add(new Note("N1","TITLE","description", new Date()));
        notes.add(new Note("N2","TITLE1","description1", new Date()));
        notes.add(new Note("N3","TITLE2","description2", new Date()));
        notes.add(new Note("N4","TITLE3","description3", new Date()));
        notes.add(new Note("N5","TITLE4","description4", new Date()));
        notes.add(new Note("N6","TITLE5","description5", new Date()));
        notes.add(new Note("N7","TITLE","description", new Date()));
        notes.add(new Note("N8","TITLE1","description1", new Date()));
        notes.add(new Note("N9","TITLE2","description2", new Date()));
        notes.add(new Note("N10","TITLE3","description3", new Date()));
        notes.add(new Note("N11","TITLE4","description4", new Date()));
        notes.add(new Note("N12","TITLE5","description5", new Date()));
        notes.add(new Note("N13","TITLE","description", new Date()));
        notes.add(new Note("N14","TITLE1","description1", new Date()));
        notes.add(new Note("N15","TITLE2","description2", new Date()));
        notes.add(new Note("N16","TITLE3","description3", new Date()));
        notes.add(new Note("N17","TITLE4","description4", new Date()));
        notes.add(new Note("N18","TITLE5","description5", new Date()));
    }

    @Override
    public void clear() {
        notes.clear();
    }

    @Override
    public Note add(String title, String description) {
        Note note =  new Note(UUID.randomUUID().toString(), title, description, new Date());
        notes.add(note);
        return note;
    }

    @Override
    public void remove(Note note) {
        notes.remove(note);
    }

    @Override
    public Note update(@NonNull Note note, @Nullable String title, @Nullable String description, @Nullable Date date) {

        for (int i = 0; i < notes.size(); i++) {
            Note item = notes.get(i);
            if (item.getId().equals(note.getId())) {

                String titleToSet = item.getTitle();
                String descriptionToSet = item.getDescription();
                Date dateToSet = item.getDate();

                if (title != null) {
                    titleToSet = title;
                }
                if (date != null) {
                    dateToSet = date;
                }
                if (description != null) {
                    descriptionToSet = description;
                }
                Note newNote = new Note(note.getId(), titleToSet, descriptionToSet, dateToSet);
                notes.remove(i);
                notes.add(i, newNote);

                return newNote;
            }
        }
        return note;
    }

    @Override
    public List<Note> getNotes() {
        return notes;
    }
}
