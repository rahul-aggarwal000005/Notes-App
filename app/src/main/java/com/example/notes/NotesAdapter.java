package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    ArrayList<Note> allNotes = new ArrayList<>();
    private final Context context;
    private final INotesRVAdapter listener;

    public NotesAdapter(Context context,INotesRVAdapter listener){
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notes_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.getButton().setOnClickListener(v -> listener.onItemClick(allNotes.get(viewHolder.getAdapterPosition())));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        Note currentNote = allNotes.get(position);
        holder.getTextView().setText(currentNote.getText());

    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public void updateNotes(List<Note> newList){
        allNotes.clear();
        allNotes.addAll(newList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            button = itemView.findViewById(R.id.deleteButton);
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getButton() {
            return button;
        }
    }
}

interface INotesRVAdapter{
    void onItemClick(Note note);
}