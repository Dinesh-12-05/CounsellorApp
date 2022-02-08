 package com.example.counsellingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

 public class ForgotPassword extends AppCompatActivity {

    EditText EtEmail;
    Button btnResetPass;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        EtEmail = findViewById(R.id.etemail);
        btnResetPass = findViewById(R.id.Btnresetpass);


        firebaseAuth = FirebaseAuth.getInstance();

        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EtEmail.getText().toString().trim();


                if (email.isEmpty()){
                    EtEmail.setError("Email is required");
                    EtEmail.requestFocus();
                    return;
                }


                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Check your Email to reset password..", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginPage.class));
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(), "Check the entered Email address..", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }
}