package com.example.dhanify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Take Quiz button
        Button quizButton = findViewById(R.id.quiz_button);
        if (quizButton != null) {
            quizButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            });
        } else {
            Log.e(TAG, "Quiz button not found");
        }

        // Initialize Go to Forum button (if implemented)
        Button forumButton = findViewById(R.id.forum_button);
        if (forumButton != null) {
            forumButton.setOnClickListener(v -> {
                Log.w(TAG, "ForumActivity not implemented");
            });
        } else {
            Log.e(TAG, "Forum button not found");
        }

        // Initialize Run a Simulation button (if implemented)
        Button simulationButton = findViewById(R.id.simulation_button);
        if (simulationButton != null) {
            simulationButton.setOnClickListener(v -> {
                Log.w(TAG, "SimulationActivity not implemented");
            });
        } else {
            Log.e(TAG, "Simulation button not found");
        }

        // Initialize My Goals button
        Button goalsButton = findViewById(R.id.goals_button);
        if (goalsButton != null) {
            goalsButton.setOnClickListener(v -> {
                try {
                    Intent intent = new Intent(MainActivity.this, GoalsActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e(TAG, "Failed to start GoalsActivity", e);
                }
            });
        } else {
            Log.e(TAG, "Goals button not found");
        }

        // Initialize My Courses button
        Button coursesButton = findViewById(R.id.courses_button);
        if (coursesButton != null) {
            coursesButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, CoursesActivity.class);
                startActivity(intent);
            });
        } else {
            Log.e(TAG, "Courses button not found");
        }
    }
}
