package com.cst338.cst338_project02_group3.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cst338.cst338_project02_group3.database.entities.User;

import java.util.List;

@Dao
public interface UserDAO {

    //Insert into users into the userTable
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

    //Deletes a user object
    @Delete
    void delete(User user);

    //Just gets all the record of Users in a List
    @Query("SELECT * FROM " + DatingAppDatabase.USER_TABLE)
    LiveData<List<User>> getAllRecords();

    @Query("SELECT * FROM " + DatingAppDatabase.USER_TABLE)
    List<User> getAllRecordsTest();

    @Query("DELETE FROM " + DatingAppDatabase.USER_TABLE)
    void deleteAll();


    @Query("SELECT * FROM " + DatingAppDatabase.USER_TABLE + " WHERE id == :userId")
    LiveData<User> getUserByUserId(int userId);

    @Query("SELECT * FROM " + DatingAppDatabase.USER_TABLE + " WHERE id == :userId")
    User getUserByUserIdTest(int userId);

    @Query("SELECT * FROM " + DatingAppDatabase.USER_TABLE + " WHERE username == :username")
    LiveData<User> getUserByUserName(String username);

    @Query("SELECT * FROM " + DatingAppDatabase.USER_TABLE + " WHERE username == :username")
    User getUserByUsernameTest(String username);

    @Query("DELETE FROM " + DatingAppDatabase.USER_TABLE + " WHERE username == :username")
    void deleteUserByUsername(String username);

    @Query("UPDATE " + DatingAppDatabase.USER_TABLE + " SET password = :password WHERE username == :username")
    void updatePassword(String username, String password);

    @Query("SELECT * FROM " + DatingAppDatabase.USER_TABLE + " ORDER BY id DESC LIMIT 1")
    LiveData<User> getNewestUser();
}
