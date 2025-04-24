package com.cst338.cst338_project02_group3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cst338.cst338_project02_group3.database.DatingAppRepository;
import com.cst338.cst338_project02_group3.database.entities.Report;
import com.cst338.cst338_project02_group3.databinding.ActivityReportLogsBinding;

import java.util.ArrayList;


public class ReportLogs extends AppCompatActivity {
    private static final String USER_ADMIN = "com.cst338.project02_group3.USER_ADMIN";
    public static final String TAG = "REPORT";
    private ActivityReportLogsBinding binding;
    private DatingAppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportLogsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = DatingAppRepository.getRepository(getApplication());

        //Makes the Users Reported Log scrollable
        binding.reportLogsDisplay.setMovementMethod(new ScrollingMovementMethod());

        reportedUsers();

        binding.updateReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportedUsers();
            }
        });

        binding.mainMenuAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WelcomeAdmin.welcomeAdminIntentFactory(getApplicationContext(),getIntent().getIntExtra(USER_ADMIN, -1));
                startActivity(intent);
            }
        });

    }

    static Intent reportLogsIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, ReportLogs.class);
        intent.putExtra(USER_ADMIN, userId);
        return intent;
    }

    private void reportedUsers() {
        ArrayList<Report> reportLogs = repository.reportedUsersLog();

        if(reportLogs.isEmpty()) {
            binding.reportLogsDisplay.setText(R.string.no_users_reported_at_the_moment);
        }

        StringBuilder sb = new StringBuilder();
        for(Report log: reportLogs) {
            sb.append(log);
        }
        binding.reportLogsDisplay.setText(sb);

    }

}