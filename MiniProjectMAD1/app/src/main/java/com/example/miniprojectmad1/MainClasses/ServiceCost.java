package com.example.miniprojectmad1.MainClasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.miniprojectmad1.Adapters.ServiceCostAdapter;
import com.example.miniprojectmad1.Adapters.ServiceHistoryAdapter;
import com.example.miniprojectmad1.Models.ServiceCostModel;
import com.example.miniprojectmad1.Models.ServiceHistoryModel;
import com.example.miniprojectmad1.R;
import com.example.miniprojectmad1.databinding.ActivityServiceCostBinding;
import com.example.miniprojectmad1.databinding.ActivityServiceHistoryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceCost extends AppCompatActivity {

    ActivityServiceCostBinding binding;
    FirebaseDatabase database;
    ServiceCostAdapter adapter;
    ArrayList<ServiceCostModel> list;

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

        database.getReference().child("serviceNo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ServiceCostModel user = dataSnapshot.getValue(ServiceCostModel.class);
                    list.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}