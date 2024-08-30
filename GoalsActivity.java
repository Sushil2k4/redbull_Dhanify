package com.example.dhanify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class GoalsActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private TextView goalsDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        db = FirebaseFirestore.getInstance();
        goalsDisplay = findViewById(R.id.goals_display);

        EditText notesInput = findViewById(R.id.notes_input);
        Button saveButton = findViewById(R.id.save_button);
        Button backButton = findViewById(R.id.back_button);
        Button viewGoalsButton = findViewById(R.id.view_goals_button);

        saveButton.setOnClickListener(v -> {
            String notes = notesInput.getText().toString().trim();
            if (!notes.isEmpty()) {
                saveGoalsToFirestore(notes);
            } else {
                Toast.makeText(GoalsActivity.this, "Please enter your goals", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(v -> finish());

        viewGoalsButton.setOnClickListener(v -> {
            Intent intent = new Intent(GoalsActivity.this, ViewGoalsActivity.class);
            startActivity(intent);
        });

        loadGoals();
    }

    private void saveGoalsToFirestore(String goals) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Toast.makeText(GoalsActivity.this, "User not authenticated", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("Users").document(user.getUid()).collection("Goals")
                .add(new Goal(goals))
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(GoalsActivity.this, "Goal saved!", Toast.LENGTH_SHORT).show();
                    loadGoals(); // Refresh goals display
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(GoalsActivity.this, "Error saving goal: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void loadGoals() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Toast.makeText(GoalsActivity.this, "User not authenticated", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("Users").document(user.getUid()).collection("Goals")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    StringBuilder goalsText = new StringBuilder();
                    for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                        Goal goal = doc.toObject(Goal.class);
                        if (goal != null) {
                            goalsText.append(goal.getGoalText()).append("\n");
                        }
                    }
                    goalsDisplay.setText(goalsText.toString());
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(GoalsActivity.this, "Error loading goals: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    // Model class for a Goal
    public static class Goal {
        private String goalText;

        public Goal() { } // Firestore requires a no-argument constructor

        public Goal(String goalText) {
            this.goalText = goalText;
        }

        public String getGoalText() {
            return goalText;
        }

        public void setGoalText(String goalText) {
            this.goalText = goalText;
        }
    }
}
