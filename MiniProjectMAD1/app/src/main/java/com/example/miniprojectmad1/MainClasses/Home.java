package com.example.miniprojectmad1.MainClasses;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojectmad1.databinding.ActivityHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
    ActivityHomeBinding binding;
    public int flag=0; //HOME PAGE
    String regNoD;
    ImageView manImg;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseRef =database.getReference("Users").child("UserData");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //default condition
        binding.bookServiceBtn.setVisibility(View.INVISIBLE);
        binding.serviceCostBtn.setVisibility(View.INVISIBLE);
        binding.serviceHistoryBtn.setVisibility(View.INVISIBLE);
        binding.trackServiceBtn.setVisibility(View.INVISIBLE);
        String uidU = auth.getCurrentUser().getUid(); // Assuming you want to retrieve the uid of the current user

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String uid = childSnapshot.child("userId").getValue(String.class);
                    if (uid != null && uid.equals(uidU)) {
                        String Uname = childSnapshot.child("username").getValue(String.class);
                        regNoD = childSnapshot.child("vRegNo").getValue(String.class);

                        // Set into textview
                        binding.usernameH.setText("Username: " + Uname);
                        binding.vRegNoH.setText("Reg No.: " + regNoD);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("TAG", "Failed to read value.", error.toException());
                binding.usernameH.setText("Username: ");
            }
        });


        binding.dashBoardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flag==0) {
                        binding.carImage.setVisibility(View.INVISIBLE);
                        binding.bookServiceBtn.setVisibility(View.VISIBLE);
                        binding.serviceCostBtn.setVisibility(View.VISIBLE);
                        binding.serviceHistoryBtn.setVisibility(View.VISIBLE);
                        binding.trackServiceBtn.setVisibility(View.VISIBLE);
                        flag = 1;
                    }
                }
            });

        //On pressing home button return back to home
        binding.HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==1) {
                    binding.carImage.setVisibility(View.VISIBLE);
                    binding.bookServiceBtn.setVisibility(View.INVISIBLE);
                    binding.serviceCostBtn.setVisibility(View.INVISIBLE);
                    binding.serviceHistoryBtn.setVisibility(View.INVISIBLE);
                    binding.trackServiceBtn.setVisibility(View.INVISIBLE);
                    flag=0;
                }
            }
        });

        //contacts
        binding.contactUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ContactUs.class);
                startActivity(intent);
            }
        });

        binding.bookServiceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Home.this, Bookservice.class);
                    
                    startActivity(intent);

                }
            });
        binding.serviceHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ServiceHistory.class);
                intent.putExtra("vehicleRegNo",regNoD);
                startActivity(intent);
            }
        });

        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             auth.signOut();
             Intent intent = new Intent(Home.this, MainActivity.class);
             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(intent);
            }
        });


        binding.trackServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, trackService.class);
                intent.putExtra("vehicleRegNo",regNoD);
                startActivity(intent);
            }
        });


        //to serviceCost
        binding.serviceCostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ServiceCost.class);
                intent.putExtra("regNo",regNoD);
                startActivity(intent);
            }
        });


//        // Create an ArrayAdapter using a string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown_options, android.R.layout.simple_spinner_item);
//
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // Apply the adapter to the spinner
//        binding.spinnerNewReg.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finish();
    super.onBackPressed();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }

}