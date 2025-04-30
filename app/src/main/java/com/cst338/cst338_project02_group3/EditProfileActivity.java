package com.cst338.cst338_project02_group3;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.database.entities.UserInfo;
import com.cst338.cst338_project02_group3.databinding.ActivityEditProfileBinding;

public class EditProfileActivity extends AppCompatActivity {

    private static final String EDIT_PROFILE_ACTIVITY_USER_ID = "com.cst338.project02_group3.EDIT_PROFILE_ACTIVITY_USER_ID";

    private ActivityEditProfileBinding binding;
    private DatingAppRepository repository;
    private int loggedInUserId;
    private UserInfo userInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        repository = DatingAppRepository.getRepository(getApplication());

        // Getting id and userInfo of current user
        loggedInUserId = getIntent().getIntExtra(EDIT_PROFILE_ACTIVITY_USER_ID, -1);
        if (loggedInUserId != -1) {
            LiveData<UserInfo> userObserver = repository.getUserInfoByUserId(loggedInUserId);
            userObserver.observe(this, userInfo -> {
                this.userInfo = userInfo;
            });
        }

        // TODO: Display the user's current information
        // TODO: ^^^ Add TextViews for above task to layout

        // Wiring button to respective function
        binding.editProfileSaveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmationDialog();
            }
        });
    }

    private void confirmationDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(EditProfileActivity.this);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Save changes?");
        alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editProfile();
                // Reloading page
                Intent intent = EditProfileActivity.editProfileIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });
        alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
    }
    private void editProfile() {
        String ageInput = binding.editAgeEditText.getText().toString();
        String genderInput = binding.editGenderEditText.getText().toString();
        String bioInput = binding.editBioEditText.getText().toString();
        String pfpInput = binding.editPfpEditText.getText().toString();

        // NOTE: I'm gonna try to edit the data using the setters in UserInfo.java
        //       instead of using SQL queries. We'll see if this works...

        if (!ageInput.isEmpty()) {
            userInfo.setAge(parseInt(ageInput));
        }
        if (!genderInput.isEmpty()) { // This may change in the future...
            userInfo.setGender(genderInput);
        }
        if (!bioInput.isEmpty()) {
            userInfo.setBio(bioInput);
        }
        if (!pfpInput.isEmpty()) {
            userInfo.setPhoto(pfpInput);
        }
    }

    static Intent editProfileIntentFactory(Context context) {
        return new Intent(context, EditProfileActivity.class);
    }
}