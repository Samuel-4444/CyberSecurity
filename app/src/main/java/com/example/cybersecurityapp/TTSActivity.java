package com.example.cybersecurityapp;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TTSActivity extends AppCompatActivity{

        private TTS tts;
        //private String filePath = "/filepath.txt";
        //private String pageContent = "highlighted text";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tts);

            tts = new TTS(this);

            FloatingActionButton speakButton = findViewById(R.id.speak_button);
            speakButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //read from file
                   //tts.speakPageContentFromFile(filePath);
                    //read from string
                   //tts.speakPageContent(pageContent);
                }
            });
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            tts.shutdown();
        }
}
