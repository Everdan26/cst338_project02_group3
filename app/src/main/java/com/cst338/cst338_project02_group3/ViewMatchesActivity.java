package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.bumptech.glide.Glide;
import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.database.entities.UserInfo;
import com.cst338.cst338_project02_group3.database.entities.Matches;
import com.cst338.cst338_project02_group3.databinding.ActivityViewMatchesBinding;

import java.util.ArrayList;
import java.util.List;

public class ViewMatchesActivity extends AppCompatActivity {

    private static final String MY_MATCHES_ACTIVITY_USER_ID = "com.cst338.project02_group3.USER_MATCHES_ACTIVITY_USER_ID";

    private ActivityViewMatchesBinding binding;

    private DatingAppRepository repository;

    private int loggedInUserId;

    private UserInfo userInfo;
    private List<UserInfo> matchesList = new ArrayList<>();
    private UserInfo matchedUserInfo;

    private int currentMatchIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewMatchesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = DatingAppRepository.getRepository(getApplication());
        loggedInUserId = getIntent().getIntExtra(MY_MATCHES_ACTIVITY_USER_ID,-1);

        if (loggedInUserId != -1) {
            LiveData<List<UserInfo>> usersWhoLikedMe = repository.getUsersWhoLikedMe(loggedInUserId);
            usersWhoLikedMe.observe(this, users -> {
                if (users != null && !users.isEmpty()){
                    matchesList.clear();
                    matchesList.addAll(users);
                    currentMatchIndex = 0;
                    displayMatch(currentMatchIndex);
                } else {
                    binding.textViewName.setText("No matches yet.");
                    binding.textViewAge.setText("");
                    binding.textViewBio.setText("");
                    binding.profileImg.setImageDrawable(null);
                }
            });
        }

        // Left button
        binding.matchBtnLeft.setOnClickListener(v -> {
            if (!matchesList.isEmpty()) {
                currentMatchIndex = (currentMatchIndex - 1 + matchesList.size()) % matchesList.size();
                displayMatch(currentMatchIndex);
            }
        });

        // Right button
        binding.matchBtnRight.setOnClickListener(v -> {
            if (!matchesList.isEmpty()) {
                currentMatchIndex = (currentMatchIndex + 1) % matchesList.size();
                displayMatch(currentMatchIndex);
            }
        });


        //back button
        binding.viewMatchesBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WelcomeUser.welcomeUserIntentFactory(getApplicationContext(),loggedInUserId);
                startActivity(intent);
            }
        });
    }

    private void displayMatch(int index) {
        UserInfo user = matchesList.get(index);
        binding.textViewName.setText(user.getName());
        binding.textViewAge.setText("Age: " + user.getAge());
        binding.textViewBio.setText("Bio: " + user.getBio());

        Glide.with(binding.profileImg.getContext())
                .load(user.getPhoto())
                .into(binding.profileImg);
    }

    static Intent viewMatchesIntentFactory(Context context, int userId) {
            Intent intent = new Intent(context, ViewMatchesActivity.class);
            intent.putExtra(MY_MATCHES_ACTIVITY_USER_ID,userId);
            return intent;
        }
    }