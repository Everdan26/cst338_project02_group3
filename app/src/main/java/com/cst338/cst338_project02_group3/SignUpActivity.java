package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.cst338.cst338_project02_group3.databinding.ActivitySignUpBinding;


public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting the binding variable to activity_sign_up.xml essentially
        //To be able to edit things
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this,"Hooray! Successful creating an account", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //This switches to different view (SignUpActivity)
    //Takes in current context which is MainActivity
    //Returns new intent having the MainActivity as the first param( The context) and switching to the view which is SignUpActivity.class)
    static Intent signUpActivityIntentFactory(Context context) {
        return new Intent(context, SignUpActivity.class);
    }



}

