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

public class LoginActivity extends AppCompatActivity {

    TextView TvCreateNew, tvForgotPass;
    EditText etEmail, etPassword;
    Button btnLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TvCreateNew = findViewById(R.id.tvcreatenew);
        etEmail = findViewById(R.id.etemail);
        etPassword = findViewById(R.id.etpassword);
        btnLogin = findViewById(R.id.Btnlogin);
        tvForgotPass = findViewById(R.id.tvforgotpassword);
        fAuth = FirebaseAuth.getInstance();

        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ForgotPassword.class));
                finish();

            }
        });


        TvCreateNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = etEmail.getText().toString();
                String pass = etPassword.getText().toString();


                if (TextUtils.isEmpty(email)){
                    etEmail.setError("Enter College Email. ex(@rvce.edu.in)");
                    etEmail.requestFocus();
                    return;
                }else if (!email.matches("[a-zA-Z0-9._-]+[mca19]+@[rvce._-]+[edu._-]+\\.+[in]+")){
                    etEmail.setError("Enter valid email address. ex(@rvce.edu.in");
                    etEmail.requestFocus();
                    return;

                }
                if (TextUtils.isEmpty(pass)){
                    etPassword.setError("password must be >=8 characters");
                    etPassword.requestFocus();
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(getApplicationContext(), "Login Successfull..", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Dashboard1.class));
                        }else {
                            Toast.makeText(getApplicationContext(), "Login UnSuccessfull", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

            }
        });


    }
}