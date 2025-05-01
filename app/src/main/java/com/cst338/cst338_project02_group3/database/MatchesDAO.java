package com.cst338.cst338_project02_group3.database;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.LiveData;
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

    @Query("SELECT * FROM matchesTable WHERE userId2 = :userId AND `like` = 1")
    LiveData<List<Matches>> getUsersWhoLikedMe(int userId);

    @Query("SELECT * FROM matchesTable WHERE userId1 = :userId1 AND userId2 = :userId2")
    Matches getMatchByUserIds(int userId1, int userId2);

}
