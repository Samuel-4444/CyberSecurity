package com.example.cybersecurityapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class HelpDialog  extends AppCompatDialogFragment {
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Help")
                .setMessage("Email should be a valid email. \n" +
                        "Password should be at least 5 characters. \n" +
                        "Password and Confirm Password should match. \n" +
                        "Email and Password should not be equal. ")
                .setPositiveButton("Understood", (dialog, which) -> {

                });
        return builder.create();
    }
}
