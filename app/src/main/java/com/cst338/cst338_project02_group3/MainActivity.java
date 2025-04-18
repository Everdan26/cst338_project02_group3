package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cst338.cst338_project02_group3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private int loggedInUserId = -1;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        if(loggedInUserId == -1) {
//            Intent intent = SignUpActivity.signUpActivityIntentFactory(getApplicationContext());
//            startActivity(intent);
//        }

        binding.mainLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Implement this.
            }
        });

        binding.mainSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SignUpActivity.signUpActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

    }

    static Intent mainActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
}