package com.example.cybersecurityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

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
                String name = jObject.getString("topic_name");
                createMenu(name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void createMenu(String text) {
        LinearLayout linearLayout = findViewById(R.id.learningMenuLL);
        TextView textView = new TextView(this);
        textView.setText(text);
        linearLayout.addView(textView);
    }
}