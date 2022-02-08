package com.example.counsellingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentProfile extends AppCompatActivity {


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String userID;

    TextView tvName,tvUsn,tvEmail,tvPhone,tvBatch,tvDob,tvGender,tvsslcpercent,tvsslcyop,tvsslcschool,tvpupercent,tvpuyop,tvpuclg,tvugdegree,tvugcombination,tvugcgpa,tvugyop,tvugclg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        tvName = findViewById(R.id.tvname);
        tvUsn = findViewById(R.id.tvusn);
        tvEmail = findViewById(R.id.tvemail);
        tvPhone = findViewById(R.id.tvphone);
        tvBatch = findViewById(R.id.tvbatch);
        tvDob = findViewById(R.id.tvdob);
        tvGender = findViewById(R.id.tvgender);
        tvsslcpercent = findViewById(R.id.tvsslcpercent);
        tvsslcyop = findViewById(R.id.tvsslcyop);
        tvsslcschool = findViewById(R.id.tvsslcschool);
        tvpupercent = findViewById(R.id.tvpupercent);
        tvpuyop = findViewById(R.id.tvpuyop);
        tvpuclg = findViewById(R.id.tvpuclg);
        tvugdegree = findViewById(R.id.tvugdegree);
        tvugcombination = findViewById(R.id.tvugcombination);
        tvugcgpa = findViewById(R.id.tvugcgpa);
        tvugyop = findViewById(R.id.tvugyop);
        tvugclg = findViewById(R.id.tvugclg);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        userID = firebaseUser.getUid();


        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String name = userProfile.name;
                    String usn = userProfile.usn;
                    String email = userProfile.email;
                    String phone = userProfile.phone;
                    String batch = userProfile.batch;
                    String dob = userProfile.dob;
                    String gender = userProfile.gender;
                    String sslcpercent = userProfile.sslcpercent;
                    String sslcyop = userProfile.sslcyop;
                    String sslcschool = userProfile.sslcschool;
                    String pupercent = userProfile.pupercent;
                    String puyop = userProfile.puyop;
                    String puclg = userProfile.puclg;
                    String ugdegree = userProfile.ugdegree;
                    String ugcombination = userProfile.ugcombination;
                    String ugcgpa = userProfile.ugcgpa;
                    String ugyop = userProfile.ugyop;
                    String ugclg = userProfile.ugclg;


                    tvName.setText(name);
                    tvUsn.setText(usn);
                    tvEmail.setText(email);
                    tvPhone.setText(phone);
                    tvBatch.setText(batch);
                    tvDob.setText(dob);
                    tvGender.setText(gender);
                    tvsslcpercent.setText(sslcpercent);
                    tvsslcyop.setText(sslcyop);
                    tvsslcschool.setText(sslcschool);
                    tvpupercent.setText(pupercent);
                    tvpuyop.setText(puyop);
                    tvpuclg.setText(puclg);
                    tvugdegree.setText(ugdegree);
                    tvugcombination.setText(ugcombination);;
                    tvugcgpa.setText(ugcgpa);
                    tvugyop.setText(ugyop);
                    tvugclg.setText(ugclg);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "something wrong happen.....", Toast.LENGTH_SHORT).show();
            }
        });
    }
}