package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.database.entities.User;
import com.cst338.cst338_project02_group3.databinding.ActivitySignUpBinding;


public class SignUpActivity extends AppCompatActivity {
    private DatingAppRepository repository;
    private ActivitySignUpBinding binding;
    private User user1;
    private boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Assigning views binding
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = DatingAppRepository.getRepository(getApplication());

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               verifyUser();
            }
        });
    }

    //This switches to different view (SignUpActivity)
    //Takes in current context which is MainActivity
    //Returns new intent having the MainActivity as the first param( The context) and switching to the view which is SignUpActivity.class)
    static Intent signUpActivityIntentFactory(Context context) {
        return new Intent(context, SignUpActivity.class);
    }

    private void verifyUser() {


        //Gets the the string for the user input
        String username = binding.signUpUsernameEditTextView.getText().toString();

        //Checks if the input is empty for username
        if(username.isEmpty()) {
            Toast.makeText(this,"Please enter a valid username", Toast.LENGTH_SHORT).show();
            return;
        }

        //Checks the User table if the username already exist
        LiveData<User> userObserver = repository.getUserByUsername(username);

        userObserver.observe(this, user -> {
            if (user != null) {
                Toast.makeText(this, "Username is already taken!", Toast.LENGTH_SHORT).show();
            } else {
                String password = binding.signUpPasswordEditTextView.getText().toString();
                if (password != null) {
                    user1 = new User(username, password);
                    repository.insertUser(user1);
                    int newUserId = user1.getId();
                    Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = SetUpProfileActivity.setUpProfileIntentFactory(getApplicationContext(), newUserId);
                    startActivity(intent);
                }
            }
        });

    }



}

