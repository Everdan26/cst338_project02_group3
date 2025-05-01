package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.databinding.ActivityEditPreferencesBinding;

public class EditPreferencesActivity extends AppCompatActivity {

    private static final String EDIT_PREFERENCES_USER_ID = "com.cst338.project02_group3.EDIT_PREFERENCES_USER_ID";
    ActivityEditPreferencesBinding binding;

    private DatingAppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditPreferencesBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        repository = DatingAppRepository.getRepository(getApplication());

        //TODO: I think this activity just needs to save whatever's in the EditTexts when the user hits the save changes button.

        binding.editPreferencesSaveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Hook this up (See above comment)
            }
        });

        binding.editPreferencesBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Hook this up (should go back to the last page without making changes)
            }
        });

    }

    static Intent editPreferencesIntentFactory (Context context, int userId) {
        Intent intent = new Intent(context, EditPreferencesActivity.class);
        intent.putExtra(EDIT_PREFERENCES_USER_ID, userId);
        return intent;
    }
}