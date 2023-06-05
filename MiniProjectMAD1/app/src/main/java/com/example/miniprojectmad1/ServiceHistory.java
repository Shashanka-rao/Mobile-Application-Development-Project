package com.example.miniprojectmad1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.miniprojectmad1.databinding.ActivityServiceHistoryBinding;

public class ServiceHistory extends AppCompatActivity {

    ActivityServiceHistoryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backButtonBSSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceHistory.this, Home.class);
                startActivity(intent);
            }
        });
    }
}