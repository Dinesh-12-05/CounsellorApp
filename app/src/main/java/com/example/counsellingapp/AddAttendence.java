package com.example.counsellingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class AddAttendence extends AppCompatActivity {

    private Spinner txtSemester,txtTest;
    private EditText txtUsn;
    TextView txtMarks1,txtMarks2,txtMarks3,txtMarks4,txtMarks5, txtSub1, txtSub2, txtSub3, txtSub4, txtSub5;
    private Button btnGet;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String marks1, marks2, marks3, marks4, marks5, subj1, subj2, subj3, subj4, subj5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendence);

        txtSemester = findViewById(R.id.txtsemester);
        txtTest = findViewById(R.id.txttest);


        txtUsn = findViewById(R.id.txtusn);

        txtMarks1 = findViewById(R.id.txtmarks1);
        txtMarks2 = findViewById(R.id.txtmarks2);
        txtMarks3 = findViewById(R.id.txtmarks3);
        txtMarks4 = findViewById(R.id.txtmarks4);
        txtMarks5 = findViewById(R.id.txtmarks5);

        txtSub1 = findViewById(R.id.txtsub1);
        txtSub2 = findViewById(R.id.txtsub2);
        txtSub3 = findViewById(R.id.txtsub3);
        txtSub4 = findViewById(R.id.txtsub4);
        txtSub5 = findViewById(R.id.txtsub5);

        btnGet = findViewById(R.id.btnGet);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        //databaseReference = FirebaseDatabase.getInstance().getReference().child("Results");



        String[] semester = getResources().getStringArray(R.array.semester);
        String[] test = getResources().getStringArray(R.array.test);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,semester);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtSemester.setAdapter(adapter);

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,test);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtTest.setAdapter(adapter1);



        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Usn = txtUsn.getText().toString();
                String Semester = txtSemester.getSelectedItem().toString();
                String Test = txtTest.getSelectedItem().toString();


                //Query Qresults = databaseReference.orderByChild("usn").equalTo(Usn);
                //Query Qresults1 = databaseReference.orderByChild("semester").equalTo(Semester);
                //Query Qresults2 = databaseReference.orderByChild("test").equalTo(Test);



                databaseReference = FirebaseDatabase.getInstance().getReference().child("Results").child(Usn).child(Semester).child(Test);

               databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       try{
                           marks1 = snapshot.child("sub1").getValue().toString();
                           marks2 = snapshot.child("sub2").getValue().toString();
                           marks3 = snapshot.child("sub3").getValue().toString();
                           marks4 = snapshot.child("sub4").getValue().toString();
                           marks5 = snapshot.child("sub5").getValue().toString();

                           subj1 = snapshot.child("subject1").getValue().toString();
                           subj2 = snapshot.child("subject2").getValue().toString();
                           subj3 = snapshot.child("subject3").getValue().toString();
                           subj4 = snapshot.child("subject4").getValue().toString();
                           subj5 = snapshot.child("subject5").getValue().toString();

                           txtMarks1.setText(marks1);
                           txtMarks2.setText(marks2);
                           txtMarks3.setText(marks3);
                           txtMarks4.setText(marks4);
                           txtMarks5.setText(marks5);

                           txtSub1.setText(subj1);
                           txtSub2.setText(subj2);
                           txtSub3.setText(subj3);
                           txtSub4.setText(subj4);
                           txtSub5.setText(subj5);


                       }catch (Throwable e){
                           Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                       }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}