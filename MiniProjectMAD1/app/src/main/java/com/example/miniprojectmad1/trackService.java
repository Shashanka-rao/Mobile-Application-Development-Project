package com.example.miniprojectmad1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.miniprojectmad1.Models.ServiceHistoryModel;
import com.example.miniprojectmad1.databinding.ActivityTrackServiceBinding;
import com.google.firebase.database.FirebaseDatabase;

public class trackService extends AppCompatActivity {
ActivityTrackServiceBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrackServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}