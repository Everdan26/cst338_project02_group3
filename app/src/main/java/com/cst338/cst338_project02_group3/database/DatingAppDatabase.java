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

@Database(entities = {User.class, UserInfo.class, Matches.class, Report.class, UserPreferences.class}, version = 17, exportSchema = false)
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

                User testUser2 = new User("testuser2", "testuser2");
                userDAO.insert(testUser2);

                User testUser3 = new User("testuser3", "testuser3");
                userDAO.insert(testUser3);

                User testUser4 = new User("testuser4", "testuser4");
                userDAO.insert(testUser4);

                User monteMascot = new User("monte", "monte");
                userDAO.insert(monteMascot);

                User boyTest = new User("boy", "boy");
                userDAO.insert(boyTest);

                User girlTest = new User("girl", "girl");
                userDAO.insert(girlTest);

                User helmet = new User("helmet", "helmet");
                userDAO.insert(helmet);

                User buttercup = new User("buttercup", "buttercup");
                userDAO.insert(buttercup);

                User westley = new User("westley", "westley");
                userDAO.insert(westley);

                User inigo = new User("inigo", "inigo");
                userDAO.insert(inigo);

                //Report User test
                ReportDAO repDAO = INSTANCE.reportDAO();
                repDAO.insert(new Report(testUser1.getId(),"Inappropriate Picture",testUser1.isBan()));


                //User Info
                UserInfo monte = new UserInfo(6, "Monte", 21, "M",
                        "Your favorite otter mascot.",
                        "https://csumb.edu/media/csumb/section-editors/student-life/traditions/Otter-Thursday-Square.jpg");
                userInfoDAO.insert(monte);

                UserInfo boy = new UserInfo(7, "Guy", 22, "M",
                        "Your favorite dude.",
                        "https://csumb.edu/media/csumb/section-editors/student-life/traditions/Otter-Thursday-Square.jpg");
                userInfoDAO.insert(boy);

                UserInfo girl = new UserInfo(8, "Gal", 20, "F",
                        "Your favorite girl.",
                        "https://csumb.edu/media/csumb/section-editors/student-life/traditions/Otter-Thursday-Square.jpg");
                userInfoDAO.insert(girl);

                UserInfo testUserNumberThree = new UserInfo(3,"testuser3", 23, "F",
                        "Slaaaaay Queen",
                        "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi.pinimg.com%2Foriginals%2F16%2Fb6%2Fdf%2F16b6df52a0d8f83a8fd717587dde86eb.jpg&f=1&nofb=1&ipt=60120f3d6c380f67c36e7a44ada1a1f6eabfcc42153c592306fac928be991966");
                userInfoDAO.insert(testUserNumberThree);

                UserInfo darkHelmet = new UserInfo(9, "Helmet", 34, "M",
                            "I am your father's brother's nephew's cousin's former roommate.",
                            "https://static.wikia.nocookie.net/spaceballs/images/2/25/Dark_Helmet.jpg/");
                userInfoDAO.insert(darkHelmet);

                UserInfo princessButtercup = new UserInfo(10, "Buttercup", 21, "F",
                        "Westley, What about the R.O.U.S.'s?",
                        "https://static.wikia.nocookie.net/princessbride/images/b/b9/Princess_Buttercup.png/");
                userInfoDAO.insert(princessButtercup);

                UserInfo manInBlack = new UserInfo(11, "Westley", 25, "M",
                        "Have you considered piracy? You'd make a wonderful Dread Pirate Roberts.",
                                "https://images.hobbydb.com/processed_uploads/subject_photo/subject_photo/image/37341/1519151546-3600-7296/Westley-Buttercup-in-The-Princess-Bride-movie-couples-19610752-500-281_large.jpg");
                userInfoDAO.insert(manInBlack);

                UserInfo inigoMontoya = new UserInfo(12, "Inigo", 35, "M",
                            "Hello. My name is Inigo Montoya. You killed my father. Prepare to die.",
                                "https://www.dictionary.com/e/wp-content/uploads/2018/03/Inigo-Montoya1.jpg");
                userInfoDAO.insert(inigoMontoya);


                //Test for user matches
                    MatchesDAO matchesDAO = INSTANCE.matchesDAO();
                    Matches match = new Matches(2, 6, true);
                    matchesDAO.insert(match);

                    Matches match2 = new Matches(6, 2, true);
                    matchesDAO.insert(match2);

                    Matches match3 = new Matches(98, 1, true);
                    matchesDAO.insert(match3);


                //UserPreference test
                UserPreferencesDAO userPreferencesDAO = INSTANCE.userPreferencesDAO();
                UserPreferences testOne = new UserPreferences(2,18,"M");
                UserPreferences testTwo = new UserPreferences(3,18,"F");
                UserPreferences adminTwo = new UserPreferences(1,18,"M");
                UserPreferences buttercupPref = new UserPreferences(6, 21, "M");
                UserPreferences westleyPref = new UserPreferences(7, 21, "F");


                userPreferencesDAO.insert(testOne);
                userPreferencesDAO.insert(testTwo);
                userPreferencesDAO.insert(adminTwo);
                userPreferencesDAO.insert(buttercupPref);
                userPreferencesDAO.insert(westleyPref);

            });

        }
    };

    public abstract UserDAO userDAO();

    public abstract UserInfoDAO userInfoDAO();

    public abstract MatchesDAO matchesDAO();

    public abstract ReportDAO reportDAO();

    public abstract UserPreferencesDAO userPreferencesDAO();
}
