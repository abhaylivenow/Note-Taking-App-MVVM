package com.example.notetakingappmvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_layout,parent,false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.txt_title.setText(currentNote.getTitle());
        holder.txt_desc.setText(currentNote.getDescription());
        holder.txt_priority.setText(String.valueOf(currentNote.getId()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position){
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView txt_title, txt_desc, txt_priority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.text_view_title);
            txt_desc = itemView.findViewById(R.id.text_view_description);
            txt_priority = itemView.findViewById(R.id.text_view_priority);
        }
    }

}
