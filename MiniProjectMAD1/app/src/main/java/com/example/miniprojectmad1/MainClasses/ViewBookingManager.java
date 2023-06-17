package com.example.miniprojectmad1.MainClasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.miniprojectmad1.Adapters.ServiceHistoryAdapter;
import com.example.miniprojectmad1.Adapters.ViewBookingManagerAdapter;
import com.example.miniprojectmad1.Models.ServiceHistoryModel;
import com.example.miniprojectmad1.Models.ViewBookingManagerModel;
import com.example.miniprojectmad1.R;
import com.example.miniprojectmad1.databinding.ActivityServiceHistoryBinding;
import com.example.miniprojectmad1.databinding.ActivityViewBookingManagerBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewBookingManager extends AppCompatActivity {
    ActivityViewBookingManagerBinding binding;

    FirebaseDatabase database;

    ViewBookingManagerAdapter adapter;
    ArrayList<ViewBookingManagerModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityViewBookingManagerBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        binding.rvBooking.setHasFixedSize(true);
        binding.rvBooking.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new ViewBookingManagerAdapter(this,list);
        binding.rvBooking.setAdapter(adapter);


        DatabaseReference bookingReference = database.getReference().child("Users").child("Booking");
//        DatabaseReference userDataReference = database.getReference().child("Users").child("UserData");

//        bookingReference

        //fetch from databse
        bookingReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ViewBookingManagerModel user = dataSnapshot.getValue(ViewBookingManagerModel.class);
                    list.add(user);
                    Log.d("View Booking Decode ",""+user);
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