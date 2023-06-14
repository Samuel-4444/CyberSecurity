package com.example.cybersecurityapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cybersecurityapp.databinding.ActivityMenuBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MenuActivity extends AppCompatActivity {
    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // ViewPager2 Init
        ViewPager2 viewPager2 = findViewById(R.id.menu_pager);
        MenuPagerAdapter menuPagerAdapter = new MenuPagerAdapter(this);
        viewPager2.setAdapter(menuPagerAdapter);

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
//        darkMode = findViewById(R.id.dark);
//        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
//            isNight = false;
//        } else {
//            isNight = true;
//        }
//
//        darkMode.setOnClickListener(v -> {
//            if (isNight) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                isNight = false;
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                isNight = true;
//
//            }
//        });
    }
}