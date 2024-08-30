package com.example.dhanify;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ViewGoalsActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private TextView goalsDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_goals);

        db = FirebaseFirestore.getInstance();
        goalsDisplay = findViewById(R.id.goals_display);

        // Back button listener
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());

        loadGoals();
    }

    private void loadGoals() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Toast.makeText(ViewGoalsActivity.this, "User not authenticated", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("Users").document(user.getUid()).collection("Goals")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    StringBuilder goalsText = new StringBuilder();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        Goal goal = doc.toObject(Goal.class);
                        if (goal != null) {
                            goalsText.append(goal.getGoalText()).append("\n");
                        }
                    }
                    goalsDisplay.setText(goalsText.toString());
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ViewGoalsActivity.this, "Error loading goals: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
