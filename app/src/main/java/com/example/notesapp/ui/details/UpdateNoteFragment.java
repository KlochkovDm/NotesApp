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
import com.example.notesapp.domain.Callback;
import com.example.notesapp.domain.Note;
import com.example.notesapp.domain.NotesFirestoreRepository;
import com.example.notesapp.domain.NotesRepository;
import com.example.notesapp.domain.NotesRepositoryImpl;
import com.example.notesapp.ui.MainRouter;
import com.example.notesapp.ui.RouterHolder;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class UpdateNoteFragment extends Fragment {

    public static final String TAG = "UpdateNoteFragment";
    public static final String ARG_NOTE = "ARG_NOTE";
    public static final String UPDATE_RESULT = "UPDATE_RESULT";

    private final NotesRepository repository = NotesFirestoreRepository.INSTANCE;
    private int selectedYear = -1;
    private int selectedMonthOfYear = -1;
    private int selectedDayOfMonth = -1;

    public static UpdateNoteFragment newInstance(Note note) {
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
        EditText title = view.findViewById(R.id.note_title);
        EditText description = view.findViewById(R.id.note_description);
        DatePicker datePicker = view.findViewById(R.id.picker);

        //        if (getArguments() != null && getArguments().getParcelable(ARG_NOTE) != null) {
        Note note = getArguments().getParcelable(ARG_NOTE);

        title.setText(note.getTitle());
        description.setText(note.getDescription());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(note.getDate());

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedYear = year;
                selectedMonthOfYear = monthOfYear;
                selectedDayOfMonth = dayOfMonth;

            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_done) {

                    Date selectedDate = null;
                    if (selectedYear != -1 && selectedMonthOfYear != -1 && selectedDayOfMonth != -1) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(note.getDate());
                        calendar.set(Calendar.YEAR, selectedYear);
                        calendar.set(Calendar.MONTH, selectedMonthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, selectedDayOfMonth);

                        selectedDate = calendar.getTime();
                    }
                    repository.update(note, title.getText().toString(), description.getText().toString(), selectedDate, new Callback<Note>() {
                        @Override
                        public void onSuccess(Note result) {
                            if (requireActivity() instanceof RouterHolder) {
                                MainRouter router = ((RouterHolder) requireActivity()).getMainRouter();

                                Bundle bundle = new Bundle();
                                bundle.putParcelable(ARG_NOTE, result);

                                getParentFragmentManager().setFragmentResult(UPDATE_RESULT, bundle);

                                router.back();
                            }
                        }
                    });
                    return true;
                }
                return false;
            }
        });




    }

}
