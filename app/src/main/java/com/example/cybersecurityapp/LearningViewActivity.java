package com.example.cybersecurityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;

public class LearningViewActivity extends AppCompatActivity {
    LearningPagerAdapter learningPagerAdapter;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_view);
        viewPager2 = findViewById(R.id.pager);
        learningPagerAdapter = new LearningPagerAdapter(this);

        viewPager2.setAdapter(learningPagerAdapter);
    }
}