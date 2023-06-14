package com.example.cybersecurityapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_menu);
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
    }

    public void TestButtonPressed(View v){
        Intent intent = new Intent(this, LearningMenuActivity.class);
        startActivity(intent);
    }
}
