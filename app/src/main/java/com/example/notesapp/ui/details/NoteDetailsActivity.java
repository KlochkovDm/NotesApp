package com.example.notesapp.ui.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.notesapp.R;
import com.example.notesapp.domain.Note;

public class NoteDetailsActivity extends AppCompatActivity {

    public static final String ARG_NOTE = "ARG_NOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        if (savedInstanceState == null) {

            Note note = getIntent().getParcelableExtra(ARG_NOTE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, NoteDetailsFragment.newInstance(note))
                    .commit();

        }
    }
}