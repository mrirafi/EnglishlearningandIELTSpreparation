package com.meghpy.englishlearningandieltspreparation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    Button btnVocabulary, btnGrammar, btnIELTS;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_home, container, false);

        btnVocabulary = myView.findViewById(R.id.btnVocabulary);
        btnGrammar = myView.findViewById(R.id.btnGrammar);
        btnIELTS = myView.findViewById(R.id.btnIELTS);

        btnIELTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), Quiz.class));

            }
        });

        btnGrammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),Grammar.class));
            }
        });

        return myView;
    }
}