package com.cst338.cst338_project02_group3.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cst338.cst338_project02_group3.database.entities.Report;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ReportDAO {
    //TODO: Might have to fix this in the future...
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Report report);

    @Delete
    void delete(Report report);

    @Query("DELETE FROM " + DatingAppDatabase.REPORT_TABLE + " WHERE userId = :userId")
    void deleteReport(int userId);

    @Query("SELECT * FROM " + DatingAppDatabase.REPORT_TABLE)
    List<Report> getAllRecords();

    @Query("SELECT * FROM " + DatingAppDatabase.REPORT_TABLE + " WHERE userId = :userId ")
    Report getReportByUserId(int userId);

    @Query("SELECT * FROM " + DatingAppDatabase.REPORT_TABLE + " WHERE userId = :userId ")
    boolean isReportInData(int userId);

    @Query("UPDATE " + DatingAppDatabase.REPORT_TABLE + " SET isBan = :isBan WHERE userId = :userId ")
    void updateBanStatus(boolean isBan, int userId);

    @Query("SELECT isBan FROM " + DatingAppDatabase.REPORT_TABLE + " WHERE userId = :userId ")
    boolean banStatus(int userId);


    @Query("SELECT userId FROM " + DatingAppDatabase.REPORT_TABLE + " WHERE isBan = 1")
    LiveData<List<Integer>> getAllBannedUserIds();

}
