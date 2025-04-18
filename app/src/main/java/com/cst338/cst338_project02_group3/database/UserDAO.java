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
    List<User> getAllRecords();

    @Query("DELETE FROM " + DatingAppDatabase.USER_TABLE)
    void deleteAll();


    @Query("SELECT * FROM " + DatingAppDatabase.USER_TABLE + " WHERE id == :userId")
    LiveData<User> getUserByUserId(int userId);
}
