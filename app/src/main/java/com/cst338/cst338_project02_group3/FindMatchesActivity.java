package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.database.entities.User;
import com.cst338.cst338_project02_group3.database.entities.UserInfo;
import com.cst338.cst338_project02_group3.databinding.ActivityFindMatchesBinding;
import com.bumptech.glide.Glide;


public class FindMatchesActivity extends AppCompatActivity {

    private ActivityFindMatchesBinding binding;

    private static final String FIND_MATCHES_ACTIVITY_USER_ID = "com.cst338.project02_group3.FIND_MATCHES_ACTIVITY_USER_ID";
    private static final String PREFERRED_GENDER = "com.cst338.project02_group3.PREFERRED_GENDER";
    private DatingAppRepository repository;
    private User user;
    private int loggedInUserId;
    private UserInfo randomUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindMatchesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = DatingAppRepository.getRepository(getApplication());

        loggedInUserId = getIntent().getIntExtra(FIND_MATCHES_ACTIVITY_USER_ID, -1);

        if(loggedInUserId != -1) {
            setRandomUserInfo();

        }

        //Like button or Yes button
        binding.FindMatchesResultUserInfoBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WelcomeUser.welcomeUserIntentFactory(getApplicationContext(),loggedInUserId);
                startActivity(intent);
            }
        });

        //back button
        binding.FindMatchesResultUserInfoBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WelcomeUser.welcomeUserIntentFactory(getApplicationContext(),loggedInUserId);
                startActivity(intent);
            }
        });

        //back button
        binding.FindMatchesResultUserInfoBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WelcomeUser.welcomeUserIntentFactory(getApplicationContext(),loggedInUserId);
                startActivity(intent);
            }
        });
    }

    static Intent findMatchesIntentFactory(Context context, String prefGender, int loggedInUser) {
        Intent intent = new Intent(context, FindMatchesActivity.class);
        intent.putExtra(PREFERRED_GENDER,prefGender);
        intent.putExtra(FIND_MATCHES_ACTIVITY_USER_ID,loggedInUser);

        return intent;
    }

    private void setRandomUserInfo() {
        LiveData<UserInfo> randomUserInfoObserver = repository.getRandomUserInfo(getIntent().getStringExtra(PREFERRED_GENDER));

        randomUserInfoObserver.observe(this, randomUser -> {
            this.randomUserInfo = randomUser;

            if (this.randomUserInfo != null) {
                binding.FindMatchesResultTextViewName.setText(randomUserInfo.getName());
                binding.FindMatchesResultUserInfoTextViewAge.setText(Integer.toString(randomUserInfo.getAge()));
                binding.FindMatchesResultUserInfoTextViewBio.setText(randomUserInfo.getBio());
                Glide.with(binding.FindMatchesResultUserInfoImg).load(randomUserInfo.getPhoto()).into(binding.FindMatchesResultUserInfoImg);
            }
        });

    }


}