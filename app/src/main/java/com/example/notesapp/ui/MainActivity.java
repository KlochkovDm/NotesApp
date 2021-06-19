package com.example.notesapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notesapp.R;
import com.example.notesapp.domain.Note;
import com.example.notesapp.ui.details.NoteDetailsActivity;
import com.example.notesapp.ui.details.NoteDetailsFragment;
import com.example.notesapp.ui.list.NoteListFragment;

public class MainActivity extends AppCompatActivity implements NoteListFragment.OnNoteClicked, PublisherHolder {

    private final Publisher publisher = new Publisher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onNoteClicked(Note note) {

        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);

        if (isLandscape) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.notes_details_fragment, NoteDetailsFragment.newInstance(note))
                    .commit();

        } else {
            Intent intent = new Intent(this, NoteDetailsActivity.class);
            intent.putExtra(NoteDetailsActivity.ARG_NOTE, note);
            startActivity(intent);

//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.note_list_container, NoteDetailsFragment.newInstance(note))
//                    .commit();
        }
    }

    @Override
    public Publisher getPublisher() {
        return publisher;
    }
}