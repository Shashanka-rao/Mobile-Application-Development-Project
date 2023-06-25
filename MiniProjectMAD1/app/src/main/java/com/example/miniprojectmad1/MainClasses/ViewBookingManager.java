package com.example.miniprojectmad1.MainClasses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.miniprojectmad1.Adapters.ViewBookingManagerAdapter;
import com.example.miniprojectmad1.Models.ViewBookingManagerModel;
import com.example.miniprojectmad1.databinding.ActivityViewBookingManagerBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

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
                }
                Collections.reverse(list);
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