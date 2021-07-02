package com.example.notesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private String[] title;
    private String[] description;
    private int[] id;
    private Context context;

    public RecyclerAdapter(String[] title, String[] description, int[] id, Context context) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, context);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int Id = id[position];
        String title_id = title[position];
        String description_id = description[position];
        holder.id.setText("" + Id);
        holder.title.setText(title_id);
        holder.description.setText(description_id);
    }

    public void deleteItem(int position) {
        Entity_Note entityNote = new Entity_Note();
        entityNote.setNote_id(position);
        MainActivity.databaseNote.daoNote().deleteNote(entityNote);
    }

    @Override
    public int getItemCount() {
        return id.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, description, id;
        Context context;

        public MyViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            id = itemView.findViewById(R.id.text_view_priority);
            title = itemView.findViewById(R.id.text_view_title);
            description = itemView.findViewById(R.id.text_view_description);
            this.context = context;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAbsoluteAdapterPosition();
            Toast.makeText(context, "Position: " + position, Toast.LENGTH_SHORT).show();
        }
    }

}
