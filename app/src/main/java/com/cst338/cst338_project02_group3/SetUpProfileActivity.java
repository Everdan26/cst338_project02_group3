package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.database.entities.UserInfo;
import com.cst338.cst338_project02_group3.databinding.ActivitySetUpProfileBinding;

public class SetUpProfileActivity extends AppCompatActivity {

    private static final String SET_UP_PROFILE_ACTIVITY_USER_ID = "com.cst338.project02_group3.SET_UP_PROFILE_ACTIVITY_USER_ID";

    private ActivitySetUpProfileBinding binding;
    private DatingAppRepository repository;
    private int loggedInUserId;
    private UserInfo newUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetUpProfileBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_set_up_profile);
        repository = DatingAppRepository.getRepository(getApplication());

        // Getting id of current user
        loggedInUserId = getIntent().getIntExtra(SET_UP_PROFILE_ACTIVITY_USER_ID, -1);
        if (loggedInUserId != -1) {
            newUserInfo = new UserInfo(loggedInUserId, "defName", -1, "M", "defBio", "https://upload.wikimedia.org/wikipedia/commons/b/bc/Unknown_person.jpg");

        }
    }

    static Intent setUpProfileIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, SetUpProfileActivity.class);
        intent.putExtra(SET_UP_PROFILE_ACTIVITY_USER_ID, userId);
        return intent;
    }
}