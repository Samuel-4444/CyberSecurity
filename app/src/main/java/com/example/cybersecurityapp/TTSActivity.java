package com.example.cybersecurityapp;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class TTSActivity extends AppCompatActivity{

        private TTS tts;
        //private String filePath = "/filepath.txt";
        //private String highlightedText = "highlighted text";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tts);

            tts = new TTS(this);

            ImageButton speakButton = findViewById(R.id.speak_button);
            speakButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // tts.speakHighlightedPageContentFromFile(filePath, highlightedText);
                }
            });
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            tts.shutdown();
        }
}
