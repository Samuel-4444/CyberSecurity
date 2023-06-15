package com.example.cybersecurityapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuizPagerAdapter extends FragmentStateAdapter {
    private int NUM_PAGES = 3; //CHANGE MEEEEEEEEEEEE
    private String topic;
    private JSONObject jsonObj;

    public QuizPagerAdapter(@NonNull FragmentActivity fragmentActivity, String topic, String jsonObj) {
        super(fragmentActivity);
        this.topic = topic;
        try {
            this.jsonObj = new JSONObject(jsonObj);
        } catch (JSONException e) {
            throw new RuntimeException();
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //Extract note
        String ans1;
        String ans2;
        String ans3;
        String ans4;
        String question;
        int answer;

        try {
            // Assign questions
            JSONArray questionArray = jsonObj.getJSONArray("questions");
            question = questionArray.getString(position);

            // Assign answers
            JSONArray answerArray = jsonObj.getJSONArray("choices").getJSONArray(position);
            ans1 = answerArray.getString(0);
            ans2 = answerArray.getString(1);
            ans3 = answerArray.getString(2);
            ans4 = answerArray.getString(3);

        } catch (JSONException e) {
            question = "";
            ans1 = "";
            ans2 = "";
            ans3 = "";
            ans4 = "";
            answer = 1;
        }

        // Create bundle
        Bundle bundle = new Bundle();
        bundle.putString("question", question);
        bundle.putString("ans1", ans1);
        bundle.putString("ans2", ans2);
        bundle.putString("ans3", ans3);
        bundle.putString("ans4", ans4);
        //bundle.putInt("answer", answer);

        // Create fragment
        QuizNoteFragment quizNoteFragment = new QuizNoteFragment();
        quizNoteFragment.setArguments(bundle);

        // Return fragment
        return quizNoteFragment;
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
