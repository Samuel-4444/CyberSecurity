package com.example.cybersecurityapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class TTSV2 extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttsv2);

        Button englishButton = findViewById(R.id.englishButton);


        tts = new TextToSpeech(this, this);

        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.val == 0){
                    tts.setLanguage(Locale.ENGLISH);
                } else if (MainActivity.val == 1) {
                    tts.setLanguage(Locale.FRENCH);
                }else if (MainActivity.val == 2) {
                    tts.setLanguage(new Locale("es"));
                }
                speakOut();
            }
        });




    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.ENGLISH);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                new AlertDialog.Builder(TTSV2.this).setTitle("Language Not Supported").setMessage("Language Not Supported");
            }
        } else {
            // Initialization failed
        }
    }

    private void speakOut() {
        String text = "Hello world KO";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}

