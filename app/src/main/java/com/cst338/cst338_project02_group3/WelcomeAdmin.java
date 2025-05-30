package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;

import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.database.entities.User;
import com.cst338.cst338_project02_group3.databinding.ActivityWelcomeAdminBinding;

public class WelcomeAdmin extends AppCompatActivity {

    private static final String WELCOME_ADMIN_USER_ID = "com.cst338.project02_group3.WELCOME_ADMIN_USER_ID";

    private ActivityWelcomeAdminBinding binding;

    private DatingAppRepository repository;

    private int loggedInUserId;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeAdminBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());

        repository = DatingAppRepository.getRepository(getApplication());

        loggedInUserId = getIntent().getIntExtra(WELCOME_ADMIN_USER_ID, -1);
        if (loggedInUserId != -1) {
            LiveData<User> userObserver = repository.getUserByUserId(loggedInUserId);
            userObserver.observe(this, user -> {
                this.user = user;
            });
        }

        //Logout Button
//        binding.logOutButtonAdmin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showLogoutDialog();
//            }
//        });

        //main menu button
        binding.mainMenuAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WelcomeUser.welcomeUserIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });

        //User Reports
        binding.userReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ReportLogs.reportLogsIntentFactory(getApplicationContext(),loggedInUserId);
                startActivity(intent);

            }
        });

        //Ban Users
        binding.banUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AdminBanActivity.adminBanActivityIntentFactory(getApplicationContext(),loggedInUserId);
                startActivity(intent);
            }
        });

    }

    static Intent welcomeAdminIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, WelcomeAdmin.class);
        intent.putExtra(WELCOME_ADMIN_USER_ID, userId);
        return intent;
    }

    private void showLogoutDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(WelcomeAdmin.this);
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

        getIntent().putExtra(WELCOME_ADMIN_USER_ID, MainActivity.LOGGED_OUT);

        startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext()));
    }
}