package com.example.miniprojectmad1.MainClasses;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojectmad1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText loginEmail,loginPassword;
    Button loginButton;
    TextView signUpButton;
    ImageButton backButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        backButton = (ImageButton) findViewById(R.id.backButton);
        signUpButton = findViewById(R.id.signUpButton);
        loginButton = findViewById(R.id.loginButton);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if(user!=null)
        {
            String email = user.getEmail();
            if(email.equals("Manager1@services.com")) {
                Intent intent = new Intent(Login.this, HomeManager.class);
                startActivity(intent);
            }
            else if (user!=null){
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
            }
        }
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
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
                if(!email.equals("Manager1@services.com") && !password.equals("Mad#12345"))
                {
                    Intent intent2 = new Intent(Login.this,Home.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent2);
                }
                else {
                    Intent intent1 = new Intent(Login.this, HomeManager.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                }
            }
        });
        auth.signInWithEmailAndPassword(email,password).addOnFailureListener(Login.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "enter correct credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}