package com.example.cybersecurityapp.menufrag;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cybersecurityapp.MenuActivity;
import com.example.cybersecurityapp.QuizActivity;
import com.example.cybersecurityapp.R;

public class MenuHomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView  = inflater.inflate(R.layout.fragment_menu_home,container,false);
//        @SuppressLint("MissingInflatedId") Button button = rootView.findViewById(R.id.startQuizButton);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("TESTACTION","THIS IS A TEST");
//            }
//        });
//        Button button = rootView.findViewById(R.id.startQuizButton);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), QuizActivity.class);
//                startActivity(intent);
//            }
//        });

        return rootView;
    }


}