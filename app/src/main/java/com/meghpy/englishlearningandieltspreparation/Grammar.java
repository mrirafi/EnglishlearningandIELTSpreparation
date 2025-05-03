package com.meghpy.englishlearningandieltspreparation;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Grammar extends AppCompatActivity {

    private TextView tvLesson, tvEnglish, tvBangla;
    private Button btnPlayAudio, btnNext;

    private ArrayList<GrammarItem> grammarList;
    private int currentIndex = 0;
    private MediaPlayer mediaPlayer; // Optional if using audio

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);

        tvLesson = findViewById(R.id.tvLesson);
        tvEnglish = findViewById(R.id.tvEnglishSentence);
        tvBangla = findViewById(R.id.tvBanglaTranslation);
        btnPlayAudio = findViewById(R.id.btnPlayAudio);
        btnNext = findViewById(R.id.btnNextGrammar);

        grammarList = new ArrayList<>();
        loadData();

        displayItem(currentIndex);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex++;
                if (currentIndex >= grammarList.size()) currentIndex = 0; // loop
                displayItem(currentIndex);
            }
        });

        btnPlayAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Optional: play an audio file corresponding to current sentence
                // For demo, this assumes you have matching audio files in res/raw
                int audioResId = getResources().getIdentifier("audio_" + currentIndex, "raw", getPackageName());
                if (mediaPlayer != null) mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(Grammar.this, audioResId);
                mediaPlayer.start();
            }
        });
    }

    private void loadData() {
        grammarList.add(new GrammarItem("Lesson: Subject + Verb", "I am a student.", "আমি একজন ছাত্র।"));
        grammarList.add(new GrammarItem("Lesson: Subject + Verb", "She is a teacher.", "সে একজন শিক্ষক।"));
        grammarList.add(new GrammarItem("Lesson: Subject + Verb", "They are friends.", "তারা বন্ধু।"));
        // Add more items here
    }

    private void displayItem(int index) {
        GrammarItem item = grammarList.get(index);
        tvLesson.setText(item.getLessonTitle());
        tvEnglish.setText(item.getEnglishSentence());
        tvBangla.setText(item.getBanglaTranslation());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}