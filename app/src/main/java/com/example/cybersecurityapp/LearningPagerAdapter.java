package com.example.cybersecurityapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LearningPagerAdapter extends FragmentStateAdapter {
    private static final int NUM_PAGES = 2;

    public LearningPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new LearningNoteFragment();
            case 1:
                return new LearningNoteFragment(); //Change later
        }
        return new LearningNoteFragment();
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
