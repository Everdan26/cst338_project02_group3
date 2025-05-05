package com.cst338.cst338_project02_group3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import org.junit.Test;
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
        matches = new Matches(userId, matchUser.getId(), true);
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


    @Test
    public void insertUserTest() {
        userDAO.insert(user);
        assertNotNull(userDAO.getUserByUsernameTest("testuser"));
    }

    @Test
    public void removeUserTest() {
        userDAO.insert(user);
        assertNotNull(userDAO.getUserByUsernameTest("testuser"));
        userDAO.deleteUserByUsername("testuser");
        assertNull(userDAO.getUserByUsernameTest("testuser"));
    }

    //Testing the SQL call, since LiveData unit testing is extremely difficult/impossible/beyond my pay grade
    @Test
    public void getUserByIdTest() {
        userDAO.insert(user);
        assertNotNull(userDAO.getUserByUserIdTest(1));
    }

    @Test
    public void deleteAllUsersTest() {
        userDAO.insert(user);
        userDAO.insert(matchUser);
        userDAO.deleteAll();
        assertTrue(userDAO.getAllRecordsTest().isEmpty());
    }

    @Test
    public void updatePasswordTest() {
        userDAO.insert(user);
        userDAO.updatePassword("testuser", "newPassword");
        assertEquals("newPassword", userDAO.getUserByUsernameTest("testuser").getPassword());
    }


    //User Preferences Test (insert)
    @Test
    public void preferenceTest() {
        userPreferences = new UserPreferences(userInfoId, 21, "F");
        userPreferencesDAO.insert(userPreferences);
        assertNotNull(userPreferences);
    }

    // User Preferences Test (update)
    // Only testing SQL call
    @Test
    public void updatePreferenceTest() {
        userInfoDAO.insert(userInfo);
        userPreferencesDAO.insert(userPreferences);
        int changedRows = userPreferencesDAO.saveNewUserPreferenceTest(42, "M", userInfoId);
        assertEquals(1, changedRows);

        int unchangedRows = userPreferencesDAO.saveNewUserPreferenceTest(42, "M", userInfoId+1);
        assertEquals(0,unchangedRows);
    }

    //User Preferences Test (delete)
    @Test
    public void preferenceDeleteTest() {
        userPreferences = new UserPreferences(userInfoId, 21, "F");
        userPreferencesDAO.delete(userPreferences);
        UserPreferences getPref = userPreferencesDAO.getUserPreferencesByUserId(userId).getValue();
        assertNull(getPref);
    }

    //Insert UserInfo test
    @Test
    public void insertUserInfoTest(){
        userInfoDAO.insert(userInfo);
        assertNotNull(userInfo);
        assertEquals("Monte", userInfo.getName());
        assertEquals(21, userInfo.getAge());
    }

    // Update UserInfo test
    // Only testing SQL call because updateUserInfo(...) is asynchronous
    @Test
    public void updateUserInfo() {
        userInfoDAO.insert(userInfo);   // See set up for initial info
        int changedRows = userInfoDAO.updateUserInfoTest("Changed", 42, "F", "Changed", "Changed", userId);
        assertEquals(1, changedRows);

        int unchangedRows = userInfoDAO.updateUserInfoTest("Changed", 42, "F", "Changed", "Changed", userId+1);
        assertEquals(0,unchangedRows);
    }

    //Delete UserInfo test
    @Test
    public void deleteUserInfoTest() {
        UserInfo u1 = new UserInfo(3, "Bob", 22, "M", "lol", "");
        UserInfo u2 = new UserInfo(4, "Bobby", 28, "M", "haha", "");
        userInfoDAO.insert(u1,u2);

        userInfoDAO.deleteAll();
        UserInfo getU1 = userInfoDAO.getUserInfoByUserId(3).getValue();
        assertNull(getU1);
        UserInfo getU2 = userInfoDAO.getUserInfoByUserId(4).getValue();
        assertNull(getU2);
    }

    //Report Logs Test
    @Test
    public void insertReport() {
        reportDAO.insert(report);
        assertNotNull(reportDAO.getReportByUserId(report.getUserId()));

    }

    //Matches Entity Test
    @Test
    public void insertMatchTest() {
        userDAO.insert(user);
        userDAO.insert(matchUser);

        Matches match = new Matches(user.getId(), matchUser.getId(), true);
        matchesDAO.insert(match);

        Matches retrievedMatch = matchesDAO.getMatchByUserIds(user.getId(), matchUser.getId());
        assertNotNull(retrievedMatch);
        assertEquals(user.getId(), retrievedMatch.getUserId1());
        assertEquals(matchUser.getId(), retrievedMatch.getUserId2());
        assertTrue(retrievedMatch.isLike());
    }

    //Delete all matches test
    public void deleteAllMatchesTest() {
        userDAO.insert(user);
        userDAO.insert(matchUser);
        Matches match = new Matches(user.getId(), matchUser.getId(), true);
        matchesDAO.insert(match);

        matchesDAO.deleteAll();
        assertTrue(matchesDAO.getAllMatches().isEmpty());
    }

    @Test
    public void deleteReport() {
        reportDAO.insert(report);
        reportDAO.deleteReport(userId);

        //User should not be in the data, so should be false
        assertFalse(reportDAO.isReportInData(userId));
    }

    @Test
    public void updateReportStatus() {
        //Insert something first before updating
        reportDAO.insert(report);

        //Ban Status should be false
        assertFalse(reportDAO.banStatus(report.getUserId()));

        //Updating the ban status to true
        reportDAO.updateBanStatus(true,report.getUserId());

        //Checking if it got updated the status
        assertTrue(reportDAO.banStatus(report.getUserId()));
    }





}
