package com.cst338.cst338_project02_group3;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;

public class IntentTest {

    private static final int TEST_VALUE = 1;

    //Intent keys. Keep adding them as you add intent tests.
    private static final String USER_PROFILE_ACTIVITY_USER_ID = "com.cst338.project02_group3.USER_PROFILE_ACTIVITY_USER_ID";
    private static final String WELCOME_ACTIVITY_USER_ID = "com.cst338.project02_group3.WELCOME_ACTIVITY_USER_ID";

    @Test
    public void userProfileIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, UserProfileActivity.class);
        intent.putExtra(USER_PROFILE_ACTIVITY_USER_ID, TEST_VALUE);

        assertEquals(TEST_VALUE, intent.getIntExtra(USER_PROFILE_ACTIVITY_USER_ID, -1));
    }

    @Test
    public void welcomeUserIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, WelcomeUser.class);
        intent.putExtra(WELCOME_ACTIVITY_USER_ID, TEST_VALUE);

        assertEquals(TEST_VALUE, intent.getIntExtra(WELCOME_ACTIVITY_USER_ID, -1));
    }
}
