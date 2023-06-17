package com.example.miniprojectmad1.MainClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojectmad1.R;
import com.example.miniprojectmad1.databinding.ActivityUpdateSsmanagerBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateSSManager extends AppCompatActivity {

    ActivityUpdateSsmanagerBinding binding;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateSsmanagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reference = FirebaseDatabase.getInstance().getReference("Services").child("TrackStatus");

        Intent intent = getIntent();
        String uname = intent.getExtras().getString("username");
        String VRegNo = intent.getExtras().getString("vRegNo");
        String timeUSS = intent.getExtras().getString("time");
        String dateUSS = intent.getExtras().getString("date");

        //set data into textview
        binding.usernameUSS.setText("Username : "+uname);
        binding.regNoUSS.setText("Reg No. : "+VRegNo);
        binding.timeSlotUSS.setText("Time Slot : "+timeUSS);

        //checking
        reference.child(VRegNo).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                 if (task.isSuccessful())
                 {
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        String checkDate =String.valueOf(dataSnapshot.child("dateUSS").getValue());
                        String checkTime =String.valueOf(dataSnapshot.child("timeUSS").getValue());

                            if(timeUSS.equals(checkTime) && dateUSS.equals(checkDate)){
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
                                 binding.cbVehicleIn.performClick();
                             }
                             if(checkRoOpen.equals("COMPLETED")){
                                 binding.cbROOpen.performClick();
                             }
                             if(checkInService.equals("COMPLETED")){
                                 binding.cbInService.performClick();
                             }
                             if (checkQCheck.equals("COMPLETED")){
                                 binding.cbQualityCheck.performClick();
                             }
                             if (checkWashing.equals("COMPLETED")){
                                 binding.cbWashing.performClick();
                             }
                             if(checkReady.equals("COMPLETED")){
                                 binding.cbDOne.performClick();
                             }
                         }
                        else {

                                //set textviews to -- completed
//                                binding.tvCVehicleIn.setText("COMPLETED");
//
                                binding.cbVehicleIn.performClick();
                                binding.cbROOpen.performClick();
                                binding.cbInService.performClick();
                                binding.cbQualityCheck.performClick();
                                binding.cbWashing.performClick();
                                binding.cbDOne.performClick();

                        }

                        //to clear
//                        if (timeUSS.equals(checkTime) && dateUSS.equals(checkDate) && binding.tvCVehicleIn.equals("COMPLETED") && binding.tvCROOpen.equals("COMPLETED") && binding.tvCInService.equals("COMPLETED") && binding.tvCQualityCheck.equals("COMPLETED") && binding.tvCWashing.equals("COMPLETED") && binding.tvCDone.equals("COMPLETED")) {
//                            Toast.makeText(UpdateSSManager.this, "New Booking", Toast.LENGTH_SHORT).show();
//                            //click to uncheck
//                            binding.cbVehicleIn.performClick();
//                            binding.cbROOpen.performClick();
//                            binding.cbInService.performClick();
//                            binding.cbQualityCheck.performClick();
//                            binding.cbWashing.performClick();
//                            binding.cbDOne.performClick();
//
//                        }
                    }
                    else {
                        Toast.makeText(UpdateSSManager.this, "Visting for the first time!", Toast.LENGTH_SHORT).show();
                    }

                 }else {
                     Toast.makeText(UpdateSSManager.this, "Could not fetch data :(", Toast.LENGTH_SHORT).show();
                 }
            }
        });



        binding.clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.cbVehicleIn.performClick();
                binding.cbROOpen.performClick();
                binding.cbInService.performClick();
                binding.cbQualityCheck.performClick();
                binding.cbWashing.performClick();
                binding.cbDOne.performClick();
            }
        });


        //update button
        binding.updateUSSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vehicleInStatus =binding.tvCVehicleIn.getText().toString();
                String roOpenStatus =binding.tvCROOpen.getText().toString();
                String inServiceStatus =binding.tvCInService.getText().toString();
                String qcheckStatus =binding.tvCQualityCheck.getText().toString();
                String washingStatus =binding.tvCWashing.getText().toString();
                String vReadyStatus =binding.tvCDone.getText().toString();

                //method to update

                updateData(VRegNo,timeUSS,dateUSS,vehicleInStatus,roOpenStatus,inServiceStatus,qcheckStatus,washingStatus,vReadyStatus);
            }
        });



        //setting textviews to completed on check and in-process data,pending data

        binding.cbVehicleIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvCVehicleIn.setText("COMPLETED");
                    binding.tvCROOpen.setText("IN PROCESS");
                }
                else {
                    binding.tvCVehicleIn.setText("PENDING");
                    binding.tvCROOpen.setText("PENDING");
                }
            }
        });
        binding.cbROOpen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvCROOpen.setText("COMPLETED");
                    binding.tvCInService.setText("IN PROCESS");
                }
                else {
                    binding.tvCROOpen.setText("PENDING");
                    binding.tvCInService.setText("PENDING");
                }
            }
        });
        binding.cbInService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvCInService.setText("COMPLETED");
                    binding.tvCQualityCheck.setText("IN PROCESS");
                }
                else {
                    binding.tvCInService.setText("PENDING");
                    binding.tvCQualityCheck.setText("PENDING");
                }
            }
        });
        binding.cbQualityCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvCQualityCheck.setText("COMPLETED");
                    binding.tvCWashing.setText("IN PROCESS");
                }
                else {
                    binding.tvCQualityCheck.setText("PENDING");
                    binding.tvCWashing.setText("PENDING");
                }
            }
        });
        binding.cbWashing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvCWashing.setText("COMPLETED");
                    binding.tvCDone.setText("IN PROCESS");
                }
                else {
                    binding.tvCWashing.setText("PENDING");
                    binding.tvCDone.setText("PENDING");
                }
            }
        });
        binding.cbDOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvCDone.setText("COMPLETED");
                }
                else {
                    binding.tvCDone.setText("PENDING");
                }
            }
        });
        binding.cbVehicleIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvCVehicleIn.setText("COMPLETED");
                    binding.tvCROOpen.setText("IN PROCESS");
                }
                else {
                    binding.tvCVehicleIn.setText("PENDING");
                    binding.tvCROOpen.setText("PENDING");
                }
            }
        });

        binding.genBillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateSSManager.this, BillEntryManager.class);
                intent.putExtra("uname",uname);
                intent.putExtra("regNo",VRegNo);
                intent.putExtra("date",dateUSS);
                intent.putExtra("time",timeUSS);
                startActivity(intent);

                //send user id


            }
        });
    }

    private void updateData(String vRegNo, String timeUSS, String dateUSS, String vehicleInStatus, String roOpenStatus, String inServiceStatus, String qcheckStatus, String washingStatus, String vReadyStatus) {
        HashMap Ustatus = new HashMap();
        Ustatus.put("vRegNo",vRegNo);
        Ustatus.put("timeUSS",timeUSS);
        Ustatus.put("dateUSS",dateUSS);
        Ustatus.put("vehicleInStatus",vehicleInStatus);
        Ustatus.put("roOpenStatus",roOpenStatus);
        Ustatus.put("inServiceStatus",inServiceStatus);
        Ustatus.put("qcheckStatus",qcheckStatus);
        Ustatus.put("washingStatus",washingStatus);
        Ustatus.put("vReadyStatus",vReadyStatus);


        reference.child(vRegNo).updateChildren(Ustatus).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(UpdateSSManager.this, "Update Succesful :)", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UpdateSSManager.this, "Unable to update :(", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }


}