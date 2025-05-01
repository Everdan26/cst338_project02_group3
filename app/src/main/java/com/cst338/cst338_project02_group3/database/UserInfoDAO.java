package com.cst338.cst338_project02_group3.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cst338.cst338_project02_group3.database.entities.UserInfo;

@Dao
public interface UserInfoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserInfo... userInfo);

    @Delete
    void delete(UserInfo userInfo);

    @Query("DELETE FROM " + DatingAppDatabase.USERINFO_TABLE)
    void deleteAll();

    /**
     * <h2>Retrieves a UserInfo record by userId</h2>
     * @param userId ID of retrieved UserInfo record
     * @return UserInfo record
     */
    @Query("SELECT * FROM " + DatingAppDatabase.USERINFO_TABLE + " WHERE userId == :userId")
    LiveData<UserInfo> getUserInfoByUserId(int userId);

    /**
     * <h2>Updates a UserInfo record</h2>
     * @param name new name
     * @param age new age
     * @param gender new gender
     * @param bio new bio
     * @param photo new photo
     * @param userId userId of record to be changed
     */
    @Query("UPDATE " + DatingAppDatabase.USERINFO_TABLE + " SET name = :name, age = :age, " +
            "gender = :gender, bio = :bio, photo = :photo WHERE userId = :userId")
    void updateUserInfo(String name, int age, String gender, String bio, String photo, int userId);
}
