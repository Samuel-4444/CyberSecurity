package com.example.cybersecurityapp;

import android.annotation.SuppressLint;
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
import androidx.viewpager2.widget.ViewPager2;

import com.example.cybersecurityapp.databinding.ActivityMenuBinding;

import java.util.Locale;

public class MenuActivity extends AppCompatActivity {
    private ActivityMenuBinding binding;

    ImageButton darkMode;
    ImageView langImage;

    boolean isNight;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ViewPager2 viewPager2 = findViewById(R.id.menu_pager);
        MenuPagerAdapter menuPagerAdapter = new MenuPagerAdapter(this);
        viewPager2.setAdapter(menuPagerAdapter);












//        binding = ActivityMenuBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_menu);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);

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
        AlertDialog.Builder mBuilder =  new AlertDialog.Builder(MenuActivity.this);
        mBuilder.setTitle("");
        mBuilder.setSingleChoiceItems(langs, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                if(i==0){
                    setLocale("en");
                    MainActivity.val = 0;
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                } else if (i==1)
                {
                    setLocale("fr");
                    MainActivity.val = 1;
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                } else if (i==2)
                {
                    setLocale("es");
                    MainActivity.val  = 2;
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