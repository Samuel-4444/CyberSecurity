package com.example.cybersecurityapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Locale;

public class Accessibility extends AppCompatActivity {


    ImageButton darkMode;
    ImageView english;
    ImageView french;
    ImageView spanish;

    boolean isNight;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accessibility_setting);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle(getResources().getString(R.string.app_name));


        loadLocale();

//Dark Mode
        darkMode = findViewById(R.id.dark);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            isNight = false;
        } else {
            isNight = true;
        }

        darkMode.setOnClickListener(v -> {
            if (isNight) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                isNight = false;
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                isNight = true;

            }
        });

//


        english = findViewById(R.id.flag1);
        english.setOnClickListener(v -> {
                setLocale("en");
        });

        french = findViewById(R.id.flag2);

        french.setOnClickListener(v -> {
                setLocale("fr");
        });

        spanish = findViewById(R.id.flag3);
        spanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("fr");
            }
        });




//        Languages

    }

    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());


        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Language",lang);
        editor.apply();
    };

    private void loadLocale() {
        SharedPreferences pref = getSharedPreferences ("Settings", Activity.MODE_PRIVATE);
        String language = pref.getString("My_Language", "");
        setLocale(language);

    }

}
