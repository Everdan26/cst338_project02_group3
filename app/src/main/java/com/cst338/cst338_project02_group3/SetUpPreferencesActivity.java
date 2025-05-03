package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SetUpPreferencesActivity extends AppCompatActivity {

    private static final String SET_UP_PREFERENCES_ACTIVITY_USER_ID = "com.cst338.project02_group3.SET_UP_PROFILE_ACTIVITY_USER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_preferences);
    }

    static Intent setUpPreferencesIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, SetUpPreferencesActivity.class);
        intent.putExtra(SET_UP_PREFERENCES_ACTIVITY_USER_ID, userId);
        return intent;
    }
}