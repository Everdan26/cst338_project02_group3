package com.cst338.cst338_project02_group3.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.cst338.cst338_project02_group3.database.DatingAppDatabase;

@Entity(tableName = DatingAppDatabase.USERINFO_TABLE)
public class UserInfo {
    @PrimaryKey(autoGenerate = true)
    private int id;

//    TODO: comment this back in when User/UserDAO are merged...
//    @ForeignKey()
//    private int userId;

    private int age;
    private String gender;
    private String location;
    private String interest;
    private String bio;
    private String photo;

    public UserInfo(int age, String gender, String location, String interest, String bio, String photo) {
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.interest = interest;
        this.bio = bio;
        this.photo = photo;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    //TODO: add equals() and hashCode() when we can implement user keys
}
