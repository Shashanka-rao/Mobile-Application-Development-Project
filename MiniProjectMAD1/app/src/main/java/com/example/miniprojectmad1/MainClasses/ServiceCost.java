package com.example.miniprojectmad1.MainClasses;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.miniprojectmad1.Adapters.ServiceCostAdapter;
import com.example.miniprojectmad1.Models.ServiceCostModel;
import com.example.miniprojectmad1.databinding.ActivityServiceCostBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceCost extends AppCompatActivity {

    ActivityServiceCostBinding binding; 
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database;

    ServiceCostAdapter adapter;
    ArrayList<ServiceCostModel> list;
    String regNoD,vRegNum;

    String regNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityServiceCostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        binding.serviceCRecycleV.setHasFixedSize(true);
        binding.serviceCRecycleV.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<>();
        adapter = new ServiceCostAdapter(this,list);
        binding.serviceCRecycleV.setAdapter(adapter);

        //fetch from home
        Intent intent =getIntent();
        regNumber = intent.getExtras().getString("regNo");



        //database
        DatabaseReference referenceBill =database.getReference().child("Services").child("Bills");

        DatabaseReference databaseRef =database.getReference("Users").child("UserData");


        //check condition
//        String uidU = auth.getCurrentUser().getUid(); // Assuming you want to retrieve the uid of the current user

//        databaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
//                    String uid = childSnapshot.child("userId").getValue(String.class);
//                    if (uid != null && uid.equals(uidU)) {
//                        regNoD = childSnapshot.child("vRegNo").getValue(String.class);
//
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.e("TAG", "Failed to read value.", error.toException());
//            }
//        });

        //true so proceed
        //checking each user's data

        referenceBill.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot childSnapshot : snapshot.getChildren()){
                    vRegNum = childSnapshot.child("vRegNo").getValue(String.class);
                    //here
//                    if(vRegNum.equals(regNumber)){

                        database.getReference().child("Services").child("Bills").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                list.clear();
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                                        ServiceCostModel user = dataSnapshot.getValue(ServiceCostModel.class);
                                        String regisNo = user.getvRegNo().toString();
                                    if(regisNo.equals(regNumber)) {
                                        list.add(user);
                                    }
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
//                    }
//                    else {
//                        Toast.makeText(ServiceCost.this, "Book a Service for your vehicle!!", Toast.LENGTH_SHORT).show();
//                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //condition

//        if(regNoD.equals(regNumber)){
//            referenceBill.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    list.clear();
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                        ServiceCostModel user = dataSnapshot.getValue(ServiceCostModel.class);
//                        list.add(user);
//                    }
//                    adapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }
//        else {
//            Toast.makeText(ServiceCost.this, "Book a Service for your vehicle!!", Toast.LENGTH_SHORT).show();
//        }


//        database.getReference().child("Services").child("Bills").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    ServiceCostModel user = dataSnapshot.getValue(ServiceCostModel.class);
//                    list.add(user);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}