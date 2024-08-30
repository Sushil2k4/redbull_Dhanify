package com.example.dhanify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Take Quiz button
        Button quizButton = findViewById(R.id.quiz_button);
        quizButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });

        // Go to Forum button (remove or replace this section if ForumActivity is not available)
        Button forumButton = findViewById(R.id.forum_button);
        forumButton.setOnClickListener(v -> {
            // If ForumActivity is not available, you can replace it with another activity
            // or comment out this section
            // Intent intent = new Intent(MainActivity.this, ForumActivity.class);
            // startActivity(intent);
        });

        // Run a Simulation button (remove or replace this section if SimulationActivity is not available)
        Button simulationButton = findViewById(R.id.simulation_button);
        simulationButton.setOnClickListener(v -> {
            // If SimulationActivity is not available, you can replace it with another activity
            // or comment out this section
            // Intent intent = new Intent(MainActivity.this, SimulationActivity.class);
            // startActivity(intent);
        });

        // My Goals button - navigate to GoalsActivity
        Button goalsButton = findViewById(R.id.goals_button);
        goalsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GoalsActivity.class);
            startActivity(intent);
        });

        // My Courses button - navigate to CoursesActivity
        Button coursesButton = findViewById(R.id.courses_button);
        coursesButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CoursesActivity.class);
            startActivity(intent);
        });
    }
}
