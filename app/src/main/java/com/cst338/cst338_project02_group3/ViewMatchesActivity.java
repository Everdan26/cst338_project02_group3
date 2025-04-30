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

import java.util.List;

public class ViewMatchesActivity extends AppCompatActivity {

    private static final String USER_MATCHES_ACTIVITY_USER_ID = "com.cst338.project02_group3.USER_MATCHES_ACTIVITY_USER_ID";

    private ActivityViewMatchesBinding binding;

    private DatingAppRepository repository;

    private int loggedInUserId;
    private List<Matches> matchesList;
    private UserInfo matchedUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewMatchesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
//        repository = DatingAppRepository.getRepository(getApplication());
//
//        loggedInUserId = getIntent().getIntExtra(USER_MATCHES_ACTIVITY_USER_ID, -1);
//        if (loggedInUserId != -1) {
//
//            repository.getUserInfoByUserId(Matches.getUserId1()).observe(this, userInfo -> {
//                if (userInfo != null) {
//                    binding.textViewName.setText(userInfo.getName());
//                    binding.textViewAge.setText("Age: " + userInfo.getAge());
//                    binding.textViewBio.setText("Bio: " + userInfo.getBio());
//
//                    Glide.with(binding.profileImg.getContext())
//                            .load(userInfo.getPhoto())
//                            .into(binding.profileImg);
//                }
//            });
//        }

        //TODO: add back button on matches page
        binding.viewMatchesBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WelcomeUser.welcomeUserIntentFactory(getApplicationContext(),loggedInUserId);
                startActivity(intent);
            }
        });
    }


    static Intent viewMatchesIntentFactory(Context context) {
            Intent intent = new Intent(context, ViewMatchesActivity.class);
            return intent;
        }
    }