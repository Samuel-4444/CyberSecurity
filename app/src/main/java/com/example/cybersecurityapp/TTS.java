package com.example.cybersecurityapp;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class TTS extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private boolean initialised;
    private Context context;


    public TTS(LearningNoteFragment context){
        this.context = context;
        tts = new TextToSpeech(context, this);
    }

    public void speak(String text) {
        if (initialised){
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            Toast.makeText(context, "Text-to-speech is not initialised yet.", Toast.LENGTH_SHORT).show();
        }
    }

    public void speakPageContent(String pageContent) {
        if (initialised) {
            tts.speak(pageContent, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            Toast.makeText(context, "Text-to-speech is not initialized yet.", Toast.LENGTH_SHORT).show();
        }
    }

    public void speakPageContentFromFile(String filePath) {
        try {
            StringBuilder content = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            speak(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutdown(){
        tts.shutdown();
    }

    @Override
    public void onInit(int status){
        if (status == TextToSpeech.SUCCESS){
            int result = tts.setLanguage(Locale.getDefault());
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(context, "Language not supported.", Toast.LENGTH_SHORT).show();
            } else {
                initialised = true;
            }
        } else {
            Toast.makeText(context, "Initialisation failed.", Toast.LENGTH_SHORT).show();
        }
    }
}
