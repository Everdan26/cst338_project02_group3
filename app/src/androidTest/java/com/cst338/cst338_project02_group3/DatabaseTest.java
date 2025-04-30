package com.cst338.cst338_project02_group3;

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

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private DatingAppDatabase db;

    private UserDAO userDAO;
    private UserInfoDAO userInfoDAO;
    private ReportDAO reportDAO;
    private UserPreferencesDAO userPreferencesDAO;
    private MatchesDAO matchesDAO;

    private int userId;

    private User user;
    private UserInfo userInfo;
    private Report report;
    private UserPreferences userPreferences;
    private Matches matches;



}
