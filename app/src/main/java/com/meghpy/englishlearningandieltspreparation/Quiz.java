package com.meghpy.englishlearningandieltspreparation;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;


public class Quiz extends AppCompatActivity {

    FrameLayout frameLayout;
    TabLayout tabLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        frameLayout = findViewById(R.id.fLayout);
        tabLay = findViewById(R.id.tabLay);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fLayout, new PracticeFragment())
                .commit();
        tabLay.getTabAt(0).select();

        tabLay.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabPosition = tab.getPosition();

                if (tabPosition == 0 ){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fLayout, new PracticeFragment())
                            .commit();
                }else if (tabPosition == 1){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fLayout, new QuizFragment())
                            .commit();
                }else if (tabPosition == 2){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fLayout, new QuizFragment())
                            .commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}