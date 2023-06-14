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
import com.example.miniprojectmad1.trackService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
    ActivityHomeBinding binding;
    ImageView dashBoardBtn,contactUsBtn,carImage,imageViewHome;
    TextView bookServiceBtn,serviceHistoryBtn,serviceCostBtn,trackServiceBtn;
    Button logoutButton;
    public int flag=0;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseRef =database.getReference("Users");
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
        String email = auth.getCurrentUser().getEmail(); // Assuming you want to retrieve the email of the current user

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String childId = childSnapshot.getKey();
                    String email1 = childSnapshot.child("email").getValue(String.class);
                    if (email1 != null && email1.equals(email)) {
                        String Uname = childSnapshot.child("username").getValue(String.class);
                        String regNoD = childSnapshot.child("vRegNo").getValue(String.class);

                        // Set into textview
                        binding.usernameH.setText("Username: " + Uname);
                        binding.vRegNoH.setText("Reg No.: " + regNoD);
                        break;
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
                    else if(flag==1)
                    {
                        binding.bookServiceBtn.setVisibility(View.INVISIBLE);
                        binding.serviceCostBtn.setVisibility(View.INVISIBLE);
                        binding.serviceHistoryBtn.setVisibility(View.INVISIBLE);
                        binding.trackServiceBtn.setVisibility(View.INVISIBLE);
                        binding.carImage.setVisibility(View.VISIBLE);
                        flag = 0;
                    }
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

        //past
//        binding.addNewVehicleBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.newRegNo.setVisibility(View.VISIBLE);
//                binding.addNewRegNo.setVisibility(View.VISIBLE);
//            }
//        });
        //new registration
//        binding.addNewRegNo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.newRegNo.setVisibility(View.VISIBLE);
//                binding.addNewRegNo.setVisibility(View.VISIBLE);
//                String newRegNo = binding.newRegNo.getText().toString().trim();
//                if (!newRegNo.isEmpty()) {
//
//                    // Get the current items in the spinner
//                    ArrayAdapter<String> adapter = (ArrayAdapter<String>)binding.spinnerNewReg.getAdapter();
//                    List<String> spinnerItems = new ArrayList<>();
//                    for (int i = 0; i < adapter.getCount(); i++) {
//                        spinnerItems.add(adapter.getItem(i));
//                    }
//
//                    // Add the new item to the spinner items
//                    spinnerItems.add(newRegNo);
//
//                    // Create a new ArrayAdapter with the updated spinner items
//                    ArrayAdapter<String> newAdapter = new ArrayAdapter<>(Home.this, android.R.layout.simple_spinner_item, spinnerItems);
//                    newAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                    // Set the new ArrayAdapter as the adapter for the spinner
//                    binding.spinnerNewReg.setAdapter(newAdapter);
//
//                    // Clear the EditText
//                    binding.newRegNo.setText("");
//                    binding.newRegNo.setVisibility(View.INVISIBLE);
//                    binding.addNewRegNo.setVisibility(View.INVISIBLE);
//
//                    // Optionally, select the newly added item in the spinner
//                    binding.spinnerNewReg.setSelection(newAdapter.getPosition(newRegNo));
//                }
//
//            }
//        });

        binding.trackServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, trackService.class);
                startActivity(intent);
            }
        });
        //On pressing home button return back to home
        binding.imageViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //to serviceCost
        binding.serviceCostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ServiceCost.class);
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