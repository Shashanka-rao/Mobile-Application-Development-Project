package com.example.miniprojectmad1.MainClasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojectmad1.Models.UserCreationModel;
import com.example.miniprojectmad1.R;
import com.example.miniprojectmad1.databinding.ActivityUserCreationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserCreation extends AppCompatActivity {
    ActivityUserCreationBinding binding;
    Button signUpButton;
    TextView signInButton;
    EditText signUpEmail,signUpPassword;
    ImageButton backButton;
    private FirebaseAuth auth;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserCreationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        setContentView(R.layout.activity_user_creation);

        backButton = findViewById(R.id.backButton);
        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);
        signUpEmail = findViewById(R.id.signUpEmail);
        signUpPassword = findViewById(R.id.signUpPassword);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
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
                onBackPressed();
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


                    UserCreationModel model = new UserCreationModel();
                    //enter data to firebase
                    model.setUsername(binding.usernameSU.getText().toString().trim());
                    model.setMobileNo(binding.mobileNoSU.getText().toString().trim());
                    model.setvRegNo(binding.VRegNoSU.getText().toString().trim());
                    model.setEmail(email);


                    database.getReference().child("Users").push().setValue(model);
                    Toast.makeText(UserCreation.this, "User Created", Toast.LENGTH_SHORT).show();
                    auth.signOut();
                    Intent intent = new Intent(UserCreation.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(UserCreation.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

}