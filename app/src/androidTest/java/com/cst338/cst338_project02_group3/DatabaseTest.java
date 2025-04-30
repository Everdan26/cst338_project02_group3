package com.cst338.cst338_project02_group3;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cst338.cst338_project02_group3.database.DatingAppDatabase;
import com.cst338.cst338_project02_group3.database.MatchesDAO;
import com.cst338.cst338_project02_group3.database.ReportDAO;
import com.cst338.cst338_project02_group3.database.UserDAO;
import com.cst338.cst338_project02_group3.database.UserInfoDAO;
import com.cst338.cst338_project02_group3.database.UserPreferencesDAO;
import com.cst338.cst338_project02_group3.database.entities.Matches;
import com.cst338.cst338_project02_group3.database.entities.Report;
import com.cst338.cst338_project02_group3.database.entities.User;
import com.cst338.cst338_project02_group3.database.entities.UserInfo;
import com.cst338.cst338_project02_group3.database.entities.UserPreferences;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private DatingAppDatabase db;

    private UserDAO userDAO;
    private UserInfoDAO userInfoDAO;
    private ReportDAO reportDAO;
    private UserPreferencesDAO userPreferencesDAO;
    private MatchesDAO matchesDAO;


    private User user;
    private UserInfo userInfo;
    private Report report;
    private UserPreferences userPreferences;
    private Matches matches;

    //Dummy user to test matches. Shouldn't actually test it.
    private User matchUser;

    private int userId;
    private int userInfoId;

    @Before
    public void setup() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, DatingAppDatabase.class).build();

        userDAO = db.userDAO();
        userInfoDAO = db.userInfoDAO();
        reportDAO = db.reportDAO();
        userPreferencesDAO = db.userPreferencesDAO();
        matchesDAO = db.matchesDAO();


        user = new User("testuser", "testuser");
        matchUser = new User("matchtest", "matchtest");

        userId = user.getId();

        userInfo = new UserInfo(userId, "Monte", 21, "M", "Test Bio", "csumb.edu");

        userInfoId = userInfo.getUserInfoId();

        report = new Report(userId, "Test Report", false);
        userPreferences = new UserPreferences(userInfoId, 21, "F");
        matches = new Matches(userId, matchUser.getId());
    }

    @After
    public void teardown() {
        userId = 0;

        user = null;
        matchUser = null;
        userInfo = null;
        report = null;
        userPreferences = null;
        matches = null;

        db.close();
    }


}
