package com.cst338.cst338_project02_group3.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cst338.cst338_project02_group3.database.DatingAppDatabase;

import java.util.Objects;

@Entity(tableName = DatingAppDatabase.PREFERENCES_TABLE)
public class UserPreferences {

    @PrimaryKey(autoGenerate = true)
    private int preferencesId;

    private int userInfoId;
    private int age;
    private String gender;
    private String interest;

    public UserPreferences(int userInfoId, int age, String gender, String interest) {
        this.userInfoId = userInfoId;
        this.age = age;
        this.gender = gender;
        this.interest = interest;
    }

    public void setPreferencesId(int preferencesId) {
        this.preferencesId = preferencesId;
    }

    public int getPreferencesId() {
        return preferencesId;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getInterest() {
        return interest;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserPreferences that = (UserPreferences) o;
        return preferencesId == that.preferencesId && userInfoId == that.userInfoId && age == that.age && Objects.equals(gender, that.gender) && Objects.equals(interest, that.interest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preferencesId, userInfoId, age, gender, interest);
    }
}
