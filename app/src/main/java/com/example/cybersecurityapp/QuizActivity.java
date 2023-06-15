package com.example.cybersecurityapp;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.graphics.Color;
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

public class QuizActivity extends AppCompatActivity  implements View.OnClickListener{

    TextView totalQuestionsTV, questionTV;

    Button answer1, answer2, answer3, answer4, submit;
    int tally=0, totalQuestions = 4, questionIndex = 0;             //define variables
    String selectAnswer = "", correctChoice;


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

//    void readFromJSON(){                            //method to read questions and answers from json
//        Resources r = getResources();
//        InputStream is = r.openRawResource(R.raw.quiz);
//        Scanner scanner = new Scanner(is);
//        String jString = scanner.useDelimiter("\\A").next();
//        scanner.close();
//        try {
//            JSONArray jArray = new JSONArray(jString);
//            for (int i = 0; i < jArray.length(); i++) {
//                JSONObject jObject = jArray.getJSONObject(i);
//                String quiz = jObject.getString("quiz_name");
//                String question = jObject.getString("questions");
//                // jObject -> String
//                String jsonObjString = jObject.toString();
//
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        //InputStream
//    }

//    MainActivity.val will stores an int to decide which language file will be used
//    void readFromJSON(){
//
//        //method to read questions and answers from json
//        Resources r = getResources();
//        InputStream is = r.openRawResource(R.raw.quiz);
//        if (MainActivity.val == 1){
//             is = r.openRawResource(R.raw.quizfr);
//        } else if (MainActivity.val==2) {
//            is = r.openRawResource(R.raw.quizes);
//        }
//        Scanner scanner = new Scanner(is);
//        String jString = scanner.useDelimiter("\\A").next();
//        scanner.close();
//        try {
//            JSONArray jArray = new JSONArray(jString);
//            for (int i = 0; i < jArray.length(); i++) {
//                JSONObject jObject = jArray.getJSONObject(i);
//                String quiz = jObject.getString("quiz_name");
//                String question = jObject.getString("questions");
//                // jObject -> String
//                String jsonObjString = jObject.toString();
//
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        //InputStream
//    }

    public void quizMenu(){
        if (questionIndex ==  totalQuestions){
            finishQuiz();
            return;
        }

        Resources r = getResources();
        InputStream is = r.openRawResource(R.raw.quiz);
        if (MainActivity.val == 1){
            is = r.openRawResource(R.raw.quizfr);
        } else if (MainActivity.val==2) {
            is = r.openRawResource(R.raw.quizes);
        }
        Scanner scan = new Scanner(is);
        String jString = scan.useDelimiter("\\A").next();
        scan.close();
        try{
            questionTV = findViewById(R.id.question);
            JSONArray jArray = new JSONArray(jString);
            for (int i=0; i < jArray.length(); i++){
                JSONObject jObject = jArray.getJSONObject(i);
                String quiz = jObject.getString("quiz_name");
                String question = (String) jObject.get("questions");
                questionTV.setText(question);
                String correctChoice = jObject.getString("choices");
                Scanner json = new Scanner(jObject.get("choices").toString());
                json.useDelimiter(",");
                String[] jsonArray = new String[4];
                for (int j=0; j < 4; j++){
                    jsonArray[j] = json.next();
                }
                String answer1text = jsonArray[0];
                String answer2text = jsonArray[1];
                String answer3text = jsonArray[2];
                String answer4text = jsonArray[3];

                answer1.setText(answer1text);
                answer2.setText(answer2text);
                answer3.setText(answer3text);
                answer4.setText(answer4text);


            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }

    }

    public void finishQuiz(){
        String passOrFail;
        if (tally > totalQuestions*0.5){
            passOrFail = "You have passed.";
        }
        else{
            passOrFail = "You have failed.";
        }

        new AlertDialog.Builder(this)
                .setTitle(passOrFail)
                .setMessage("Your score is " + tally + " out of " + totalQuestions);


    }

    @Override
    public void onClick(View v) {
        


        Button clickedButton = (Button) v;
        if (clickedButton.getId()==R.id.submit_ans){
            if (selectAnswer.equals(correctChoice)){
                tally++;
            }
            questionIndex++;
            quizMenu();


        }
        else{
            selectAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }
}
