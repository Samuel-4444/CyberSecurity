package com.example.cybersecurityapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    AccountDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new AccountDatabaseHandler(this,"UserAccounts.db",null,1);
    }

    private void clearText(){
        TextView username = (TextView) findViewById(R.id.textUserName);
        TextView password = (TextView) findViewById(R.id.textPassword);
        TextView confirm = (TextView) findViewById(R.id.textConfirmPassword);
        username.setText("");
        password.setText("");
        confirm.setText("");
    }

    public void BackButtonPressed(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void HelpButtonPressed(View v){
        HelpDialog helpDialog = new HelpDialog();
        helpDialog.show(getSupportFragmentManager(), "Help");
    }

    public void RegisterButtonPressed(View v) {
        TextView username = (TextView) findViewById(R.id.textUserName);
        TextView password = (TextView) findViewById(R.id.textPassword);
        TextView confirm = (TextView) findViewById(R.id.textConfirmPassword);

        String email = username.getText().toString();
        String passText = password.getText().toString();
        String confirmText = confirm.getText().toString();


        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                username.setTextColor(Color.parseColor("#000000"));
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password.setTextColor(Color.parseColor("#000000"));
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        confirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                confirm.setTextColor(Color.parseColor("#000000"));
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        boolean validCriteria = true;

        if (email.length() <= 4) {
            validCriteria = false;
            username.setTextColor(Color.parseColor("#FF0000"));
        }

        if (passText.length() <= 4) {
            validCriteria = false;
            password.setTextColor(Color.parseColor("#FF0000"));
        }

        if (!passText.equals(confirmText)) {
            validCriteria = false;
            confirm.setTextColor(Color.parseColor("#FF0000"));
        }

        if (email.equals(passText)){
            validCriteria = false;
            username.setTextColor(Color.parseColor("#FF0000"));
            password.setTextColor(Color.parseColor("#FF0000"));
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            validCriteria = false;
        }

        if (validCriteria){
            Boolean checkAccountExists = db.checkEmail(email);
            if (checkAccountExists){
                Toast.makeText(RegisterActivity.this,"Email already registered",Toast.LENGTH_SHORT).show();
                clearText();
            }
            else{
                Boolean attemptInsert = db.insertAccount(email,passText);
                if (attemptInsert){
                    Toast.makeText(RegisterActivity.this,"Account registered successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Registered failed",Toast.LENGTH_SHORT).show();
                    clearText();
                }
            }
        }
        else{
            Toast.makeText(RegisterActivity.this, "Please ensure valid details",Toast.LENGTH_SHORT).show();
        }
    }
}

