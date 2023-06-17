package com.example.miniprojectmad1.MainClasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import com.example.miniprojectmad1.Models.ServiceHistoryModel;
import com.example.miniprojectmad1.databinding.ActivityTrackServiceBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class trackService extends AppCompatActivity {
ActivityTrackServiceBinding binding;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrackServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reference = FirebaseDatabase.getInstance().getReference("Services").child("TrackStatus");

        Intent intent = getIntent();
        String VRegNo = intent.getExtras().getString("vehicleRegNo");


        reference.child(VRegNo).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();

                        String checkReady = String.valueOf(dataSnapshot.child("vReadyStatus").getValue());
                        String checkRoOpen = String.valueOf(dataSnapshot.child("roOpenStatus").getValue());
                        String checkWashing = String.valueOf(dataSnapshot.child("washingStatus").getValue());
                        String checkInService = String.valueOf(dataSnapshot.child("inServiceStatus").getValue());
                        String checkQCheck = String.valueOf(dataSnapshot.child("qcheckStatus").getValue());
                        String checkVehicleIn = String.valueOf(dataSnapshot.child("vehicleInStatus").getValue());

                        // changes the status based on the data in firebase
                        // Note : the order should not change
                        // if it changes then the result will have overlap of data

                        if (checkVehicleIn.equals("COMPLETED")){
                            binding.cbVehicleInU.performClick();
                        }
                        if(checkRoOpen.equals("COMPLETED")){
                            binding.cbROOpenU.performClick();
                        }
                        if(checkInService.equals("COMPLETED")){
                            binding.cbInServiceU.performClick();
                        }
                        if (checkQCheck.equals("COMPLETED")){
                            binding.cbQualityCheckU.performClick();
                        }
                        if (checkWashing.equals("COMPLETED")){
                            binding.cbWashingU.performClick();
                        }
                        if(checkReady.equals("COMPLETED")){
                            binding.cbDOneU.performClick();
                        }
                    }
                    else {Toast.makeText(trackService.this, "Your vehicle yet to reach the service center", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(trackService.this, "Could not fetch data :(", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //setting textviews to completed on check and in-process data,pending data

        binding.cbVehicleInU.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvCVehicleInU.setText("COMPLETED");
                    binding.tvCROOpenU.setText("IN PROCESS");
                }
                else {
                    binding.tvCVehicleInU.setText("PENDING");
                    binding.tvCROOpenU.setText("PENDING");
                }
            }
        });
        binding.cbROOpenU.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvCROOpenU.setText("COMPLETED");
                    binding.tvCInServiceU.setText("IN PROCESS");
                }
                else {
                    binding.tvCROOpenU.setText("PENDING");
                    binding.tvCInServiceU.setText("PENDING");
                }
            }
        });
        binding.cbInServiceU.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvCInServiceU.setText("COMPLETED");
                    binding.tvCQualityCheckU.setText("IN PROCESS");
                }
                else {
                    binding.tvCInServiceU.setText("PENDING");
                    binding.tvCQualityCheckU.setText("PENDING");
                }
            }
        });
        binding.cbQualityCheckU.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvCQualityCheckU.setText("COMPLETED");
                    binding.tvCWashingU.setText("IN PROCESS");
                }
                else {
                    binding.tvCQualityCheckU.setText("PENDING");
                    binding.tvCWashingU.setText("PENDING");
                }
            }
        });
        binding.cbWashingU.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvCWashingU.setText("COMPLETED");
                    binding.tvCDoneU.setText("IN PROCESS");
                }
                else {
                    binding.tvCWashingU.setText("PENDING");
                    binding.tvCDoneU.setText("PENDING");
                }
            }
        });
        binding.cbDOneU.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvCDoneU.setText("COMPLETED");
                }
                else {
                    binding.tvCDoneU.setText("PENDING");
                }
            }
        });
        binding.cbVehicleInU.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvCVehicleInU.setText("COMPLETED");
                    binding.tvCROOpenU.setText("IN PROCESS");
                }
                else {
                    binding.tvCVehicleInU.setText("PENDING");
                    binding.tvCROOpenU.setText("PENDING");
                }
            }
        });
    }
}