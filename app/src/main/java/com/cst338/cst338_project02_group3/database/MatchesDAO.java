package com.cst338.cst338_project02_group3.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cst338.cst338_project02_group3.database.entities.Matches;

import java.util.List;

@Dao
public interface MatchesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Matches match);

    @Delete
    void delete(Matches match);

    @Query("SELECT * FROM " + DatingAppDatabase.MATCHES_TABLE)
    List<Matches> getAllRecords();
}
