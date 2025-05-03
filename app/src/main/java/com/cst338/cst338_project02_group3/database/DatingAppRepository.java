package com.cst338.cst338_project02_group3.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.cst338.cst338_project02_group3.ReportLogs;
import com.cst338.cst338_project02_group3.database.entities.Matches;
import com.cst338.cst338_project02_group3.database.entities.Report;
import com.cst338.cst338_project02_group3.database.entities.User;
import com.cst338.cst338_project02_group3.database.entities.UserInfo;
import com.cst338.cst338_project02_group3.database.entities.UserPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DatingAppRepository {

    private final UserDAO userDAO;
    private final UserInfoDAO userInfoDAO;
    private final UserPreferencesDAO userPreferencesDAO;
    private final MatchesDAO matchesDAO;
    private final ReportDAO reportDAO;
//    private ArrayList<Report> reportLogs;

    private static DatingAppRepository repository;

    private DatingAppRepository(Application application) {
        DatingAppDatabase db = DatingAppDatabase.getDatabase(application);
        this.userDAO = db.userDAO();
        this.userPreferencesDAO = db.userPreferencesDAO();
        this.userInfoDAO = db.userInfoDAO();
        this.matchesDAO = db.matchesDAO();
        this.reportDAO = db.reportDAO();
    }

    public static DatingAppRepository getRepository(Application application) {
        if (repository != null) {
            return repository;
        }
        Future<DatingAppRepository> future =DatingAppDatabase.databaseWriteExecutor.submit(
                new Callable<DatingAppRepository>() {
                    @Override
                    public DatingAppRepository call() throws Exception {
                        return new DatingAppRepository(application);
                    }
                }
        );
        try {
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            Log.d("CST338_DATINGAPP", "Error getting DatingAppRepository.");
        }
        return null;
    }

    public LiveData<User> getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }

    public LiveData<User> getUserByUserName(String username) {
        return userDAO.getUserByUserName(username);
    }

    public LiveData<User> getNewestUser() {
        return userDAO.getNewestUser();
    }

    public void insertUser(User... user) {
        DatingAppDatabase.databaseWriteExecutor.execute(() -> {
            userDAO.insert(user);
        });
    }

    public LiveData<User> getUserByUsername(String username) {
        return userDAO.getUserByUserName(username);
    }


    public ArrayList<Report> reportedUsersLog() {
        Future<ArrayList<Report>> future = DatingAppDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<Report>>() {
                    @Override
                    public ArrayList<Report> call() throws Exception {
                        return (ArrayList<Report>) reportDAO.getAllRecords();
                    }
                }
        );
        //Will try to pull information out of our future object
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.i(ReportLogs.TAG, "Problem when getting all Report Logs in the repository");
        }
        return null;
    }

    public LiveData<UserInfo> getUserInfoByUserId(int userId) {
        return userInfoDAO.getUserInfoByUserId(userId);
    }

    //get user who liked me
    public LiveData<List<UserInfo>> getUsersWhoLikedMe(int userId) {
        return userInfoDAO.getUsersWhoLikedUser(userId);
    }

    public LiveData<UserInfo> getRandomUserInfo(String prefGender) {
        return userInfoDAO.getRandomUserInfo(prefGender);
    }

    // Adding userInfo record
    public void insertUserInfo(UserInfo... userInfo) {
        DatingAppDatabase.databaseWriteExecutor.execute(() -> {
            userInfoDAO.insert(userInfo);
        });
    }

    // Updating existing userInfo
    public void updateUserInfo(String name, int age, String gender, String bio, String photo, int userId) {
        CompletableFuture.runAsync(() -> {
            userInfoDAO.updateUserInfo(name, age, gender, bio, photo, userId);
        });
    }

    // Updating new userInfo
    public void updateUserIdOfNewRecord(int userId) {
        CompletableFuture.runAsync(() -> {
            userInfoDAO.updateUserIdOfNewRecord(userId);
        });
    }

    public LiveData<UserPreferences> currUserPreference(int loggedInUserId) {
        return userPreferencesDAO.getCurrUserPreference(loggedInUserId);
    }

    public void insertReportUser(UserInfo otherUserInfo) {
        DatingAppDatabase.databaseWriteExecutor.execute(() -> {
            Report report = new Report(otherUserInfo.getUserId(),"Reported by user", false);
            reportDAO.insert(report);
        });
    }



    public void saveMatch(int user1Id, int user2Id, boolean like) {
        DatingAppDatabase.databaseWriteExecutor.execute(() -> {
            Matches match = new Matches(user1Id, user2Id, like);
            matchesDAO.insert(match);
        });
    }

    public LiveData<List<UserInfo>> getUnmatchedUsers(int currentUserId) {
        return userInfoDAO.getUnmatchedUsers(currentUserId);
    }

    //admin ban
    public void banUser(int userId) {
        DatingAppDatabase.databaseWriteExecutor.execute(() ->
                reportDAO.updateBanStatus(true, userId)
        );
    }

    public LiveData<List<Integer>> getAllBannedUserIds() {
        return reportDAO.getAllBannedUserIds();
    }

    public void unbanUser(int userId) {
        DatingAppDatabase.databaseWriteExecutor.execute(() -> {
            Report existing = reportDAO.getReportByUserId(userId);
            if (existing != null) {
                reportDAO.updateBanStatus(false, userId);
            }
        });
    }

    public LiveData<UserPreferences> getUserPreferencesByUserId(int userId) {
        return userPreferencesDAO.getUserPreferencesByUserId(userId);
    }

    public void updateUserPreferences(int age, String gender, int userPreferencesId) {
        userPreferencesDAO.updateUserPreferences(age, gender, userPreferencesId);
    }

}
