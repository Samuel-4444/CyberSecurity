package com.example.cybersecurityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cybersecurityapp.R;

public class LoginActivity extends AppCompatActivity {

    AccountDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new AccountDatabaseHandler(this,"UserAccounts.db",null,1);
    }

    public void BackButtonPressed(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void LoginButtonPressed(View v) {

        TextView username = (TextView) findViewById(R.id.textUserName);
        TextView password = (TextView) findViewById(R.id.textPassword);
        String email = username.getText().toString();
        String passText = password.getText().toString();

        // Check valid email

        // Check password

        boolean validCriteria = true;

        if (passText.length() <= 4 || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            validCriteria = false;
        }

        if (validCriteria){
            if (db.checkEmail(email)){
                if (db.checkAccountDetails(email,passText)){
                    Toast.makeText(LoginActivity.this,"Successful Login",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Unsuccessful Login",Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(LoginActivity.this,"Email not registered",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(LoginActivity.this, "Please ensure valid details",Toast.LENGTH_SHORT).show();
        }



    }

}

