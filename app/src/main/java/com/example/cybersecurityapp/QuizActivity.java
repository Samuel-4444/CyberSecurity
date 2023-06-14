package com.example.cybersecurityapp;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTV, questionTV;
    Button answer1, answer2, answer3, answer4, submit;
    int tally=0, totalQuestions = 0, questionIndex = 0;             //define variables
    String selectAnswer = "";


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        totalQuestionsTV = findViewById(R.id.question_total);
        questionTV = findViewById(R.id.question);
        answer1 = findViewById(R.id.ans1);                      //link variables to views
        answer2 = findViewById(R.id.ans2);
        answer3 = findViewById(R.id.ans3);
        answer4 = findViewById(R.id.ans4);
        submit = findViewById(R.id.submit_ans);

        answer1.setOnClickListener(this);                   //give variables onclicklisteners
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);
        submit.setOnClickListener(this);

        totalQuestionsTV.setText("Questions: " + totalQuestions);

    }

    void readFromJSON(){                            //method to read questions and answers from json
        Resources r = getResources();
        InputStream is = r.openRawResource(R.raw.quiz);
        Scanner scanner = new Scanner(is);
        String jString = scanner.useDelimiter("\\A").next();
        scanner.close();
        try {
            JSONArray jArray = new JSONArray(jString);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObject = jArray.getJSONObject(i);
                String quiz = jObject.getString("quiz_name");
                String question = jObject.getString("questions");
                // jObject -> String
                String jsonObjString = jObject.toString();


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //InputStream
    }

    public void quizMenu(){

        questionTV = findViewById(R.id.question);
        questionTV.setText
    }

    @Override
    public void onClick(View v) {

    }
}
