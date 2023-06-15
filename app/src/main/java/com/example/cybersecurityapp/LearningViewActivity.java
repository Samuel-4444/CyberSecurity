package com.example.cybersecurityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LearningViewActivity extends AppCompatActivity {
    LearningPagerAdapter learningPagerAdapter;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_view);

        Intent intent = getIntent();
        String topic = intent.getStringExtra("topic");
        String jsonObj = intent.getStringExtra("jsonObj");
        viewPager2 = findViewById(R.id.pager);
        learningPagerAdapter = new LearningPagerAdapter(this, topic, jsonObj);

        viewPager2.setAdapter(learningPagerAdapter);
    }

    public void backButton(View v){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}