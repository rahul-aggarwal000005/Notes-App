package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements INotesRVAdapter {

    private NotesViewModel viewModel;
    TextView inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NotesAdapter notesAdapter = new NotesAdapter(this,this);
        recyclerView.setAdapter(notesAdapter);


        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NotesViewModel.class);

        viewModel.getAllNotes().observe(this, notesAdapter::updateNotes);

    }

    @Override
    public void onItemClick(Note note) {
        viewModel.delete(note);
        Toast.makeText(this, note.getText() + " is Deleted", Toast.LENGTH_SHORT).show();
    }

    public void submitData(View view) {
        inputText = findViewById(R.id.inputText);
        String text = inputText.getText().toString();
        if(!text.isEmpty()){
            viewModel.insert(new Note(text));
            Toast.makeText(this, text + " is Inserted", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Enter the Text", Toast.LENGTH_SHORT).show();
        }
    }
}