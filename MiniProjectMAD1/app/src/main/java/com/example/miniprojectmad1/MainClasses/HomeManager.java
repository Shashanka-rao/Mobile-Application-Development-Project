package com.example.miniprojectmad1.MainClasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.miniprojectmad1.databinding.ActivityHomeManagerBinding;
import com.google.firebase.auth.FirebaseAuth;

public class HomeManager extends AppCompatActivity {
ActivityHomeManagerBinding binding;
ImageView viewBookingsBtn;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityHomeManagerBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.viewBookingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(HomeManager.this, ViewBookingManager.class);
                startActivity(intent3);
            }
        });
//        binding.billEntryBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent2 = new Intent(HomeManager.this, BillEntryManager.class);
//                startActivity(intent2);
//            }
//        });
//        binding.updateSSBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent3 = new Intent(HomeManager.this, UpdateSSManager.class);
//                startActivity(intent3);
//            }
//        });

        binding.logoutButtonMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auth.signOut();
                Intent intent = new Intent(HomeManager.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
        auth.signOut();
        Intent intent = new Intent(HomeManager.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}