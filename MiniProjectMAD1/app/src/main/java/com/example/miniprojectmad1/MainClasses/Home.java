package com.example.miniprojectmad1.MainClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojectmad1.R;
import com.example.miniprojectmad1.databinding.ActivityHomeBinding;
import com.example.miniprojectmad1.trackService;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    ActivityHomeBinding binding;
    ImageView dashBoardBtn,contactUsBtn,carImage,imageViewHome;
    TextView bookServiceBtn,serviceHistoryBtn,serviceCostBtn,trackServiceBtn;
    Button logoutButton;
    Spinner spinnerNewReg;
    public int flag=0;
    FirebaseAuth auth = FirebaseAuth.getInstance();
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
        binding.newRegNo.setVisibility(View.INVISIBLE);


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
//                finish();
            }
        });
        binding.addNewVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.newRegNo.setVisibility(View.VISIBLE);

            }
        });

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


        // Create an ArrayAdapter using a string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown_options, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        binding.spinnerNewReg.setAdapter(adapter);
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