package com.example.cybersecurityapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    boolean isNight;
    ImageView english;
    ImageView french;
    ImageView spanish;
    public static Integer val = 0 ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_account_menu);



        english = findViewById(R.id.flag1);

        english.setOnClickListener(v -> {
            setLocale("en");
            val = 0;
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        });

        french = findViewById(R.id.flag2);

        french.setOnClickListener(v -> {
            setLocale("fr");
            val = 1;
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        });

        spanish = findViewById(R.id.flag3);
        spanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setLocale("es");
                val = 2;
                Intent intent = getIntent();
                finish();
                startActivity(intent);
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



    public void LoginButtonPressed(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void RegisterButtonPressed(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void GuestButtonPressed(View v){
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
