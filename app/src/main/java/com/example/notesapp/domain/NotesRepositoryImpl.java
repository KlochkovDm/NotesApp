package com.example.notesapp.domain;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.notesapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotesRepositoryImpl implements NotesRepository {

    public static final NotesRepository INSTANCE = new NotesRepositoryImpl();

    private final ArrayList<Note> notes = new ArrayList<>();

    private ExecutorService executor = Executors.newCachedThreadPool();

    private Handler handler = new Handler(Looper.getMainLooper());

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
    public void getNotes(Callback<List<Note>> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(notes);
                    }
                });
            }
        });

    }

    @Override
    public void clear(Callback<Object> callback) {
        notes.clear();

        callback.onSuccess(notes);
    }

    @Override
    public void add(String title, String description, Callback<Note> callback) {
        Note note =  new Note(UUID.randomUUID().toString(), title, description, new Date());
        notes.add(note);

        callback.onSuccess(note);
    }

    @Override
    public void remove(Note note, Callback<Object> callback) {
        notes.remove(note);
        callback.onSuccess(note);
    }

    @Override
    public void update(@NonNull Note note, @Nullable String title, @Nullable String description, @Nullable Date date, Callback<Note> callback) {

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


                callback.onSuccess(newNote);
            }
        }
        callback.onSuccess(note);
    }

}
