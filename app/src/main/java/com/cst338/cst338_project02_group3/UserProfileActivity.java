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

import com.bumptech.glide.Glide;
import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.database.entities.User;
import com.cst338.cst338_project02_group3.database.entities.UserInfo;
import com.cst338.cst338_project02_group3.databinding.ActivityUserProfileBinding;

public class UserProfileActivity extends AppCompatActivity {

    private static final String USER_PROFILE_ACTIVITY_USER_ID = "com.cst338.project02_group3.USER_PROFILE_ACTIVITY_USER_ID";

    private ActivityUserProfileBinding binding;

    private DatingAppRepository repository;

    private int loggedInUserId;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = DatingAppRepository.getRepository(getApplication());

        loggedInUserId = getIntent().getIntExtra(USER_PROFILE_ACTIVITY_USER_ID, -1);
        if (loggedInUserId != -1) {
            LiveData<UserInfo> userInfoObserver = repository.getUserInfoByUserId(loggedInUserId);
            //Get User's info and replace text with relevant info from bio
            userInfoObserver.observe(this, userInfo -> {
                this.userInfo = userInfo;
                if (this.userInfo != null) {
                    String nameAgeGender = getString(R.string.name_age_gender, userInfo.getName(), userInfo.getAge(), userInfo.getGender());
                    binding.userProfileNameAgeGender.setText(nameAgeGender);

                    String bio = getString(R.string.user_biography, userInfo.getBio());
                    binding.userProfileBioTextView.setText(bio);

//                    String url = userInfo.getPhoto();
                    Glide.with(binding.userProfileImageView).load(userInfo.getPhoto()).into(binding.userProfileImageView);
                }
            });
        }

        binding.userProfileBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EditProfileActivity.editProfileIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });
    }


    static Intent userProfileIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, UserProfileActivity.class);
        intent.putExtra(USER_PROFILE_ACTIVITY_USER_ID, userId);
        return intent;
    }
}