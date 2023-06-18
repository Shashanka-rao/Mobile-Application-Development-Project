package com.example.miniprojectmad1.MainClasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.miniprojectmad1.Models.ServiceCostModel;
import com.example.miniprojectmad1.R;
import com.example.miniprojectmad1.Adapters.ServiceHistoryAdapter;
import com.example.miniprojectmad1.Models.ServiceHistoryModel;
import com.example.miniprojectmad1.databinding.ActivityServiceHistoryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceHistory extends AppCompatActivity {

    ActivityServiceHistoryBinding binding;
    ImageButton backButtonBSSH;
    FirebaseDatabase database;
    ServiceHistoryAdapter adapter;
    ArrayList<ServiceHistoryModel> list1;
    String regNumber,vRegNum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityServiceHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        binding.rvServiceHistory.setHasFixedSize(true);
        binding.rvServiceHistory.setLayoutManager(new LinearLayoutManager(this));

        list1 = new ArrayList<>();
        adapter = new ServiceHistoryAdapter(this,list1);
        binding.rvServiceHistory.setAdapter(adapter);

        //fetch from home
        Intent intent = getIntent();
        regNumber = intent.getExtras().getString("vehicleRegNo");



        //database
        DatabaseReference referenceBill =database.getReference().child("Services").child("Bills");

        DatabaseReference databaseRef =database.getReference("Users").child("UserData");

        //condition same as service cost
        referenceBill.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot childSnapshot : snapshot.getChildren()){
                    vRegNum = childSnapshot.child("vRegNo").getValue(String.class);
                    //here
                    database.getReference().child("Services").child("Bills").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            list1.clear();
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                                ServiceHistoryModel user = dataSnapshot.getValue(ServiceHistoryModel.class);
                                String regisNo = user.getvRegNo().toString();
                                if(regisNo.equals(regNumber)) {
                                    list1.add(user);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //old



//        referenceBill.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list1.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    ServiceHistoryModel user = dataSnapshot.getValue(ServiceHistoryModel.class);
//                    list1.add(user);
////                    Log.d("billInDecode",""+user);
//                }
//             adapter.notifyDataSetChanged(); // Add this line
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        // Back button
      binding.backButtonBSSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
