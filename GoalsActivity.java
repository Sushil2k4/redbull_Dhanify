package com.example.dhanify;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GoalsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        EditText notesInput = findViewById(R.id.notes_input);
        Button saveButton = findViewById(R.id.save_button);
        Button backButton = findViewById(R.id.back_button);

        saveButton.setOnClickListener(v -> {
            String notes = notesInput.getText().toString().trim();
            if (!notes.isEmpty()) {
                // Save the notes (you can implement saving logic here)
                Toast.makeText(GoalsActivity.this, "Goals saved!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(GoalsActivity.this, "Please enter your goals", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(v -> finish());
    }
}

