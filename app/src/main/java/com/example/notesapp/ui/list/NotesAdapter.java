package com.example.notesapp.ui.list;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;
import com.example.notesapp.domain.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private final Fragment fragment;
    private final ArrayList<Note> notes = new ArrayList<>();

    public void setData(List<Note> toSet) {
        notes.clear();
        notes.addAll(toSet);
    }
    public int add(Note addedNote) {
        notes.add(addedNote);
        return notes.size()-1;
    }
    public void remove(Note longClickedNote) {
        notes.remove(longClickedNote);
    }


    private OnNoteClickedListener listener;
    private OnNoteLongClickedListener longClickedListener;

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public interface OnNoteClickedListener {
        void onNoteClickedListener(@NonNull Note note);
    }

    public OnNoteClickedListener getListener() {
        return listener;
    }

    public void setListener(OnNoteClickedListener listener) {
        this.listener = listener;
    }

    public interface OnNoteLongClickedListener {
        void onNoteLongClickedListener(@NonNull Note note, int index);
    }

    public OnNoteLongClickedListener getLongClickedListener() {
        return longClickedListener;
    }

    public void setLongClickedListener(OnNoteLongClickedListener longClickedListener) {
        this.longClickedListener = longClickedListener;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.noteTitle.setText(note.getTitle());
        holder.noteDescription.setText(note.getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle;
        TextView noteDescription;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getListener() != null) {
                        getListener().onNoteClickedListener(notes.get(getAdapterPosition()));
                    }
                }
            });

            fragment.registerForContextMenu(itemView);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemView.showContextMenu();
                    if(getLongClickedListener() != null){
                        int index = getAdapterPosition();
                        getLongClickedListener().onNoteLongClickedListener(notes.get(index), index);
                    }
                    return true;
                }
            });
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    PopupMenu menu = new PopupMenu(v.getContext(), v);
//                    menu.inflate(R.menu.menu_notes_context);
////                    requireActivity().getMenuInflater().inflate(R.menu.menu_popup,menu.getMenu());
//                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem item) {
//                            if (item.getItemId() == R.id.popup_delete) {
//                                Toast.makeText(v.getContext(), "Note deleted", Toast.LENGTH_SHORT).show();
//                                return true;
//                            }
//                            return false;
//                        }
//                    });
//                    menu.show();
//                    return true;
//                }
//            });
            noteTitle = itemView.findViewById(R.id.note_title);
            noteDescription = itemView.findViewById(R.id.note_description);
        }
    }
}
