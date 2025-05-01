package com.cst338.cst338_project02_group3.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cst338.cst338_project02_group3.database.entities.Report;
import com.cst338.cst338_project02_group3.database.entities.User;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ReportDAO {
    //TODO: Might have to fix this in the future...
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Report report);

    @Delete
    void delete(Report report);

    @Query("SELECT * FROM " + DatingAppDatabase.REPORT_TABLE)
    List<Report> getAllRecords();

    @Query("SELECT * FROM " + DatingAppDatabase.REPORT_TABLE + " WHERE userId = :userId ")
    Report getReportByUserId(int userId);

}
