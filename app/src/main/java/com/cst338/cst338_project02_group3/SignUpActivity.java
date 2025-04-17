package com.cst338.cst338_project02_group3;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.cst338.cst338_project02_group3.databinding.ActivitySignUpBinding;


public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

    }
}