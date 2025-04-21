package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.cst338.cst338_project02_group3.databinding.ActivityViewMatchesBinding;

public class ViewMatchesActivity extends AppCompatActivity {

    private ActivityViewMatchesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewMatchesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    static Intent viewMatchesIntentFactory(Context context) {
        return new Intent(context, ViewMatchesActivity.class);
    }
}