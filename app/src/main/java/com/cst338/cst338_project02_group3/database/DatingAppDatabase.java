package com.cst338.cst338_project02_group3.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {}, version = 1, exportSchema = false)
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
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatingAppDatabase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .addCallback(addDefaultValues)
                        .build();
            }
        }

        return INSTANCE;
    }

    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                //TODO: add two test users here, one admin, one regular user for testing purposes.
            });

        }
    };

}
