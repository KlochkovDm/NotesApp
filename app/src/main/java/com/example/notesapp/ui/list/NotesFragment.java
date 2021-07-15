package com.example.notesapp.ui.list;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;
import com.example.notesapp.domain.Callback;
import com.example.notesapp.domain.Note;
import com.example.notesapp.domain.NotesRepository;
import com.example.notesapp.domain.NotesRepositoryImpl;
import com.example.notesapp.ui.MainActivity;
import com.example.notesapp.ui.MainRouter;
import com.example.notesapp.ui.RouterHolder;
import com.example.notesapp.ui.details.UpdateNoteFragment;

import java.util.Collections;
import java.util.List;

public class NotesFragment extends Fragment {

    private int longClickedIndex;
    private Note longClickedNote;

    private ProgressBar progressBar;
    private boolean isLoading = false;

    public static final String TAG = "NotesFragment";
    private final NotesRepository repository = NotesRepositoryImpl.INSTANCE;
    private NotesAdapter notesAdapter;


    public static NotesFragment  newInstance() {
        return new NotesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesAdapter = new NotesAdapter(this);

        isLoading = true;

        repository.getNotes(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> result) {
                notesAdapter.setData(result);
                notesAdapter.notifyDataSetChanged();

                isLoading  = false;
                if(progressBar != null){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

//        notesAdapter.notifyDataSetChanged();


        notesAdapter.setListener(new NotesAdapter.OnNoteClickedListener() {
            @Override
            public void onNoteClickedListener(@NonNull Note note) {

//                if (requireActivity() instanceof MainActivity) {
//                    MainActivity mainActivity = (MainActivity) requireActivity();
//                    mainActivity.getRouter().showEditNote(note);}
//
                if (requireActivity() instanceof RouterHolder) {
                    MainRouter router = ((RouterHolder) requireActivity()).getMainRouter();
                    router.showEditNote(note);
                }
            }
        });

        notesAdapter.setLongClickedListener(new NotesAdapter.OnNoteLongClickedListener() {
            @Override
            public void onNoteLongClickedListener(@NonNull Note note, int index) {
                longClickedIndex = index;
                longClickedNote = note;

            }
        });


        getParentFragmentManager().setFragmentResultListener(UpdateNoteFragment.UPDATE_RESULT, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if (result.containsKey(UpdateNoteFragment.ARG_NOTE)){
                Note note = result.getParcelable(UpdateNoteFragment.ARG_NOTE);
                notesAdapter.update(note);

//                notesAdapter.notifyItemChanged();

                }
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        androidx.appcompat.widget.Toolbar toolbar = view.findViewById(R.id.toolbar);
        RecyclerView notesList = view.findViewById(R.id.note_list_container);
        progressBar = view.findViewById(R.id.progress);

        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        }

        toolbar.setOnMenuItemClickListener(new androidx.appcompat.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_add) {
                    Note addedNote = repository.add("NEW TITLE", "NEW DESCRIPTION");
                    int index = notesAdapter.add(addedNote);
                    notesAdapter.notifyItemInserted(index);
                    notesList.scrollToPosition(index);
                    return true;
                }
                if (item.getItemId() == R.id.action_clear) {
                    repository.clear();
                    notesAdapter.setData(Collections.emptyList());
                    notesAdapter.notifyDataSetChanged();
                    return true;
                }

                return false;
            }
        });


        notesList.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), GridLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_separator));
        notesList.addItemDecoration(dividerItemDecoration);

        notesList.setAdapter(notesAdapter);


    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.menu_notes_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_update){
            if (requireActivity() instanceof RouterHolder) {
                MainRouter router = ((RouterHolder) requireActivity()).getMainRouter();
                router.showEditNote(longClickedNote);
            }

            return true;
        }
        if(item.getItemId() == R.id.action_delete){
            repository.remove(longClickedNote);
            notesAdapter.remove(longClickedNote);
            notesAdapter.notifyItemRemoved(longClickedIndex);

            return true;
        }

        return super.onContextItemSelected(item);
    }
}
