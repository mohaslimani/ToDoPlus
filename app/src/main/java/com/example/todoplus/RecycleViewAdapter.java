package com.example.todoplus;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    List<Note> notes;
    Logger  logger;

    public  RecycleViewAdapter(Context context)
    {
        logger = new Logger(context);
        notes = logger.restore();
    }

    public void addNote(String title, String content)
    {
        notes.add(new Note(title, content));
        logger.save(notes);
        notifyItemInserted(notes.size() - 1);
    }

    public void removeNote(Note note)
    {
        notes.remove(note);
        logger.save(notes);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position) {
        holder.title.setText(notes.get(position).title);
        holder.itemView.setOnClickListener(v -> onNoteClick(v.getContext(), position));
    }

    public void onNoteClick(Context context, int position) {
        Note note = notes.get(position);
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(note.title)
                .setMessage(note.content)
                .setPositiveButton("Ok", null)
                .setNegativeButton("msa7ni", (dialog, which) -> removeNote(note))
                .create();
        alertDialog.setOnShowListener(dialog -> {
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(0xffff0000);
        });
        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}
