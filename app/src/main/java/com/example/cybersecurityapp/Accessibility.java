package com.example.cybersecurityapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Locale;

public class Accessibility extends AppCompatActivity {


    ImageButton darkMode;
    ImageView langImage;

    boolean isNight;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accessibility_setting);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle(getResources().getString(R.string.app_name));




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
        langImage = findViewById(R.id.lang);

        langImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });





    }

    private void showDialog() {

        final String[] langs =  {"English","francés/francesa","español"};
        AlertDialog.Builder mBuilder =  new AlertDialog.Builder(Accessibility.this);
        mBuilder.setTitle("");
        mBuilder.setSingleChoiceItems(langs, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                if(i==0){
                    setLocale("en");
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                } else if (i==1)
                {
                    setLocale("fr");
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                } else if (i==2)
                {
                    setLocale("es");
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
                dialog.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
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
