package com.meghpy.englishlearningandieltspreparation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Result extends AppCompatActivity {

    TextView tvScore, tvFeedback, tvMistakesLabel;
    ProgressBar progressScore;
    LinearLayout layoutMistakes;
    Button btnRetry, btnNext;

    int score = 0, total = 0;
    ArrayList<String> wrongAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize Views
        tvScore = findViewById(R.id.tvScore);
        tvFeedback = findViewById(R.id.tvFeedback);
        progressScore = findViewById(R.id.progressScore);
        tvMistakesLabel = findViewById(R.id.tvMistakesLabel);
        layoutMistakes = findViewById(R.id.layoutMistakes);
        btnRetry = findViewById(R.id.btnRetry);
        btnNext = findViewById(R.id.btnNext);

        // Get data from Intent
        score = getIntent().getIntExtra("score", 0);
        total = getIntent().getIntExtra("total", 10);
        wrongAnswers = getIntent().getStringArrayListExtra("wrongQuestions");

        // Display Score
        tvScore.setText(score + " / " + total);

        // Show Progress %
        int percentage = (int) (((float) score / total) * 100);
        progressScore.setProgress(percentage);

        // Show feedback
        if (percentage >= 80) {
            tvFeedback.setText("দারুণ করেছে তুমি! Keep it up! 🏆");
        } else if (percentage >= 50) {
            tvFeedback.setText("ভালো করছো, আরও চেষ্টা করো! 👍");
        } else {
            tvFeedback.setText("চেষ্টা চালিয়ে যাও, তুমি পারবে! 💪");
        }

        // Show mistakes if any
        if (wrongAnswers != null && !wrongAnswers.isEmpty()) {
            tvMistakesLabel.setVisibility(View.VISIBLE);
            layoutMistakes.setVisibility(View.VISIBLE);

            for (String mistake : wrongAnswers) {
                TextView textView = new TextView(this);
                textView.setText("❌ " + mistake);
                textView.setTextColor(Color.RED);
                textView.setTextSize(16);
                layoutMistakes.addView(textView);
            }
        }

        // Button: Retry
        btnRetry.setOnClickListener(v -> {
            Intent retryIntent = new Intent(Result.this, Quiz.class);
            retryIntent.putExtra("topic", getIntent().getStringExtra("topic")); // optional
            startActivity(retryIntent);
            finish();
        });

        // Button: Next
        btnNext.setOnClickListener(v -> {
            Intent nextIntent = new Intent(Result.this, Grammar.class);
            startActivity(nextIntent);
            finish();
        });
    }
}