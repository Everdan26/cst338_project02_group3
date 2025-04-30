package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cst338.cst338_project02_group3.databinding.ActivityEditProfileBinding;

public class EditProfileActivity extends AppCompatActivity {

    private static final String EDIT_PROFILE_USER_ID = "com.cst338.project02_group3.EDIT_PROFILE_USER_ID";
    private ActivityEditProfileBinding binding;

    private int loggedInUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        loggedInUserId = getIntent().getIntExtra(EDIT_PROFILE_USER_ID, -1);

        binding.editProfileViewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = UserProfileActivity.userProfileIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });

        //back button
        binding.editProfileBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WelcomeUser.welcomeUserIntentFactory(getApplicationContext(),loggedInUserId);
                startActivity(intent);
            }
        });
    }

    static Intent editProfileIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, EditProfileActivity.class);
        intent.putExtra(EDIT_PROFILE_USER_ID, userId);
        return intent;
    }
}