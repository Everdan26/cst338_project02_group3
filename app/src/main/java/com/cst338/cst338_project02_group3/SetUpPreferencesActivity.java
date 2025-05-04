package com.cst338.cst338_project02_group3;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.database.entities.UserInfo;
import com.cst338.cst338_project02_group3.database.entities.UserPreferences;
import com.cst338.cst338_project02_group3.databinding.ActivitySetUpPreferencesBinding;

public class SetUpPreferencesActivity extends AppCompatActivity {

    private static final String SET_UP_PREFERENCES_ACTIVITY_USER_ID = "com.cst338.project02_group3.SET_UP_PROFILE_ACTIVITY_USER_ID";

    private ActivitySetUpPreferencesBinding binding;
    private DatingAppRepository repository;
    private int loggedInUserId;
    private int newUserPrefInfoId;
    private UserInfo newUserInfo;
    private UserPreferences newUserPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetUpPreferencesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = DatingAppRepository.getRepository(getApplication());

        loggedInUserId = getIntent().getIntExtra(SET_UP_PREFERENCES_ACTIVITY_USER_ID, -1);

        // Creating UserPref record with default values
        if (loggedInUserId != -1) {
            newUserPref = new UserPreferences(-1, -1, "(No Gender)");
            repository.insertUserPreferences(newUserPref);
            if (loggedInUserId != -1) { // Fetching newest UserInfo record (for getting new Id)
                LiveData<UserInfo> userObserver = repository.getNewestUserInfo();
                userObserver.observe(this, userInfo -> {
                    this.newUserInfo = userInfo;
                    if (newUserInfo != null) {
                        changeNewRecordId();
                    }
                });
            }
        }


        binding.finishProfileSetUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmationDialog();
            }
        });
    }

    private void changeNewRecordId() {
        newUserPrefInfoId = newUserInfo.getUserInfoId();
        newUserPref.setUserInfoId(newUserPrefInfoId);
        repository.updateUserPrefInfoId(newUserPrefInfoId);
    }

    private void confirmationDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(SetUpPreferencesActivity.this);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Finish profile set up?");
        alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveUserPref();
                Toast.makeText(getApplicationContext(), "You're all complete with your account set up!", Toast.LENGTH_SHORT).show();
                Intent intent = WelcomeUser.welcomeUserIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });
        alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertBuilder.create().show();
    }

    private void saveUserPref() {
        String ageInput = binding.setAgePrefEditText.getText().toString();
        String genderInput = binding.setGenderPrefEditText.getText().toString();

        if (!ageInput.isEmpty()) {
            newUserPref.setAge(parseInt(ageInput));
        }
        if (!genderInput.isEmpty()) {
            newUserPref.setGender(genderInput);
        }

        repository.saveNewUserPreference(newUserPref.getAge(), newUserPref.getGender(), newUserPrefInfoId);
    }


    static Intent setUpPreferencesIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, SetUpPreferencesActivity.class);
        intent.putExtra(SET_UP_PREFERENCES_ACTIVITY_USER_ID, userId);
        return intent;
    }
}