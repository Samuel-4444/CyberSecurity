package com.example.cybersecurityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class LearningMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_menu);
        jsonParser();

    }

    private void jsonParser() {
        Resources r = getResources();
        InputStream is = r.openRawResource(R.raw.eng_notes);
        Scanner scanner = new Scanner(is);
        String jString = scanner.useDelimiter("\\A").next();
        scanner.close();
        try {
            JSONArray jArray = new JSONArray(jString);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObject = jArray.getJSONObject(i);
                String topic = jObject.getString("topic_name");
                // jObject -> String
                String jsonObjString = jObject.toString();

                // Create button
                createMenu(topic, jsonObjString);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void createMenu(String topic, String jsonObj) {
        LinearLayout linearLayout = findViewById(R.id.learningMenuLL);

        // Create button
        Button button = new Button(this);
        button.setText(topic);
        // Edit params
        //button.setBackground();

        // onClick
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick(v, topic, jsonObj);
            }
        });

        // Add
        linearLayout.addView(button);
    }

    public void buttonClick(View v, String topic, String jsonObj){
        Intent intent = new Intent(this, LearningViewActivity.class);
        intent.putExtra("topic", topic);
        intent.putExtra("jsonObj", jsonObj);
        startActivity(intent);
    }
}