package com.cst338.cst338_project02_group3.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cst338.cst338_project02_group3.database.DatingAppDatabase;

import java.util.Objects;

@Entity(tableName = DatingAppDatabase.USERINFO_TABLE)
public class UserInfo {
    @PrimaryKey(autoGenerate = true)
    private int userInfoId;
    private int userId;

    private int age;
    private String gender;
    private String bio;
    private String photo;

    public UserInfo(int userId, int age, String gender, String bio, String photo) {
        this.userId = userId;
        this.age = age;
        this.gender = gender;
        this.bio = bio;
        this.photo = photo;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return userInfoId == userInfo.userInfoId && userId == userInfo.userId && age == userInfo.age && Objects.equals(gender, userInfo.gender) && Objects.equals(bio, userInfo.bio) && Objects.equals(photo, userInfo.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userInfoId, userId, age, gender, bio, photo);
    }
}
