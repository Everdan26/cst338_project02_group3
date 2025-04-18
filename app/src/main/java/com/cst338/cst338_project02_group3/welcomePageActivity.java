package com.cst338.cst338_project02_group3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.cst338.cst338_project02_group3.databinding.ActivityWelcomePageBinding;

public class welcomePageActivity extends AppCompatActivity {

    private ActivityWelcomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomePageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Use intent factory to open up other pages???

        binding.welcomePageFindMatchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Open Find Matches Page
            }
        });

        binding.welcomePageViewMatchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Open View Matches Page
            }
        });

        binding.welcomePageEditProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Open Edit Profile Page
            }
        });


    }
}