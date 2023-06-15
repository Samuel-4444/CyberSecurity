package com.example.cybersecurityapp.menufrag;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cybersecurityapp.R;

import java.util.Locale;

public class MenuSettingsFragment extends Fragment {
    public static int val = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadLocale();

        ImageView english;
        ImageView french;
        ImageView spanish;

        Activity activity = getActivity();

        english = view.findViewById(R.id.acc_flag_GBR);

        english.setOnClickListener(v -> {
            setLocale("en");
            val = 0;
            Intent intent = activity.getIntent();
            activity.finish();
            startActivity(intent);
        });

        french = view.findViewById(R.id.acc_flag_FRA);

        french.setOnClickListener(v -> {
            setLocale("fr");
            val = 1;
            Intent intent = activity.getIntent();
            activity.finish();
            startActivity(intent);
        });

        spanish = view.findViewById(R.id.acc_flag_ESP);
        spanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setLocale("es");
                val = 2;
                Intent intent = activity.getIntent();
                activity.finish();
                startActivity(intent);
            }
        });
    }

    private void setLocale(String lang){
        Context c = getContext();
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        c.getResources().updateConfiguration(config,c.getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = c.getSharedPreferences("Settings",Activity.MODE_PRIVATE).edit();
        editor.putString("My_Language",lang);
        editor.apply();
    };

    private void loadLocale() {
        Context c = getContext();
        SharedPreferences pref = c.getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = pref.getString("My_Language", "");
        setLocale(language);


    };
}