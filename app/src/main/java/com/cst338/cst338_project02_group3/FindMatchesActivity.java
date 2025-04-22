package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cst338.cst338_project02_group3.databinding.ActivityFindMatchesBinding;

public class FindMatchesActivity extends AppCompatActivity {

    private ActivityFindMatchesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindMatchesBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }

    static Intent findMatchesIntentFactory(Context context) {
        return new Intent(context, FindMatchesActivity.class);
    }
}