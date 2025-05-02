package com.cst338.cst338_project02_group3.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cst338.cst338_project02_group3.database.DatingAppDatabase;

import java.util.Objects;

@Entity(tableName = DatingAppDatabase.REPORT_TABLE)
public class Report {
    //TODO: Might have to double check all these things 
    @PrimaryKey(autoGenerate = true)
    private int reportId;
    private int userId;
    private String reason;
    private boolean isBan;

    public Report(int userId, String reason, boolean isBan) {
        this.userId = userId;
        this.reason = reason;
        this.isBan = isBan;
    }

    @NonNull
    @Override
    public String toString() {
        return  "Report Id: " + reportId + '\n' + "userId: " + userId + '\n' + "Reason = " + reason + '\n' + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n";
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return reportId == report.reportId && userId == report.userId && isBan == report.isBan && Objects.equals(reason, report.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, userId, reason, isBan);
    }

    public boolean isBan() {
        return isBan;
    }

    public void setBan(boolean ban) {
        isBan = ban;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
