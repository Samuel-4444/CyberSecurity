package com.example.cybersecurityapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class QuizMenuFragment extends Fragment {
    InputStream is;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_menu, container, false);
    }

    private void jsonParser() {
        Resources r = getResources();
        is = r.openRawResource(R.raw.quiz);
        if (MainActivity.val == 1) {
            is = r.openRawResource(R.raw.quiz);
        } else if (MainActivity.val == 2) {
            is = r.openRawResource(R.raw.quiz);
        }
        Scanner scanner = new Scanner(is);
        String jString = scanner.useDelimiter("\\A").next();
        scanner.close();
        try {
            JSONArray jArray = new JSONArray(jString);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObject = jArray.getJSONObject(i);
                String topic = jObject.getString("quiz_name");
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
        View v = getView();
        LinearLayout linearLayout = v.findViewById(R.id.quizMenuLL);

        // Create button
        Button button = new Button(getActivity());


        // Edit params
        //button.setBackground();
        button.setText(topic);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                480,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        params.setMargins(30, 10, 30, 10);
        button.setLayoutParams(params);

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
        Intent intent = new Intent(getActivity(), QuizViewActivity.class);
        intent.putExtra("topic", topic);
        intent.putExtra("jsonObj", jsonObj);
        startActivity(intent);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jsonParser();
    }
}