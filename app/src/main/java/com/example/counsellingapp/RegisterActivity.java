package com.example.counsellingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText Name,Usn,Batch,Email,Phone,Gender,Dob,Password,esslcyop,esslcpercent,esslcschool,epuyop,epupercent,epuclg,eugdegree,eugcombination,eugcgpa,eugyop,eugclg;
    Button Register;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name = findViewById(R.id.etname);
        Usn = findViewById(R.id.etusn);
        Batch = findViewById(R.id.etbatch);
        Email = findViewById(R.id.etemail);
        Phone = findViewById(R.id.etphone);
        Gender = findViewById(R.id.etgender);
        Dob = findViewById(R.id.etdob);
        Password = findViewById(R.id.etpassword);
        esslcyop = findViewById(R.id.etsslcyop);
        esslcpercent = findViewById(R.id.etsslcpercent);
        esslcschool = findViewById(R.id.etsslcschool);
        epuyop = findViewById(R.id.etpuyop);
        epupercent = findViewById(R.id.etpupercent);
        epuclg = findViewById(R.id.etpuclg);
        eugdegree = findViewById(R.id.etUgdegree);
        eugcombination = findViewById(R.id.etUgcombination);
        eugcgpa = findViewById(R.id.etUgcgpa);
        eugyop = findViewById(R.id.etUgYop);
        eugclg = findViewById(R.id.etUgClg);
        Register = findViewById(R.id.btnregister);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        //databaseReference = FirebaseDatabase.getInstance().getReference().child("Students");


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = Name.getText().toString();
                String usn = Usn.getText().toString();
                String batch = Batch.getText().toString();
                String email = Email.getText().toString();
                String phone = Phone.getText().toString();
                String gender = Gender.getText().toString();
                String dob = Dob.getText().toString();
                String password = Password.getText().toString();
                String sslcyop =  esslcyop.getText().toString();
                String sslcpercent = esslcpercent.getText().toString();
                String sslcschool = esslcschool.getText().toString();
                String puyop = epuyop.getText().toString();
                String pupercent = epupercent.getText().toString();
                String puclg = epuclg.getText().toString();
                String ugdegree = eugdegree.getText().toString();
                String ugcombination = eugcombination.getText().toString();
                String ugcgpa = eugcgpa.getText().toString();
                String ugyop = eugyop.getText().toString();
                String ugclg = eugclg.getText().toString();


                if (TextUtils.isEmpty(name)){
                    Name.setError("Name is required");
                    Name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(usn)){
                    Usn.setError("usn required");
                    Usn.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(batch)){
                    Batch.setError("Enter year of joining");
                    Batch.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    Email.setError("Email is required");
                    Email.requestFocus();
                    return;
                }else if (!email.matches("[a-zA-Z0-9._-]+[mca19]+@[rvce._-]+[edu._-]+\\.+[in]+")){
                    Email.setError("Enter valid email address. ex(@rvce.edu.in");
                    Email.requestFocus();
                    return;

                }
                if (TextUtils.isEmpty(phone)){
                    Phone.setError("Phone is required");
                    Phone.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(gender)){
                    Gender.setError("Gender is required");
                    Gender.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(dob)){
                    Dob.setError("Dob is required");
                    Dob.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Password.setError("password must be >=8 characters");
                    Password.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(sslcyop)){
                    esslcyop.setError("Required");
                    esslcyop.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(sslcpercent)){
                    esslcpercent.setError("Required");
                    esslcpercent.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(sslcschool)){
                    esslcschool.setError("Required");
                    esslcschool.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(puyop)){
                    epuyop.setError("Required");
                    epuyop.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pupercent)){
                    epupercent.setError("Required");
                    epupercent.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(puclg)){
                    epuclg.setError("Required");
                    epuclg.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ugdegree)){
                    eugdegree.setError("Required");
                    eugdegree.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ugcombination)){
                    eugcombination.setError("Required");
                    eugcombination.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ugcgpa)){
                    eugcgpa.setError("Required");
                    eugcgpa.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(ugyop)){
                    eugyop.setError("Required");
                    eugyop.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ugclg)){
                    eugclg.setError("Required");
                    eugclg.requestFocus();
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(name, usn, batch, email, phone, gender, dob,sslcyop, sslcpercent,sslcschool, puyop, pupercent, puclg, ugdegree,ugyop,ugcgpa,ugcombination,ugclg);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        startActivity(new Intent(getApplicationContext(),LoginPage.class));
                                        finish();
                                        Toast.makeText(getApplicationContext(), "Registere successfully....", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(getApplicationContext(), "Register Error....", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Register Error....", Toast.LENGTH_SHORT).show();
                        }
                    }
                });





            }
        });

    }
}