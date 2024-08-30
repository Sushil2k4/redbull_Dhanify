package com.example.dhanify;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize UI elements
        TextView questionText = findViewById(R.id.question_text);
        Button option1Button = findViewById(R.id.option1_button);
        Button option2Button = findViewById(R.id.option2_button);
        Button option3Button = findViewById(R.id.option3_button);
        Button option4Button = findViewById(R.id.option4_button);
        Button backButton = findViewById(R.id.back_button);

        // Example question
        questionText.setText("Which of the following is a key financial asset according to Rich Dad Poor Dad?");
        option1Button.setText("Savings Account");
        option2Button.setText("House");
        option3Button.setText("Stocks and Bonds");
        option4Button.setText("Retirement Plan");

        // Set click listeners
        option1Button.setOnClickListener(v -> handleAnswer("Savings Account"));
        option2Button.setOnClickListener(v -> handleAnswer("House"));
        option3Button.setOnClickListener(v -> handleAnswer("Stocks and Bonds"));
        option4Button.setOnClickListener(v -> handleAnswer("Retirement Plan"));
        backButton.setOnClickListener(v -> finish());
    }

    private void handleAnswer(String answer) {
        // Handle the answer here (e.g., check if it is correct, show a toast, etc.)
        if (answer.equals("Stocks and Bonds")) {
            // Correct answer
            Toast.makeText(this, "Correct Answer!", Toast.LENGTH_SHORT).show();
        } else {
            // Incorrect answer
            Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT).show();
        }
    }
}
