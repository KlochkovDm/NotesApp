package com.example.notesapp.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.notesapp.R;
import com.example.notesapp.domain.Note;
import com.example.notesapp.domain.NotesRepository;
import com.example.notesapp.domain.NotesRepositoryImpl;
import com.example.notesapp.ui.MainRouter;
import com.example.notesapp.ui.RouterHolder;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class UpdateNoteFragment extends Fragment {

    public static final String TAG = "UpdateNoteFragment";
    private static final String ARG_NOTE = "ARG_NOTE";

    private final NotesRepository repository = NotesRepositoryImpl.INSTANCE;
    private int selectedYear = -1;
    private int selectedMonthOfYear = -1;
    private int selectedDayOfMonth = -1;

    public static UpdateNoteFragment newInstance (Note note){
        UpdateNoteFragment fragment = new UpdateNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_done) {


                    if (requireActivity() instanceof RouterHolder) {
                        MainRouter router = ((RouterHolder) requireActivity()).getMainRouter();
                        router.back();
                    }
                    return true;
                }
                return false;
            }
        });


        if (getArguments() != null && getArguments().getParcelable(ARG_NOTE) != null) {
            Note note = getArguments().getParcelable(ARG_NOTE);

            EditText title = view.findViewById(R.id.note_title);
            title.setText(note.getTitle());
            EditText description = view.findViewById(R.id.note_description);
            description.setText(note.getDescription());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(note.getDate());

            DatePicker datePicker = view.findViewById(R.id.picker);
            datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                }
            });

        }

    }
}
