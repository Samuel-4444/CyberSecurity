package com.example.cybersecurityapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTV, questionTV;
    Button answer1, answer2, answer3, answer4, submit;
    int tally=0, totalQuestions = 0, questionIndex = 0;
    String selectAnswer = "";


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        totalQuestionsTV = findViewById(R.id.question_total);
        questionTV = findViewById(R.id.question);
        answer1 = findViewById(R.id.ans1);
        answer2 = findViewById(R.id.ans2);
        answer3 = findViewById(R.id.ans3);
        answer4 = findViewById(R.id.ans4);
        submit = findViewById(R.id.submit_ans);

        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);
        submit.setOnClickListener(this);

        totalQuestionsTV.setText("Questions: " + totalQuestions);

    }

    void readFromFile(){
        try{
            FileReader quizFile = new FileReader ("quiz_questions.txt");
            quizFile.close();
        }
        catch(FileNotFoundException e){
            Log.e("read activity", "File not found: " + e.toString());
        }
        catch(IOException e){
            Log.e("read activity", "File not found: " + e.toString());
        }


    }

    @Override
    public void onClick(View v) {

    }
}
