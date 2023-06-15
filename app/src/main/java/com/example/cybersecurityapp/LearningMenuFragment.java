package com.example.cybersecurityapp;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.getIntent;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

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

import com.example.cybersecurityapp.MainActivity;
import com.example.cybersecurityapp.R;

import java.util.Locale;

public class LearningMenuFragment extends Fragment {
    InputStream is;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning_menu, container, false);
    }

    private void jsonParser() {
        Resources r = getResources();
        is = r.openRawResource(R.raw.eng_notes);
        if (MainActivity.val == 1){
            is = r.openRawResource(R.raw.fr_notes);
        } else if (MainActivity.val == 2) {
            is = r.openRawResource(R.raw.es_notes);
        }
        Scanner scanner = new Scanner(is);
        String jString = scanner.useDelimiter("\\A").next();
        scanner.close();
        try {
            JSONArray jArray = new JSONArray(jString);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObject = jArray.getJSONObject(i);
                String topic = jObject.getString("topic_name");
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
        LinearLayout linearLayout = v.findViewById(R.id.learningMenuLL);

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
        Intent intent = new Intent(getActivity(), LearningViewActivity.class);
        intent.putExtra("topic", topic);
        intent.putExtra("jsonObj", jsonObj);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jsonParser();
        View a = getView();

        /* this stuff causes errors
        darkMode = a.findViewById(R.id.dark);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            isNight = false;
        } else {
            isNight = true;
        }

        darkMode.setOnClickListener(v -> {
            if (isNight) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                isNight = false;
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                isNight = true;

            }
        });

         */
    }


    private void showDialog() {

        final String[] langs =  {"English","francés/francesa","español"};
        AlertDialog.Builder mBuilder =  new AlertDialog.Builder(getActivity());
        mBuilder.setTitle("");
        mBuilder.setSingleChoiceItems(langs, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                if(i==0){
                    setLocale("en");
                    MainActivity.val = 0;
                    /*
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                     */
                } else if (i==1)
                {
                    setLocale("fr");
                    MainActivity.val = 1;
                    /*
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                     */
                } else if (i==2)
                {
                    setLocale("es");
                    MainActivity.val  = 2;
                    /*
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                     */
                }
                dialog.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getActivity().getBaseContext().getResources().updateConfiguration(config,getActivity().getBaseContext().getResources().getDisplayMetrics());


        SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Language",lang);
        editor.apply();
    }
}