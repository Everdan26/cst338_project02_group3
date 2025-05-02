package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.databinding.ActivityAdminBanBinding;

public class AdminBanActivity extends AppCompatActivity {
    private ActivityAdminBanBinding binding;
    private int loggedInUserId;
    private DatingAppRepository repository;
    private static final String USER_ADMIN = "com.cst338.project02_group3.USER_ADMIN";

    public static Intent adminBanActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, AdminBanActivity.class);
        intent.putExtra(USER_ADMIN, userId);
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

        binding.banUserSubmitButton.setOnClickListener(v -> {
            String input = binding.editTextUserIdToBan.getText().toString().trim();
            if (!input.isEmpty()) {
                try {
                    int userId = Integer.parseInt(input);
                    repository.banUser(userId);
                    Toast.makeText(this, "User " + userId + " banned.", Toast.LENGTH_SHORT).show();
                    binding.editTextUserIdToBan.setText(""); // clear input
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid user ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        repository.getAllBannedUserIds().observe(this, bannedUserIds -> {
            if (bannedUserIds == null || bannedUserIds.isEmpty()) {
                binding.banLogsDisplay.setText(getString(R.string.banned_users_text) + " None");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int id : bannedUserIds) {
                    sb.append(id).append(", ");
                }
                sb.setLength(sb.length() - 2);
                binding.banLogsDisplay.setText(getString(R.string.banned_users_text) + " " + sb.toString());
            }
        });


        //bannedUsers();

        //main menu
        binding.mainMenuAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WelcomeUser.welcomeUserIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });

    }
}