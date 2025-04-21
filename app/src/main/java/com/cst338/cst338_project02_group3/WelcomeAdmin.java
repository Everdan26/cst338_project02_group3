package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cst338.cst338_project02_group3.database.entities.User;

public class WelcomeAdmin extends AppCompatActivity {

    private static final String WELCOME_ADMIN_USER_ID = "com.cst338.project02_group3.WELCOME_ADMIN_USER_ID";

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    static Intent welcomeAdminIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, WelcomeAdmin.class);
        intent.putExtra(WELCOME_ADMIN_USER_ID, userId);
        return intent;
    }
}