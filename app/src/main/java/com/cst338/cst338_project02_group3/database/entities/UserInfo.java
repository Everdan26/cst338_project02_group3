package com.cst338.cst338_project02_group3.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.cst338.cst338_project02_group3.database.DatingAppDatabase;

import java.util.Objects;

@Entity(tableName = DatingAppDatabase.USERINFO_TABLE)
public class UserInfo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;

    private int age;
    private String gender;
    private String interest;
    private String bio;
    private String photo;

    public UserInfo(int userId, int age, String gender, String interest, String bio, String photo) {
        this.userId = userId;
        this.age = age;
        this.gender = gender;
        this.interest = interest;
        this.bio = bio;
        this.photo = photo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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


    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
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
        return id == userInfo.id && userId == userInfo.userId && age == userInfo.age && Objects.equals(gender, userInfo.gender)  && Objects.equals(interest, userInfo.interest) && Objects.equals(bio, userInfo.bio) && Objects.equals(photo, userInfo.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, age, gender, interest, bio, photo);
    }
}
