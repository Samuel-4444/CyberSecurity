package com.example.cybersecurityapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LearningPagerAdapter extends FragmentStateAdapter {
    private static final int NUM_PAGES = 2; //CHANGE ME LATER
    private String topic;
    private JSONObject jsonObj;

    public LearningPagerAdapter(@NonNull FragmentActivity fragmentActivity, String topic, String jsonObj) {
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
        // Extract note
        JSONArray subPages = null;
        String note;
        String image;
        try {
            subPages = jsonObj.getJSONArray("sub_pages");
            JSONObject page = subPages.getJSONObject(position);
            note = page.getString("text");
            image = page.getString("image");
        } catch (JSONException e) {
            note = "";
            image = "";
        }


        // Create bundle
        Bundle bundle = new Bundle();
        bundle.putString("topic", topic);
        bundle.putString("note", note);

        // Create fragment
        LearningNoteFragment learningNoteFragment = new LearningNoteFragment();
        learningNoteFragment.setArguments(bundle);


        // Return fragment
        return learningNoteFragment;
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }

}
