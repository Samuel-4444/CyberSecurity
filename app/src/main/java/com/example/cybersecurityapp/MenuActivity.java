package com.example.cybersecurityapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cybersecurityapp.databinding.ActivityMenuBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Locale;

public class MenuActivity extends AppCompatActivity {
    private ActivityMenuBinding binding;

    boolean isNight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // ViewPager2 Init
        ViewPager2 viewPager2 = findViewById(R.id.menu_pager);
        MenuPagerAdapter menuPagerAdapter = new MenuPagerAdapter(this);
        viewPager2.setAdapter(menuPagerAdapter);

        // binding = ActivityMenuBinding.inflate(getLayoutInflater());
        // setContentView(binding.getRoot());
        //
        // BottomNavigationView navView = findViewById(R.id.nav_view);
        // // Passing each menu ID as a set of Ids because each
        // // menu should be considered as top level destinations.
        // AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        // R.id.navigation_home, R.id.navigation_dashboard,
        // R.id.navigation_notifications)
        // .build();
        // NavController navController = Navigation.findNavController(this,
        // R.id.nav_host_fragment_activity_menu);
        // NavigationUI.setupActionBarWithNavController(this, navController,
        // appBarConfiguration);
        // NavigationUI.setupWithNavController(binding.navView, navController);
        // NavBar Init
        BottomNavigationView bNavView = findViewById(R.id.menu_nav_view);
        bNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    viewPager2.setCurrentItem(0);
                } else if (itemId == R.id.nav_accessibility) {
                    viewPager2.setCurrentItem(1);
                }
                return true;
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bNavView.getMenu().getItem(position).setChecked(true);

            }
        });

        //
        // darkMode = findViewById(R.id.dark);
        // if (AppCompatDelegate.getDefaultNightMode() ==
        // AppCompatDelegate.MODE_NIGHT_NO) {
        // isNight = false;
        // } else {
        // isNight = true;
        // }
        //
        // darkMode.setOnClickListener(v -> {
        // if (isNight) {
        // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // isNight = false;
        // } else {
        // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        // isNight = true;
        //
        // }
        // });
    }

    public void darkMethod(MenuItem item) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            isNight = false;
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            isNight = true;

        }
    }

    public void showDialog(MenuItem item) {

        final String[] langs = { "English", "Francés/Francesa", "Español" };
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MenuActivity.this);
        mBuilder.setTitle("");
        mBuilder.setSingleChoiceItems(langs, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                if (i == 0) {
                    setLocale("en");
                    MainActivity.val = 0;
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                } else if (i == 1) {
                    setLocale("fr");
                    MainActivity.val = 1;
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                } else if (i == 2) {
                    setLocale("es");
                    MainActivity.val = 2;
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

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = this.getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Language", lang);
        editor.apply();
    }

    public void showDialog() {
    }
}