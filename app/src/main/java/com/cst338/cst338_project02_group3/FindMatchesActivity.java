package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.database.entities.User;
import com.cst338.cst338_project02_group3.database.entities.UserInfo;
import com.cst338.cst338_project02_group3.databinding.ActivityFindMatchesBinding;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class FindMatchesActivity extends AppCompatActivity {

    private ActivityFindMatchesBinding binding;

    private static final String FIND_MATCHES_ACTIVITY_USER_ID = "com.cst338.project02_group3.FIND_MATCHES_ACTIVITY_USER_ID";
    private DatingAppRepository repository;
    private User user;
    private int loggedInUserId;
    private UserInfo randomUserInfo;
    private List<UserInfo> potentialMatches = new ArrayList<>();
    private int currentIndex = 0;


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

        repository.getUnmatchedUsers(loggedInUserId).observe(this, users -> {
            if (users != null && !users.isEmpty()) {
                potentialMatches.clear();
                potentialMatches.addAll(users);
                currentIndex = 0;
                setRandomUserInfo();
            } else {
                binding.FindMatchesResultTextViewName.setText("No matches yet.");
                binding.FindMatchesResultUserInfoTextViewAge.setText("");
                binding.FindMatchesResultUserInfoTextViewBio.setText("");
                binding.FindMatchesResultUserInfoImg.setImageDrawable(null);
            }
        });

        //Like button or Yes button
        binding.FindMatchesResultInfoBtnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WelcomeUser.welcomeUserIntentFactory(getApplicationContext(),loggedInUserId);
                startActivity(intent);
            }
        });

        //Like button function
        binding.FindMatchesResultInfoBtnYes.setOnClickListener(v -> {
            UserInfo targetUser = potentialMatches.get(currentIndex);
            repository.saveMatch(loggedInUserId, targetUser.getUserId(), true);
        });


        //Report Button
        binding.FindMatchesResultInfoBtnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               repository.insertReportUser(randomUserInfo);
               Toast.makeText(FindMatchesActivity.this,"Successfully reported!", Toast.LENGTH_SHORT).show();
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

    static Intent findMatchesIntentFactory(Context context, int loggedInUser) {
        Intent intent = new Intent(context, FindMatchesActivity.class);
        intent.putExtra(FIND_MATCHES_ACTIVITY_USER_ID,loggedInUser);

        return intent;
    }

    private void setRandomUserInfo() {
        if (potentialMatches.isEmpty()) return;

        Random random = new Random();
        int index = random.nextInt(potentialMatches.size());
        randomUserInfo = potentialMatches.get(index);

        binding.FindMatchesResultTextViewName.setText(randomUserInfo.getName());
        binding.FindMatchesResultUserInfoTextViewAge.setText(Integer.toString(randomUserInfo.getAge()));
        binding.FindMatchesResultUserInfoTextViewBio.setText(randomUserInfo.getBio());
        Glide.with(binding.FindMatchesResultUserInfoImg)
                .load(randomUserInfo.getPhoto())
                .into(binding.FindMatchesResultUserInfoImg);

    }



}