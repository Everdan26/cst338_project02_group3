package com.cst338.cst338_project02_group3.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.cst338.cst338_project02_group3.database.entities.User;

import com.cst338.cst338_project02_group3.database.entities.User;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DatingAppRepository {

    private final UserDAO userDAO;
    private final UserInfoDAO userInfoDAO;
    private final UserPreferencesDAO userPreferencesDAO;
    private final MatchesDAO matchesDAO;
    private final ReportDAO reportDAO;

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

    public void insertUser(User... user) {
        DatingAppDatabase.databaseWriteExecutor.execute(() -> {
            userDAO.insert(user);
        });
    }
}
