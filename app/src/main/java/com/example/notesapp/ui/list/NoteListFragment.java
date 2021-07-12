package com.example.notesapp.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;
import com.example.notesapp.domain.Note;
import com.example.notesapp.domain.NotesRepository;
import com.example.notesapp.domain.NotesRepositoryImpl;
import com.example.notesapp.ui.Publisher;
import com.example.notesapp.ui.PublisherHolder;

import java.util.List;

public class NoteListFragment extends Fragment {

    public interface OnNoteClicked {
        void onNoteClicked(Note note);
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
        setHasOptionsMenu(true);

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

        RecyclerView notesList = view.findViewById(R.id.note_list_container);
        notesList.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), GridLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_separator));
        notesList.addItemDecoration(dividerItemDecoration);


        List<Note> notes = notesRepository.getNotes();

        NotesAdapter notesAdapter = new NotesAdapter();
        notesAdapter.setData(notes);
        notesAdapter.setListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClickListener(@NonNull Note note) {
                    if (onNoteClicked != null) {
                        onNoteClicked.onNoteClicked(note);
                    }
            }
        });

        notesList.setAdapter(notesAdapter);
        notesAdapter.notifyDataSetChanged();

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
