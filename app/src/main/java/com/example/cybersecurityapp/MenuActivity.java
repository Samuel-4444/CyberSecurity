package com.example.cybersecurityapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cybersecurityapp.databinding.ActivityMenuBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

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
                switch (item.getItemId()) {
                    case 0:
                        viewPager2.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager2.setCurrentItem(1);
                        break;
                    case 2:
                        viewPager2.setCurrentItem(2);
                        break;
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
    }
}