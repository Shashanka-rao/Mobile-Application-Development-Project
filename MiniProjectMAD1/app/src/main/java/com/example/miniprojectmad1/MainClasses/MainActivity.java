package com.example.miniprojectmad1.MainClasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.miniprojectmad1.R;
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

//        if(auth.getCurrentUser()!=null)
//        {
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
    }

//android:background="#220130"