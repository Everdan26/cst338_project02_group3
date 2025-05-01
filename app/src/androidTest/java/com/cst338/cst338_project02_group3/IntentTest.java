package com.cst338.cst338_project02_group3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;

public class IntentTest {

    private static final int TEST_VALUE = 1;

    //Intent keys. Keep adding them as you add intent tests.
    private static final String USER_PROFILE_ACTIVITY_USER_ID = "com.cst338.project02_group3.USER_PROFILE_ACTIVITY_USER_ID";
    private static final String WELCOME_ACTIVITY_USER_ID = "com.cst338.project02_group3.WELCOME_ACTIVITY_USER_ID";
    private static final String MAIN_ACTIVITY_USER_ID = "com.cst338.project02_group3.MAIN_ACTIVITY_USER_ID";
    private static final String WELCOME_ADMIN_USER_ID = "com.cst338.project02_group3.WELCOME_ADMIN_USER_ID";
    private static final String MY_MATCHES_ACTIVITY_USER_ID = "com.cst338.project02_group3.USER_MATCHES_ACTIVITY_USER_ID";
    private static final String REPORT_LOGS_USER_ADMIN = "com.cst338.project02_group3.REPORT_LOGS_USER_ADMIN";

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

    @Test
    public void welcomeAdminIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, WelcomeAdmin.class);
        intent.putExtra(WELCOME_ADMIN_USER_ID, TEST_VALUE);

        assertEquals(TEST_VALUE, intent.getIntExtra(WELCOME_ADMIN_USER_ID, -1));
    }

    @Test
    public void MainActivityIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, TEST_VALUE);

        assertEquals(TEST_VALUE, intent.getIntExtra(MAIN_ACTIVITY_USER_ID, -1));
    }

    @Test
    public void viewMatchesActivityIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, ViewMatchesActivity.class);
        intent.putExtra(MY_MATCHES_ACTIVITY_USER_ID, TEST_VALUE);

        assertEquals(TEST_VALUE, intent.getIntExtra(MY_MATCHES_ACTIVITY_USER_ID, -1));
    }

    //Intent for Sign Up Activity
    @Test
    public void signUpActivityIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, SignUpActivity.class);
        assertNotNull(intent);

    }

    //Intent for Report Logs
    @Test
    public void reportLogsActivityIntentTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, ReportLogs.class);
        intent.putExtra(REPORT_LOGS_USER_ADMIN, TEST_VALUE);

        assertEquals(TEST_VALUE, intent.getIntExtra(REPORT_LOGS_USER_ADMIN, -1));

    }

}
