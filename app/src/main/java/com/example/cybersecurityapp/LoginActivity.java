package com.example.cybersecurityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cybersecurityapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void BackButtonPressed(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void LoginButtonPressed(View view) {
    }

    public void RegisterButtonPressed(View view) {
    }

    public void GuestButtonPressed(View view) {
    }}