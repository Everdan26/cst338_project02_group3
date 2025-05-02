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

        // Creating UserInfo record with id of current user with default values
        loggedInUserId = getIntent().getIntExtra(SET_UP_PROFILE_ACTIVITY_USER_ID, -1);
        if (loggedInUserId != -1) {
            newUserInfo = new UserInfo(loggedInUserId, "(No Name)", -1, "(No Gender)", "(No bio)", "https://upload.wikimedia.org/wikipedia/commons/b/bc/Unknown_person.jpg");
            repository.insertUserInfo(newUserInfo);
        }

        // Wiring button to respective function
        binding.setUpProfileNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmationDialog();
            }
        });
    }

    private void confirmationDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(SetUpProfileActivity.this);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Save profile information?");
        alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveUserInfo();
                Toast.makeText(getApplicationContext(), "Profile info successfully saved!", Toast.LENGTH_SHORT).show();
                // TODO: Intent to SetUpPreferences view
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

    private void saveUserInfo() {
        String nameInput = binding.setNameEditText.getText().toString();
        String ageInput = binding.setAgeEditText.getText().toString();
        String genderInput = binding.setGenderEditText.getText().toString();
        String bioInput = binding.setBioEditText.getText().toString();
        String pfpInput = binding.setPfpEditText.getText().toString();

        if (!nameInput.isEmpty()) {
            newUserInfo.setName(nameInput);
        }
        if (!ageInput.isEmpty()) {
            newUserInfo.setAge(parseInt(ageInput));
        }
        if (!genderInput.isEmpty()) {
            newUserInfo.setGender(genderInput);
        }
        if (!bioInput.isEmpty()) {
            newUserInfo.setBio(bioInput);
        }
        if (!pfpInput.isEmpty()) {
            newUserInfo.setPhoto(pfpInput);
        }

        repository.updateUserInfo(newUserInfo.getName(), newUserInfo.getAge(), newUserInfo.getGender(),
                newUserInfo.getBio(), newUserInfo.getPhoto(), newUserInfo.getUserId());
    }

    static Intent setUpProfileIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, SetUpProfileActivity.class);
        intent.putExtra(SET_UP_PROFILE_ACTIVITY_USER_ID, userId);
        return intent;
    }
}