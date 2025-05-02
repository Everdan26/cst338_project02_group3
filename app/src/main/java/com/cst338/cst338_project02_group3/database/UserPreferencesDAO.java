package com.cst338.cst338_project02_group3.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cst338.cst338_project02_group3.database.entities.UserPreferences;

@Dao
public interface UserPreferencesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserPreferences... userPreference);

    @Delete
    void delete(UserPreferences userPreference);

    @Query("SELECT * FROM " + DatingAppDatabase.PREFERENCES_TABLE + " WHERE userInfoId = :userId")
    LiveData<UserPreferences> getCurrUserPreference(int userId);

    @Query("SELECT * FROM " + DatingAppDatabase.PREFERENCES_TABLE
                + " INNER JOIN " + DatingAppDatabase.USERINFO_TABLE
                + " ON " + DatingAppDatabase.PREFERENCES_TABLE + ".userInfoId=" + DatingAppDatabase.USERINFO_TABLE + ".userInfoId"
                + " WHERE userId = :userId")
    LiveData<UserPreferences> getUserPreferencesByUserId(int userId);


    @Query("UPDATE " + DatingAppDatabase.PREFERENCES_TABLE + " SET age = :age, gender = :gender WHERE userPreferencesId = :userPreferencesId")
    void updateUserPreferences(int age, String gender, int userPreferencesId);
}
