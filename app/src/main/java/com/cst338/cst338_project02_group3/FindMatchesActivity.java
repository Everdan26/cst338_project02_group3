package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cst338.cst338_project02_group3.databinding.ActivityFindMatchesBinding;

public class FindMatchesActivity extends AppCompatActivity {

    private ActivityFindMatchesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindMatchesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    static Intent findMatchesIntentFactory(Context context) {
        return new Intent(context, FindMatchesActivity.class);
    }
}