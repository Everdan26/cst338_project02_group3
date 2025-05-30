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
     * <h3>Retrieves a UserInfo record by userId</h3>
     * @param userId ID of retrieved UserInfo record
     * @return UserInfo record
     */
    @Query("SELECT * FROM " + DatingAppDatabase.USERINFO_TABLE + " WHERE userId == :userId")
    LiveData<UserInfo> getUserInfoByUserId(int userId);

    /**
     * <h3>Updates an existing UserInfo record</h3>
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

    /**
     * <h3>For unit test of SQL call of above function</h3><br>
     * Because CompletableFutures are very difficult for unit testing, this method was implemented
     * to test the same SQL query. This method returns the number of rows that were accessed and changed,
     * which should be equal to 1.
     * @param name new name
     * @param age new age
     * @param gender new gender
     * @param bio new bio
     * @param photo new photo
     * @param userId userId of record to be changed
     * @return number of rows changed (Expected: 1)
     */
    @Query("UPDATE " + DatingAppDatabase.USERINFO_TABLE + " SET name = :name, age = :age, " +
            "gender = :gender, bio = :bio, photo = :photo WHERE userId = :userId")
    int updateUserInfoTest(String name, int age, String gender, String bio, String photo, int userId);

    /**
     * <h3>Updates the userId of a newly created userInfo record</h3>
     * @param userId userId that the new record should be set to (from 0)
     */
    @Query("UPDATE " + DatingAppDatabase.USERINFO_TABLE + " SET userId = :userId WHERE " +
            "AGE = -1")
    void updateUserIdOfNewRecord(int userId);

    @Query("SELECT * FROM " + DatingAppDatabase.USERINFO_TABLE + " WHERE gender == :prefGender ORDER BY random() LIMIT 1")
    LiveData<UserInfo> getRandomUserInfo(String prefGender);

    @Query("SELECT u.*  FROM userInfoTable u " +
            "INNER JOIN matchesTable m ON u.userId = m.userId1 " +
            "WHERE m.userId2 = :userId AND m.`like` =1" )
    LiveData<List<UserInfo>> getUsersWhoLikedUser(int userId);


    //prevent already matched users from showing on find matches
    //selects users that match user's preferences for minimum age and gender
    @Query("SELECT * FROM userInfoTable " +
            "WHERE userId != :currentUserId " +
            "AND userId NOT IN (SELECT userId2 from matchesTable WHERE userId1 = :currentUserId) " +
            "AND age >= (SELECT p.age from userInfoTable u INNER JOIN preferencesTable p ON u.userInfoId = p.userInfoId INNER JOIN userTable t on t.id = u.userId AND userId = :currentUserId) " +
            "AND gender = (SELECT p.gender FROM userInfoTable u INNER JOIN preferencesTable p ON u.userInfoId = p.userInfoId INNER JOIN userTable t on t.id = userId and userId = :currentUserId)")
    LiveData<List<UserInfo>> getUnmatchedUsers(int currentUserId);

    @Query("SELECT * FROM " + DatingAppDatabase.USERINFO_TABLE + " ORDER BY userInfoId DESC LIMIT 1")
    LiveData<UserInfo> getNewestUserInfo();
}
