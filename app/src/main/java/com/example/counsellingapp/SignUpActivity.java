package com.example.counsellingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    TextView TvAlreadyHaveAccount;
    EditText etUsername, etEmail, etPassword, etConfirmPassword;
    Button BtnSingup;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUsername = findViewById(R.id.etusername);
        etEmail = findViewById(R.id.etemail);
        etPassword = findViewById(R.id.etpassword);
        etConfirmPassword = findViewById(R.id.etconfirmpassword);
        BtnSingup = findViewById(R.id.btnsignup);
        TvAlreadyHaveAccount = findViewById(R.id.tvhaveaccount);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        //databaseReference = FirebaseDatabase.getInstance().getReference().child("Teachers");

        TvAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginPage.class));
                finish();
            }
        });


        BtnSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String pass = etPassword.getText().toString();
                String confirmpass = etConfirmPassword.getText().toString();


                if (TextUtils.isEmpty(username)){
                    etUsername.setError("username is required");
                }
                if (TextUtils.isEmpty(email)){
                    etEmail.setError("Email is required");
                }
                if (TextUtils.isEmpty(pass)){
                    etPassword.setError("Password must be >= 8 characters");
                }
                if (TextUtils.isEmpty(confirmpass)){
                    etConfirmPassword.setError("Password must be >= 8 characters");
                }
                if (!confirmpass.equals(pass)){
                    etConfirmPassword.setError("password not matched");
                }
                firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "SignUp Successfull....", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                           Counsellor counsellor = new Counsellor(username, email);

                           FirebaseDatabase.getInstance().getReference("Counsellors")
                                   .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                   .setValue(counsellor).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if (task.isSuccessful()){
                                       startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                       finish();
                                       Toast.makeText(getApplicationContext(), "Signup successfully....", Toast.LENGTH_SHORT).show();
                                   }else {
                                       Toast.makeText(getApplicationContext(), "SignUp Error", Toast.LENGTH_SHORT).show();
                                   }

                               }
                           });

                        }else {
                            Toast.makeText(getApplicationContext(), "SignUp Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }
}