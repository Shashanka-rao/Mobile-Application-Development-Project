package com.example.miniprojectmad1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.miniprojectmad1.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
//    ActivityMainBinding binding;
    TextView signUpButton;
    Button signInButton;
     FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setContentView(binding.getRoot());


        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);

//
//        String CurUser = auth.getCurrentUser().toString();
//
//        if(CurUser==null) {
//            Intent intent = new Intent(MainActivity.this,Home.class);
//            startActivity(intent);
//        }

            signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserCreation.class));
            }
        });

            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Login.class));
                }
            });

        }



//            startActivity(new Intent(MainActivity.this, Home.class));



    }

//android:background="#220130"