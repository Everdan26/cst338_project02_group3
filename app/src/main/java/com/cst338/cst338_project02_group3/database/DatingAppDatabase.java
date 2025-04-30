package com.cst338.cst338_project02_group3.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cst338.cst338_project02_group3.MainActivity;
import com.cst338.cst338_project02_group3.database.entities.Matches;
import com.cst338.cst338_project02_group3.database.entities.Report;
import com.cst338.cst338_project02_group3.database.entities.User;
import com.cst338.cst338_project02_group3.database.entities.UserInfo;
import com.cst338.cst338_project02_group3.database.entities.UserPreferences;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, UserInfo.class, Matches.class, Report.class, UserPreferences.class}, version = 10, exportSchema = false)
public abstract class DatingAppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "DatingAppDatabase";
    public static final String USER_TABLE = "userTable";
    public static final String USERINFO_TABLE = "userInfoTable";
    public static final String REPORT_TABLE = "reportTable";
    public static final String PREFERENCES_TABLE = "preferencesTable";
    public static final String MATCHES_TABLE = "matchesTable";

    public static volatile DatingAppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DatingAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatingAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatingAppDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i(MainActivity.TAG, "DATABASE CREATED!");
            databaseWriteExecutor.execute(() -> {

                UserDAO userDAO = INSTANCE.userDAO();
                userDAO.deleteAll();

                UserInfoDAO userInfoDAO = INSTANCE.userInfoDAO();
                userInfoDAO.deleteAll();

                User admin2 = new User("admin2", "admin2");
                admin2.setAdmin(true);
                userDAO.insert(admin2);

                User testUser1 = new User("testuser1", "testuser1");

                userDAO.insert(testUser1);


                //Report User test
                ReportDAO repDAO = INSTANCE.reportDAO();
                repDAO.insert(new Report(testUser1.getId(),"Inappropriate Picture",testUser1.isBan()));

                userDAO.insert(testUser1);

                UserInfo monte = new UserInfo(2, "Monte", 21, "M",
                        "Your favorite otter mascot.",
                        "https://csumb.edu/media/csumb/section-editors/student-life/traditions/Otter-Thursday-Square.jpg");
                userInfoDAO.insert(monte);

                //Test for user matches
                    MatchesDAO matchesDAO = INSTANCE.matchesDAO();
                    Matches match = new Matches(1, 2, true);
                    matchesDAO.insert(match);

            });

        }
    };

    public abstract UserDAO userDAO();

    public abstract UserInfoDAO userInfoDAO();

    public abstract MatchesDAO matchesDAO();

    public abstract ReportDAO reportDAO();

    public abstract UserPreferencesDAO userPreferencesDAO();
}
