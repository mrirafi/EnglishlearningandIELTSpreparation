package com.meghpy.englishlearningandieltspreparation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class QuizFragment extends Fragment {

    TextView tvQuestion, tvProgress, tvFeedback;
    ProgressBar progressBar;
    Button btnOption1, btnOption2, btnOption3, btnOption4, btnNext;

    int score = 0;
    ArrayList<String> wrongQuestions = new ArrayList<>();


    // Sample questions
    String[] questions = {
            "Which one is a noun?\nনিচের কোনটি noun?",
            "Which one is a verb?\nনিচের কোনটি verb?",
            "Which one is a name?\nনিচের কোনটি name?"
    };
    String[][] options = {
            {"Book (বই)", "Go (যাও)", "He (সে)", "Quickly (দ্রুত)"},
            {"Run (দৌড়ানো)", "Beautiful (সুন্দর)", "Chair (চেয়ার)", "Slow (ধীরে)"},
            {"Run (দৌড়ানো)", "Beautiful (সুন্দর)", "Rafi (rafi)", "Slow (ধীরে)"}
    };
    int[] correctAnswers = {0, 0, 2}; // index of correct option
    int currentQuestion = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_quiz, container, false);



        tvQuestion = myView.findViewById(R.id.tvQuestion);
        tvProgress = myView.findViewById(R.id.tvProgress);
        tvFeedback = myView.findViewById(R.id.tvFeedback);
        progressBar = myView.findViewById(R.id.progressBar);
        btnOption1 = myView.findViewById(R.id.btnOption1);
        btnOption2 = myView.findViewById(R.id.btnOption2);
        btnOption3 = myView.findViewById(R.id.btnOption3);
        btnOption4 = myView.findViewById(R.id.btnOption4);
        btnNext = myView.findViewById(R.id.btnNext);


        loadQuestion();



        View.OnClickListener optionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedIndex = -1;

                if (v == btnOption1) selectedIndex = 0;
                else if (v == btnOption2) selectedIndex = 1;
                else if (v == btnOption3) selectedIndex = 2;
                else if (v == btnOption4) selectedIndex = 3;

                checkAnswer(selectedIndex);
            }
        };

        btnOption1.setOnClickListener(optionClickListener);
        btnOption2.setOnClickListener(optionClickListener);
        btnOption3.setOnClickListener(optionClickListener);
        btnOption4.setOnClickListener(optionClickListener);

        btnNext.setOnClickListener(v -> {
            currentQuestion++;
            if (currentQuestion < questions.length) {
                loadQuestion();
            } else {
                Intent intent = new Intent(getActivity(), Result.class);
                intent.putExtra("score", score);
                intent.putExtra("total", questions.length);
                intent.putStringArrayListExtra("wrongQuestions", wrongQuestions);
                startActivity(intent);

            }
        });



        return myView;
    }

    //================================================

    private void loadQuestion() {
        tvQuestion.setText(questions[currentQuestion]);
        tvProgress.setText("Question " + (currentQuestion + 1) + " of " + questions.length);
        progressBar.setProgress((currentQuestion + 1) * 100 / questions.length);
        tvFeedback.setVisibility(View.GONE);

        btnOption1.setText(options[currentQuestion][0]);
        btnOption2.setText(options[currentQuestion][1]);
        btnOption3.setText(options[currentQuestion][2]);
        btnOption4.setText(options[currentQuestion][3]);



        // Reset button states
        enableOptionButtons(true);
    }

    private void checkAnswer(int selectedIndex) {
        enableOptionButtons(false);

        if (selectedIndex == correctAnswers[currentQuestion]) {
            tvFeedback.setText("✔️ Correct Answer!\nসঠিক উত্তর!");
            tvFeedback.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            score++;
        } else {
            String correctText = options[currentQuestion][correctAnswers[currentQuestion]];
            tvFeedback.setText("❌ Wrong Answer!\nসঠিক উত্তর: " + correctText);
            tvFeedback.setTextColor(getResources().getColor(android.R.color.holo_red_dark));

            // Save this question for review
            wrongQuestions.add(questions[currentQuestion] + "\n✔️ " + correctText);
        }

        tvFeedback.setVisibility(View.VISIBLE);
    }


    private void enableOptionButtons(boolean enable) {
        btnOption1.setEnabled(enable);
        btnOption2.setEnabled(enable);
        btnOption3.setEnabled(enable);
        btnOption4.setEnabled(enable);
    }

    //================================================
}