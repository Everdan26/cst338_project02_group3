package com.cst338.cst338_project02_group3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;

public class IntentTest {

    private static final int TEST_VALUE = 1;
    private static final String TEST_STRING = "A";

    //Intent keys. Keep adding them as you add intent tests.
    private static final String USER_PROFILE_ACTIVITY_USER_ID = "com.cst338.project02_group3.USER_PROFILE_ACTIVITY_USER_ID";
    private static final String WELCOME_ACTIVITY_USER_ID = "com.cst338.project02_group3.WELCOME_ACTIVITY_USER_ID";
    private static final String MAIN_ACTIVITY_USER_ID = "com.cst338.project02_group3.MAIN_ACTIVITY_USER_ID";
    private static final String WELCOME_ADMIN_USER_ID = "com.cst338.project02_group3.WELCOME_ADMIN_USER_ID";
    private static final String MY_MATCHES_ACTIVITY_USER_ID = "com.cst338.project02_group3.USER_MATCHES_ACTIVITY_USER_ID";
    private static final String USER_ADMIN = "com.cst338.project02_group3.USER_ADMIN";
    private static final String FIND_MATCHES_ACTIVITY_USER_ID = "com.cst338.project02_group3.FIND_MATCHES_ACTIVITY_USER_ID";
    private static final String PREFERRED_GENDER = "com.cst338.project02_group3.PREFERRED_GENDER";
    private static final String EDIT_PREFERENCES_USER_ID = "com.cst338.project02_group3.EDIT_PREFERENCES_USER_ID";

    @Test
    public void userProfileIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = UserProfileActivity.userProfileIntentFactory(context, TEST_VALUE);

        assertEquals(TEST_VALUE, intent.getIntExtra(USER_PROFILE_ACTIVITY_USER_ID, -1));
    }

    @Test
    public void welcomeUserIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = WelcomeUser.welcomeUserIntentFactory(context, TEST_VALUE);

        assertEquals(TEST_VALUE, intent.getIntExtra(WELCOME_ACTIVITY_USER_ID, -1));
    }

    @Test
    public void welcomeAdminIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = WelcomeAdmin.welcomeAdminIntentFactory(context, TEST_VALUE);

        assertEquals(TEST_VALUE, intent.getIntExtra(WELCOME_ADMIN_USER_ID, -1));
    }

    @Test
    public void MainActivityIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = MainActivity.mainActivityIntentFactory(context);

        assertNotNull(intent);
    }

    @Test
    public void viewMatchesActivityIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = ViewMatchesActivity.viewMatchesIntentFactory(context, TEST_VALUE);

        assertEquals(TEST_VALUE, intent.getIntExtra(MY_MATCHES_ACTIVITY_USER_ID, -1));
    }

    //Intent for Sign Up Activity
    @Test
    public void signUpActivityIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = SignUpActivity.signUpActivityIntentFactory(context);

        assertNotNull(intent);
    }

    //Intent for Report Logs
    @Test
    public void reportLogsActivityIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = ReportLogs.reportLogsIntentFactory(context, TEST_VALUE);

        assertEquals(TEST_VALUE, intent.getIntExtra(USER_ADMIN, -1));
    }

    @Test
    public void loginActivityIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        assertNotNull(LoginActivity.loginActivityIntentFactory(context));
    }

    @Test
    public void findMatchesActivityIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = FindMatchesActivity.findMatchesIntentFactory(context, TEST_STRING, TEST_VALUE);
        assertEquals(TEST_STRING, intent.getStringExtra(PREFERRED_GENDER));
        assertEquals(TEST_VALUE, intent.getIntExtra(FIND_MATCHES_ACTIVITY_USER_ID, -1));
    }

    @Test
    public void editPreferencesActivityIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = EditPreferencesActivity.editPreferencesIntentFactory(context, TEST_VALUE);
        assertEquals(TEST_VALUE, intent.getIntExtra(EDIT_PREFERENCES_USER_ID, -1));
    }

}
