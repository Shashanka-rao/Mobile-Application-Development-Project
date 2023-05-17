package com.example.miniprojectmad1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserCreation extends AppCompatActivity {

    Button signUpButton;
    TextView signInButton;
    EditText signUpEmail,signUpPassword;
    ImageButton backButton;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_creation);
        backButton = findViewById(R.id.backButton);
        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);

//        signUp_etUsername = findViewById(R.id.signUpUsername);
        signUpEmail = findViewById(R.id.signUpEmail);
        signUpPassword = findViewById(R.id.signUpPassword);
        auth = FirebaseAuth.getInstance();

        //start here

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = signUpEmail.getText().toString();
                String password1 = signUpPassword.getText().toString();

                if (TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1))
                {
                    Toast.makeText(UserCreation.this, "Enter both email and password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    regis(email1,password1);
                }
            }
        });


        //end here

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserCreation.this,MainActivity.class);
                startActivity(intent);
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserCreation.this,Login.class);
                startActivity(intent);
            }
        });
    }
    private void regis(String email,String password)
    {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(UserCreation.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(UserCreation.this, "User Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UserCreation.this,Login.class));
                }
                else
                {
                    Toast.makeText(UserCreation.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}