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
import androidx.lifecycle.LiveData;

import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.database.entities.User;
import com.cst338.cst338_project02_group3.database.entities.UserInfo;
import com.cst338.cst338_project02_group3.database.entities.UserPreferences;
import com.cst338.cst338_project02_group3.databinding.ActivityEditPreferencesBinding;

public class EditPreferencesActivity extends AppCompatActivity {

    private static final String EDIT_PREFERENCES_USER_ID = "com.cst338.project02_group3.EDIT_PREFERENCES_USER_ID";
    ActivityEditPreferencesBinding binding;

    private int loggedInUserId;
    private UserInfo userInfo;
    private UserPreferences userPreferences;

    private int newAge;
    private String newGender;

    private DatingAppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditPreferencesBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        repository = DatingAppRepository.getRepository(getApplication());

        loggedInUserId = getIntent().getIntExtra(EDIT_PREFERENCES_USER_ID, -1);
        if (loggedInUserId != -1) {
            LiveData<UserInfo> userInfoObserver = repository.getUserInfoByUserId(loggedInUserId);
            userInfoObserver.observe(this, userInfo -> {
                this.userInfo = userInfo;
            });
        }

        if (userInfo != null) {
            LiveData<UserPreferences> userPreferencesObserver = repository.getUserPreferencesByInfoId(userInfo.getUserInfoId());
            userPreferencesObserver.observe(this, userPreferences -> {
                this.userPreferences = userPreferences;
            });
        }

        binding.editPreferencesSaveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newAge = Integer.parseInt(binding.editPreferencesAgeEditText.getText().toString());
                newGender = binding.editPreferencesGenderEditText.toString();

                repository.updateUserPreferences(newAge, newGender, userPreferences.getUserPreferencesId());
                Intent intent = WelcomeUser.welcomeUserIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });

        binding.editPreferencesBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WelcomeUser.welcomeUserIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });

    }

    static Intent editPreferencesIntentFactory (Context context, int userId) {
        Intent intent = new Intent(context, EditPreferencesActivity.class);
        intent.putExtra(EDIT_PREFERENCES_USER_ID, userId);
        return intent;
    }
}