package com.meghpy.englishlearningandieltspreparation;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class Grammar extends AppCompatActivity {

   RecyclerView recyclerView;
   HashMap<String,String> hashMap;
   ArrayList<HashMap<String,String>> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);

        recyclerView = findViewById(R.id.recyclerView);


        arrayList = new ArrayList<>();

        hashMap = new HashMap<>();
        hashMap.put("topic","Noun");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("topic","Pronoun");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("topic","Adjective");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("topic","Verb");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("topic","Adverb");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("topic","Preposition");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("topic","Conjunction");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("topic","Interjection");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();

        xAdapter adapter = new xAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Grammar.this));


    }

//======================
    private class xAdapter extends RecyclerView.Adapter{

        private class grammarViewHolder extends RecyclerView.ViewHolder{

            TextView tvGrammarTopic;

            public grammarViewHolder(@NonNull View itemView) {
                super(itemView);

                tvGrammarTopic = itemView.findViewById(R.id.tvGrammarTopic);
            }
        }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView cardGrammar;

        LayoutInflater inflater = getLayoutInflater();
        View myView = inflater.inflate(R.layout.grammar_item,parent,false);

        cardGrammar = myView.findViewById(R.id.cardGrammar);

        cardGrammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Grammar.this,Quiz.class));

            }
        });



        return new grammarViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            grammarViewHolder grammarHolder = (grammarViewHolder) holder;

            hashMap = arrayList.get(position);
            String topic = hashMap.get("topic");
            grammarHolder.tvGrammarTopic.setText(topic);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



}


//======================
}