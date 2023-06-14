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
EditText kmsIn,dateIn,billIn;
Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrackServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceHistoryModel model = new ServiceHistoryModel();
                model.setKmsIn(binding.kmsIn.getText().toString());
                model.setBillIn(binding.billIn.getText().toString());
                model.setDateIn(binding.dateIn.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("serviceNo").push().setValue(model);
                Toast.makeText(trackService.this, "Saved Succesfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}