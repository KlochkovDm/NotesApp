package com.example.notesapp.ui.details;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesapp.R;
import com.example.notesapp.domain.Note;
import com.example.notesapp.ui.Observer;
import com.example.notesapp.ui.Publisher;
import com.example.notesapp.ui.PublisherHolder;

public class NoteDetailsFragment extends Fragment implements Observer {



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

    private Publisher publisher;
    private Note note;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof PublisherHolder) {
            publisher = ((PublisherHolder) context).getPublisher();

            publisher.subscribe(this);
        }
    }

    @Override
    public void onDetach() {
        if (publisher != null) {
            publisher.unsubscribe(this);
        }
        super.onDetach();
    }

    public NoteDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView noteName = view.findViewById(R.id.note_name);

        if (getArguments() != null && getArguments().getParcelable(ARG_NOTE) != null) {
            note = getArguments().getParcelable(ARG_NOTE);
            noteName.setText(note.getName());
        }


    }

    @Override
    public void updateNote(Note note) {
        Toast.makeText(requireContext(), note.getName(), Toast.LENGTH_LONG).show();
    }
}