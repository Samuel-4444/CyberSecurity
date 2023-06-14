package com.example.cybersecurityapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cybersecurityapp.menufrag.MenuHomeFragment;
import com.example.cybersecurityapp.menufrag.MenuProfileFragment;
import com.example.cybersecurityapp.menufrag.MenuSettingsFragment;

public class MenuPagerAdapter extends FragmentStateAdapter {
    private static final int NUM_PAGES = 3;
    public MenuPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 0:
                return new MenuProfileFragment();
            case 1:
                return new MenuHomeFragment();
            case 2:
                return new MenuSettingsFragment();
            default:
                return new MenuProfileFragment();
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
