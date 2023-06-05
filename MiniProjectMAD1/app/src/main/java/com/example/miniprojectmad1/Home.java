package com.example.miniprojectmad1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miniprojectmad1.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity {
//    ActivityHomeBinding binding;
    ImageView dashBoardBtn,contactUsBtn,carImage;
    TextView bookServiceBtn,serviceHistoryBtn,serviceCostBtn,trackServiceBtn;
    public int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityHomeBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        setContentView(R.layout.activity_home);

        bookServiceBtn = findViewById(R.id.bookServiceBtn);
        serviceCostBtn = findViewById(R.id.serviceCostBtn);
        serviceHistoryBtn = findViewById(R.id.serviceHistoryBtn);
        trackServiceBtn = findViewById(R.id.trackServiceBtn);

        dashBoardBtn = findViewById(R.id.dashBoardBtn);
        contactUsBtn = findViewById(R.id.contactUsBtn);
        carImage = findViewById(R.id.carImage);

        //default condition
        bookServiceBtn.setVisibility(View.INVISIBLE);
        serviceCostBtn.setVisibility(View.INVISIBLE);
        serviceHistoryBtn.setVisibility(View.INVISIBLE);
        trackServiceBtn.setVisibility(View.INVISIBLE);


        dashBoardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flag==0) {
                        carImage.setVisibility(View.INVISIBLE);
                        bookServiceBtn.setVisibility(View.VISIBLE);
                        serviceCostBtn.setVisibility(View.VISIBLE);
                        serviceHistoryBtn.setVisibility(View.VISIBLE);
                        trackServiceBtn.setVisibility(View.VISIBLE);
                        flag = 1;
                    }
                    else if(flag==1)
                    {
                       bookServiceBtn.setVisibility(View.INVISIBLE);
                        serviceCostBtn.setVisibility(View.INVISIBLE);
                        serviceHistoryBtn.setVisibility(View.INVISIBLE);
                        trackServiceBtn.setVisibility(View.INVISIBLE);
                        carImage.setVisibility(View.VISIBLE);
                        flag = 0;
                    }
                }
            });

        bookServiceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Home.this, Bookservice.class);
                    startActivity(intent);
                }
            });
        serviceHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ServiceHistory.class);
                startActivity(intent);
            }
        });


    }
}