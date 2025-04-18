package com.cst338.cst338_project02_group3.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.cst338.cst338_project02_group3.database.entities.UserPreferences;

@Dao
public interface UserPreferencesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserPreferences... userPreference);

    @Delete
    void delete(UserPreferences userPreference);
}
