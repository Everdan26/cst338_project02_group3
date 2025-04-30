package com.cst338.cst338_project02_group3.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cst338.cst338_project02_group3.database.DatingAppDatabase;

import java.util.Objects;

@Entity(tableName = DatingAppDatabase.PREFERENCES_TABLE)
public class UserPreferences {

    @PrimaryKey(autoGenerate = true)
    private int userPreferencesId;

    private int userInfoId;
    private int age;
    private String gender;

    public UserPreferences(int userInfoId, int age, String gender) {
        this.userInfoId = userInfoId;
        this.age = age;
        this.gender = gender;
    }

    public void setUserPreferencesId(int userPreferencesId) {
        this.userPreferencesId = userPreferencesId;
    }

    public int getUserPreferencesId() {
        return userPreferencesId;
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

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserPreferences that = (UserPreferences) o;
        return userPreferencesId == that.userPreferencesId && userInfoId == that.userInfoId && age == that.age && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userPreferencesId, userInfoId, age, gender);
    }
}
