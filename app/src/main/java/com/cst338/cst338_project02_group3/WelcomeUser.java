package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;

import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.database.entities.User;
import com.cst338.cst338_project02_group3.databinding.ActivityWelcomeUserBinding;

public class WelcomeUser extends AppCompatActivity {

    private static final String WELCOME_ACTIVITY_USER_ID = "com.cst338.project02_group3.WELCOME_ACTIVITY_USER_ID";

    private ActivityWelcomeUserBinding binding;

    private DatingAppRepository repository;
    private int loggedInUserId;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeUserBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        repository = DatingAppRepository.getRepository(getApplication());

        loggedInUserId = getIntent().getIntExtra(WELCOME_ACTIVITY_USER_ID, -1);
        if (loggedInUserId != -1) {
            LiveData<User> userObserver = repository.getUserByUserId(loggedInUserId);
            userObserver.observe(this, user -> {
                this.user = user;
                //Checks if user is not null
                if(this.user != null) {
                     //Changes the string text for the string resource welcome_user
                     //Uses %1$s... %1 == first parameter and $s == what input the parameter will take which is String
                     String welcomeMsg = getString(R.string.welcome_user,this.user.getUsername());
                     binding.titleWelcomeTextView.setText(welcomeMsg);

                    //Makes the admin show up if the user is an admin, else it will be hidden
                    if(this.user.isAdmin()) {
                        binding.adminButtonUser.setVisibility(View.VISIBLE);
                    } else {
                        binding.adminButtonUser.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }

        //Logout Button
        binding.logOutButtonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });

        //Admin Button
        binding.adminButtonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.isAdmin()) {
                    Intent intent = WelcomeAdmin.welcomeAdminIntentFactory(getApplicationContext(),loggedInUserId);
                    startActivity(intent);
                }
            }
        });

        // Wiring up buttons to other pages (find matches, view matches, edit profile)
        binding.findMatchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = FindMatchesActivity.findMatchesIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        binding.myMatchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ViewMatchesActivity.viewMatchesIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });

        binding.editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EditProfileActivity.editProfileIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });

    }

    static Intent welcomeUserIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, WelcomeUser.class);
        intent.putExtra(WELCOME_ACTIVITY_USER_ID, userId);
        return intent;
    }

    private void showLogoutDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(WelcomeUser.this);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Logout?");

        alertBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertBuilder.create().show();
    }

    private void logout() {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putInt(getString(R.string.preference_userId_key), MainActivity.LOGGED_OUT);
        sharedPrefEditor.apply();

        getIntent().putExtra(WELCOME_ACTIVITY_USER_ID, MainActivity.LOGGED_OUT);

        startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext()));
    }
}