package com.example.cybersecurityapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class QuizNoteFragment extends Fragment {
    int answer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Pass values
        String question = getArguments().getString("question");
        String ans1 = getArguments().getString("ans1");
        String ans2 = getArguments().getString("ans2");
        String ans3 = getArguments().getString("ans3");
        String ans4 = getArguments().getString("ans4");
        answer = getArguments().getInt("answer");

        // Find buttons
        View v = getView();
        TextView b_question = v.findViewById(R.id.quiz_question);
        Button b_ans1 = v.findViewById(R.id.ans_1);
        Button b_ans2 = v.findViewById(R.id.ans_2);
        Button b_ans3 = v.findViewById(R.id.ans_3);
        Button b_ans4 = v.findViewById(R.id.ans_4);

        // Set text#
        b_question.setText(question);
        b_ans1.setText(ans1);
        b_ans2.setText(ans2);
        b_ans3.setText(ans3);
        b_ans4.setText(ans4);

        // Answering questions

    }
    public void ans1Button(View v){
        int id = v.getId();
        Button button = v.findViewById(id);
        if (answer == 1) {
            button.setBackgroundColor(Color.GREEN);
        }
    }
}