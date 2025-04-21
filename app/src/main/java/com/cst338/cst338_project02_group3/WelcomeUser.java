package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cst338.cst338_project02_group3.database.entities.User;
import com.cst338.cst338_project02_group3.databinding.ActivityWelcomeUserBinding;

public class WelcomeUser extends AppCompatActivity {

    private ActivityWelcomeUserBinding binding;
    private static final String WELCOME_ACTIVITY_USER_ID = "com.cst338.project02_group3.WELCOME_ACTIVITY_USER_ID";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Wiring up buttons to other pages (find matches, view matches, edit profile)
        binding.findMatchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = FindMatchesActivity.findMatchesIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        binding.myMatchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ViewMatchesActivity.viewMatchesIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        binding.editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EditProfileActivity.editProfileIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    static Intent welcomeUserIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, WelcomeUser.class);
        intent.putExtra(WELCOME_ACTIVITY_USER_ID, userId);
        return intent;
    }
}