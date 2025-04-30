package com.cst338.cst338_project02_group3.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cst338.cst338_project02_group3.database.entities.UserInfo;

import java.util.List;

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
    @Query("SELECT * FROM " + DatingAppDatabase.USERINFO_TABLE + " WHERE userInfoId == :userId")
    LiveData<UserInfo> getUserInfoByUserId(int userId);

    @Query("SELECT u.*  FROM userInfoTable u " +
            "INNER JOIN matchesTable m ON u.userId = m.userId1 " +
            "WHERE m.userId2 = :userId AND m.`like` =1" )
    LiveData<List<UserInfo>> getUsersWhoLikedUser(int userId);
}
