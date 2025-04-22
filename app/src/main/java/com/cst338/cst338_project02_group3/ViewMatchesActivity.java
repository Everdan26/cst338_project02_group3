package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cst338.cst338_project02_group3.databinding.ActivityViewMatchesBinding;

public class ViewMatchesActivity extends AppCompatActivity {

    private ActivityViewMatchesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewMatchesBinding.inflate(getLayoutInflater());

        setContentView(R.layout.activity_view_matches);
    }

    static Intent viewMatchesIntentFactory(Context context) {
        return new Intent(context, ViewMatchesActivity.class);
    }
}