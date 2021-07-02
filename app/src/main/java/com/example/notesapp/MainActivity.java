package com.example.notesapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static Database_Note databaseNote;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        databaseNote = Room.databaseBuilder(this, Database_Note.class, "noteDB.db").allowMainThreadQueries().fallbackToDestructiveMigrationFrom(2).build();

        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewNoteActivity.class));
                class AddNoteAsyncTask extends AsyncTask<Entity_Note, Void, Void> {
                    @Override
                    protected Void doInBackground(Entity_Note... entity_notes) {
                        return null;
                    }
                }
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                recyclerAdapter.deleteItem(viewHolder.getBindingAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Entity_Note> entityNotes = MainActivity.databaseNote.daoNote().readNote();
        int[] id = new int[entityNotes.size()];
        String[] title = new String[entityNotes.size()];
        String[] description = new String[entityNotes.size()];
        int i = 0;
        for (Entity_Note entityNote : entityNotes) {
            int note_id = entityNote.getNote_id();
            String note_title = entityNote.getTitle();
            String note_description = entityNote.getDescription();


            id[i] = note_id;
            title[i] = note_title;
            description[i] = note_description;
            i++;
        }


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter(title, description, id, this);
        recyclerView.setAdapter(recyclerAdapter);
    }
}