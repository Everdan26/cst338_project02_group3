package com.cst338.cst338_project02_group3.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cst338.cst338_project02_group3.database.entities.Matches;
import com.cst338.cst338_project02_group3.database.entities.Report;
import com.cst338.cst338_project02_group3.database.entities.User;
import com.cst338.cst338_project02_group3.database.entities.UserInfo;
import com.cst338.cst338_project02_group3.database.entities.UserPreferences;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, UserInfo.class, Matches.class, Report.class, UserPreferences.class}, version = 4, exportSchema = false)
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
            databaseWriteExecutor.execute(() -> {
                UserDAO dao = INSTANCE.userDAO();
                dao.deleteAll();

                User admin2 = new User("admin2", "admin2");
                admin2.setAdmin(true);
                dao.insert(admin2);

                User testUser1 = new User("testuser1", "testuser1");
                dao.insert(testUser1);
            });

        }
    };

    public abstract UserDAO userDAO();

    public abstract UserInfoDAO userInfoDAO();

    public abstract MatchesDAO matchesDAO();

    public abstract ReportDAO reportDAO();

    public abstract UserPreferencesDAO userPreferencesDAO();
}
