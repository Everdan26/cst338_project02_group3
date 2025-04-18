package com.cst338.cst338_project02_group3.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface PreferencesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PreferencesDAO... preference);

    @Delete
    void delete(PreferencesDAO preference);
}
