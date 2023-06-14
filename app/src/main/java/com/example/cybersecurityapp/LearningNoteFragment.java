package com.example.cybersecurityapp;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LearningNoteFragment extends Fragment {

    private TTS tts;
    private String pageContent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning_note, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Pass values
        String topic = getArguments().getString("topic");
        String note = getArguments().getString("note");

        // Edit default params
        View v = getView(); //ADD ME BACK
        TextView ln_text_topic = v.findViewById(R.id.ln_text_topic);
        TextView ln_text_note = v.findViewById(R.id.ln_text_note);
        //ImageView ln_img_image = v.findViewById(R.id.ln_img_image);

        ln_text_topic.setText(topic);
        ln_text_note.setText(note);

        //TTS
        tts = new TTS(this);

        FloatingActionButton speakButton = v.findViewById(R.id.speak_button);
        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageContent = note;
                //read from string
                tts.speakPageContent(pageContent);
            }
        });
    }
}