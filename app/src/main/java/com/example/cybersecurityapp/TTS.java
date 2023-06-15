package com.example.cybersecurityapp;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class TTS extends AppCompatActivity {
    private static final String TAG = "TextViewToSpeech";
    private Context context;
    private TextToSpeech tts;

    public TTS(Context context) {
        this.context = context;
        tts = new TextToSpeech( context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Locale locale = Locale.ENGLISH;
                    if (tts.isLanguageAvailable(locale) == TextToSpeech.LANG_AVAILABLE) {
                        tts.setLanguage(locale);
                    } else {
                        Log.e(TAG, "Unsupported language: " + locale.getLanguage());
                    }
                } else {
                    Log.e(TAG, "Failed to initialize TextToSpeech");
                }
            }
        });
    }

    public void speakText(TextView textView) {
        String text = textView.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void release() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}
