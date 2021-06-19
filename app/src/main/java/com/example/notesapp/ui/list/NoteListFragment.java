package com.example.notesapp.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notesapp.R;
import com.example.notesapp.domain.Note;
import com.example.notesapp.domain.NotesRepository;
import com.example.notesapp.domain.NotesRepositoryImpl;
import com.example.notesapp.ui.Publisher;
import com.example.notesapp.ui.PublisherHolder;

import java.util.List;

public class NoteListFragment extends Fragment {

    public interface OnNoteClicked {
        void onNoteClicked(com.example.notesapp.domain.Note note);
    }

    private NotesRepository notesRepository;

    private OnNoteClicked onNoteClicked;

    private Publisher publisher;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnNoteClicked) {
            onNoteClicked = (OnNoteClicked) context;
        }

        if (context instanceof PublisherHolder) {
            publisher = ((PublisherHolder) context).getPublisher();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

        onNoteClicked = null;
        publisher = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notesRepository = new NotesRepositoryImpl();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout notesList = view.findViewById(R.id.note_list_container);

        List<Note> notes = notesRepository.getNotes();

        for (Note note : notes) {

            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, notesList, false);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNoteClicked != null) {
                        onNoteClicked.onNoteClicked(note);
                    }

                    if (publisher != null) {
                        publisher.notify(note);
                    }
                }
            });

            TextView cityName = itemView.findViewById(R.id.note_name);
            cityName.setText(note.getName());

            notesList.addView(itemView);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
