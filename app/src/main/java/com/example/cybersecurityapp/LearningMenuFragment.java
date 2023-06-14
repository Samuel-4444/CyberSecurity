package com.example.cybersecurityapp.menufrag;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.getIntent;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cybersecurityapp.MainActivity;
import com.example.cybersecurityapp.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View darkMode;
    boolean isNight;


    public MenuProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuProfileFragment newInstance(String param1, String param2) {
        MenuProfileFragment fragment = new MenuProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View a = getView();

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
//                    Intent intent = getIntent();
//                    finish();
//                    startActivity(intent);
                } else if (i==1)
                {
                    setLocale("fr");
                    MainActivity.val = 1;
//                    Intent intent = getIntent();
//                    finish();
//                    startActivity(intent);
                } else if (i==2)
                {
                    setLocale("es");
                    MainActivity.val  = 2;
//                    Intent intent = getIntent();
//                    finish();
//                    startActivity(intent);
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



    ;
}