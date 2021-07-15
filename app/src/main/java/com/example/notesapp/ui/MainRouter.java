package com.example.notesapp.ui;

import androidx.fragment.app.FragmentManager;

import com.example.notesapp.R;
import com.example.notesapp.domain.Note;
import com.example.notesapp.ui.details.UpdateNoteFragment;
import com.example.notesapp.ui.list.NotesFragment;

public class MainRouter {

    private final FragmentManager fragmentManager;

    public MainRouter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showNotes() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, NotesFragment.newInstance(), NotesFragment.TAG)
                .commit();
    }

//    public void showNoteDetail(Note note) {
//        fragmentManager
//                .beginTransaction()
//                .replace(R.id.container, NoteDetailsFragment.newInstance(note), NoteDetailsFragment.TAG)
//                .addToBackStack(NoteDetailsFragment.TAG)
//                .commit();
//    }


    public void showEditNote(Note note) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, UpdateNoteFragment.newInstance(note), UpdateNoteFragment.TAG)
                .addToBackStack(UpdateNoteFragment.TAG)
                .commit();
    }

    public void back() {
        fragmentManager.popBackStack();
    }


}
