package com.meghpy.englishlearningandieltspreparation;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PracticeFragment extends Fragment {

    private TextView tvLesson, tvEnglish, tvBangla;
    private Button btnPlayAudio, btnNext;

    private ArrayList<GrammarItem> grammarList;
    private int currentIndex = 0;
    private MediaPlayer mediaPlayer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_practice, container, false);

        tvLesson = myView.findViewById(R.id.tvLesson);
        tvEnglish = myView.findViewById(R.id.tvEnglishSentence);
        tvBangla = myView.findViewById(R.id.tvBanglaTranslation);
        btnPlayAudio = myView.findViewById(R.id.btnPlayAudio);
        btnNext = myView.findViewById(R.id.btnNextGrammar);

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
                int audioResId = getResources().getIdentifier("audio_" + currentIndex, "raw", getActivity().getPackageName());
                if (mediaPlayer != null) mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(getActivity(), audioResId);
                mediaPlayer.start();
            }
        });


        return myView;
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
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}