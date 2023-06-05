package com.example.miniprojectmad1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojectmad1.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

//    ActivityLoginBinding binding;
    EditText loginEmail,loginPassword;
    Button loginButton;
    TextView signUpButton;
    ImageButton backButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        setContentView(binding.getRoot());

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        backButton = (ImageButton) findViewById(R.id.backButton);
        signUpButton = findViewById(R.id.signUpButton);
        loginButton = findViewById(R.id.loginButton);
        auth = FirebaseAuth.getInstance();


        if(auth.getCurrentUser()!=null)
        {
            Intent intent = new Intent(Login.this,Home.class);
            startActivity(intent);

        }
        //start here

       loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = loginEmail.getText().toString();
                String password1 = loginPassword.getText().toString();

                if (TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1))
                {
                    Toast.makeText(Login.this, "Enter both email and password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    login(email1,password1);
                }
            }
        });



        //end here

       backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,UserCreation.class);
                startActivity(intent);
            }
        });
    }
    private void login(String email,String password)
    {
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(Login.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this, Home.class));
            }
        });

        auth.signInWithEmailAndPassword(email,password).addOnFailureListener(Login.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "enter correct credentials", Toast.LENGTH_SHORT).show();
            }
        });

    }

}