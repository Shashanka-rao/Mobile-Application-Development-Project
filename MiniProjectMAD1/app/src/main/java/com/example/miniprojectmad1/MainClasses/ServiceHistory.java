package com.example.miniprojectmad1.MainClasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.miniprojectmad1.R;
import com.example.miniprojectmad1.Adapters.ServiceHistoryAdapter;
import com.example.miniprojectmad1.Models.ServiceHistoryModel;
import com.example.miniprojectmad1.databinding.ActivityServiceHistoryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceHistory extends AppCompatActivity {

    ActivityServiceHistoryBinding binding;
    ImageButton backButtonBSSH;
    FirebaseDatabase database;
    ServiceHistoryAdapter adapter;
    ArrayList<ServiceHistoryModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityServiceHistoryBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());


        backButtonBSSH = (ImageButton) findViewById(R.id.backButtonBSSH);
        database = FirebaseDatabase.getInstance();
        binding.ServiceHistory.setHasFixedSize(true);
        binding.ServiceHistory.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new ServiceHistoryAdapter(this,list);
        binding.ServiceHistory.setAdapter(adapter);

        database.getReference().child("serviceNo").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ServiceHistoryModel user = dataSnapshot.getValue(ServiceHistoryModel.class);
                    list.add(user);
                    Log.d("billInDecode",""+user);
                }
                adapter.notifyDataSetChanged(); // Add this line
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren())
//                {
//                    ServiceHistoryModel user = dataSnapshot.getValue(ServiceHistoryModel.class);
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


      // Back button
      backButtonBSSH.setOnClickListener(new View.OnClickListener() {
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
