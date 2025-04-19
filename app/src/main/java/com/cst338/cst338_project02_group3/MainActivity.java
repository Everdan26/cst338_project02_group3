package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.database.entities.User;
import com.cst338.cst338_project02_group3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "DATING_APP";
    private static final String MAIN_ACTIVITY_USER_ID = "com.cst338.project02_group3.MAIN_ACTIVITY_USER_ID";
    static final String SHARED_PREFERENCE_USERID_KEY = "com.cst338.project02_group3.SHARED_PREFERENCE_USERID_KEY";
    static final String SHARED_PREFERENCE_USERID_VALUE = "com.cst338.project02_group3.SHARED_PREFERENCE_USERID_VALUE";
    static final String SAVED_INSTANCE_STATE_USERID_KEY = "com.cst338.project02_group3.SAVED_INSTANCE_STATE_USERID_KEY";
    static final int LOGGED_OUT = -1;
    private int loggedInUserId = -1;
    private ActivityMainBinding binding;
    private DatingAppRepository repository;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = DatingAppRepository.getRepository(getApplication());
        loginUser(savedInstanceState);

        if (loggedInUserId != LOGGED_OUT) {
            Intent intent;

            if(user.isAdmin()) {
                intent = WelcomeAdmin.welcomeAdminIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
            intent = WelcomeUser.welcomeUserIntentFactory(getApplicationContext(), loggedInUserId);
            startActivity(intent);
        }

        updateSharedPreference();

        binding.mainLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LoginActivity.loginActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        binding.mainSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SignUpActivity.signUpActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

    }

    private void loginUser(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_userId_key), Context.MODE_PRIVATE);

        loggedInUserId = sharedPreferences.getInt(getString(R.string.preference_userId_key), LOGGED_OUT);

        if (loggedInUserId == LOGGED_OUT & savedInstanceState != null && savedInstanceState.containsKey(SAVED_INSTANCE_STATE_USERID_KEY)) {
            loggedInUserId = savedInstanceState.getInt(SAVED_INSTANCE_STATE_USERID_KEY, LOGGED_OUT);
        }
        if (loggedInUserId == LOGGED_OUT) {
            loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);
        }
        if (loggedInUserId == LOGGED_OUT) {
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserId(loggedInUserId);
        userObserver.observe(this, user -> {
            this.user = user;
            if(user != null) {
                invalidateOptionsMenu();
            }
        });
    }

    private void updateSharedPreference() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putInt(getString(R.string.preference_userId_key), loggedInUserId);
        sharedPrefEditor.apply();
    }


    static Intent mainActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
}