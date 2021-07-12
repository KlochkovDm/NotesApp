package com.example.notesapp.ui.list;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;
import com.example.notesapp.domain.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    public interface OnNoteClickListener {
        void onNoteClickListener(@NonNull Note note);
    }

    private ArrayList<Note> notes = new ArrayList<>();
    public void setData(List<Note> toSet) {
        notes.clear();
        notes.addAll(toSet);
        }

    private OnNoteClickListener listener;

    public OnNoteClickListener getListener() {
        return listener;
    }

    public void setListener(OnNoteClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent , false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        Note note= notes.get(position);
        holder.noteName.setText(note.getName());
        holder.noteDescription.setText(note.getName());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteName;
        TextView noteDescription;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getListener() != null) {
                    getListener().onNoteClickListener(notes.get(getAdapterPosition()));
                }
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu menu = new PopupMenu(v.getContext(), v);
                    menu.inflate(R.menu.menu_popup);
//                    requireActivity().getMenuInflater().inflate(R.menu.menu_popup,menu.getMenu());
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if(item.getItemId()==R.id.popup_delete){
                                Toast.makeText(v.getContext(), "Note deleted", Toast.LENGTH_SHORT).show();
                                return true;
                            }
                            return false;
                        }
                    });
                    menu.show();
                    return true;
                }
        });
        noteName = itemView.findViewById(R.id.note_name);
        noteDescription = itemView.findViewById(R.id.note_description);
    }
}
}
