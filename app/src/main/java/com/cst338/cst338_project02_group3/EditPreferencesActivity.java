package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.database.entities.UserPreferences;
import com.cst338.cst338_project02_group3.databinding.ActivityEditPreferencesBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EditPreferencesActivity extends AppCompatActivity {

    private static final String EDIT_PREFERENCES_USER_ID = "com.cst338.project02_group3.EDIT_PREFERENCES_USER_ID";
    ActivityEditPreferencesBinding binding;

    private int loggedInUserId;
    private UserPreferences userPreferences;

    private DatingAppRepository repository;

    private static final int NUMBER_OF_THREADS = 4;

    private static final ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditPreferencesBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        repository = DatingAppRepository.getRepository(getApplication());

        loggedInUserId = getIntent().getIntExtra(EDIT_PREFERENCES_USER_ID, -1);
        if (loggedInUserId != -1) {
            LiveData<UserPreferences> userPreferencesObserver = repository.getUserPreferencesByUserId(loggedInUserId);
            userPreferencesObserver.observe(this, userPreferencesId -> {
                this.userPreferences = userPreferencesId;
                if (this.userPreferences != null) {
                    binding.editPreferencesAgeEditText.setHint(Integer.toString(userPreferences.getAge()));
                    binding.editPreferencesGenderEditText.setHint(this.userPreferences.getGender());
                }
            });
        }

        binding.editPreferencesSaveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = Integer.parseInt(binding.editPreferencesAgeEditText.getText().toString());
                String gender = binding.editPreferencesGenderEditText.toString();

                sendPreferences(age, gender);

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


    private void sendPreferences(int age, String gender) {
        //TODO: see why this is throwing an error unhappy that it's working on the main thread.
        //Need to make it asynchronous somehow.
        if (loggedInUserId != -1) {
            LiveData<UserPreferences> userPreferencesObserver = repository.getUserPreferencesByUserId(loggedInUserId);
            userPreferencesObserver.observe(this, userPreferencesId -> {
                this.userPreferences = userPreferencesId;
                if (this.userPreferences != null) {
                    executorService.execute(() -> {
                        repository.updateUserPreferences(age, gender, userPreferencesId.getUserPreferencesId());
                    });

                }
            });
        }
    }

    static Intent editPreferencesIntentFactory (Context context, int userId) {
        Intent intent = new Intent(context, EditPreferencesActivity.class);
        intent.putExtra(EDIT_PREFERENCES_USER_ID, userId);
        return intent;
    }
}