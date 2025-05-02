package com.cst338.cst338_project02_group3;

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
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetUpProfileBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_set_up_profile);
        repository = DatingAppRepository.getRepository(getApplication());

        // Getting id
    }
}