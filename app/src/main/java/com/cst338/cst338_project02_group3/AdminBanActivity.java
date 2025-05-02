package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.databinding.ActivityAdminBanBinding;

public class AdminBanActivity extends AppCompatActivity {
    private ActivityAdminBanBinding binding;
    private int loggedInUserId;
    private DatingAppRepository repository;
    private static final String USER_ADMIN = "com.cst338.project02_group3.USER_ADMIN";

    public static Intent adminBanActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, AdminBanActivity.class);
        intent.putExtra(USER_ADMIN,userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loggedInUserId = getIntent().getIntExtra(USER_ADMIN, -1);

        repository = DatingAppRepository.getRepository(getApplication());

        binding.banLogsDisplay.setMovementMethod(new ScrollingMovementMethod());

        //bannedUsers();

        //main menu
        binding.mainMenuAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WelcomeUser.welcomeUserIntentFactory(getApplicationContext(),loggedInUserId);
                startActivity(intent);
            }
        });

        }

//    private void bannedUsers() {
//        ArrayList<Ban>
//    });
//    }
}