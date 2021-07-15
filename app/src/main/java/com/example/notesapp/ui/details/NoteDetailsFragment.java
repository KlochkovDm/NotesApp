package com.example.notesapp.ui.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notesapp.R;
import com.example.notesapp.domain.Note;

public class NoteDetailsFragment extends Fragment {

    private static final String ARG_NOTE = "ARG_NOTE";

    public static NoteDetailsFragment newInstance(Note note) {
        NoteDetailsFragment fragment = new NoteDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, note);
        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        return fragment;
    }

    private Note note;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView noteTitle = view.findViewById(R.id.note_title);

        if (getArguments() != null && getArguments().getParcelable(ARG_NOTE) != null) {
            note = getArguments().getParcelable(ARG_NOTE);
            noteTitle.setText(note.getTitle());
        }

    }

}