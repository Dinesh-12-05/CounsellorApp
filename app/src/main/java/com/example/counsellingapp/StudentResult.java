package com.example.counsellingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class StudentResult extends AppCompatActivity {
    private Spinner txtSemester,txtTest;
    private EditText txtUsn,txtMarks1,txtMarks2,txtMarks3,txtMarks4,txtMarks5, txtSub1, txtSub2, txtSub3, txtSub4, txtSub5;
    private Button bttnSave, BtnGet;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_result);
        txtUsn = findViewById(R.id.txtusn);

        BtnGet = findViewById(R.id.btnGet);

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

        txtSemester = findViewById(R.id.txtsemester);
        txtTest = findViewById(R.id.txttest);

        bttnSave = findViewById(R.id.bttnSave);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Results");

        String[] semester = getResources().getStringArray(R.array.semester);
        String[] test = getResources().getStringArray(R.array.test);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,semester);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtSemester.setAdapter(adapter);

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,test);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtTest.setAdapter(adapter1);

        bttnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usn = txtUsn.getText().toString();
                String marks1 = txtMarks1.getText().toString();
                String marks2 = txtMarks2.getText().toString();
                String marks3 = txtMarks3.getText().toString();
                String marks4 = txtMarks4.getText().toString();
                String marks5 = txtMarks5.getText().toString();

                String sub1 = txtSub1.getText().toString();
                String sub2 = txtSub2.getText().toString();
                String sub3 = txtSub3.getText().toString();
                String sub4 = txtSub4.getText().toString();
                String sub5 = txtSub5.getText().toString();

                String semester = txtSemester.getSelectedItem().toString();
                String test = txtTest.getSelectedItem().toString();

                if (TextUtils.isEmpty(usn)){
                    txtUsn.setError("Usn Required");
                    txtUsn.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(marks1)){
                    txtMarks1.setError("Marks required");
                    txtMarks1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(marks2)){
                    txtMarks2.setError("Marks required");
                    txtMarks2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(marks3)){
                    txtMarks3.setError("Marks required");
                    txtMarks3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(marks4)){
                    txtMarks4.setError("Marks required");
                    txtMarks4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(marks5)){
                    txtMarks5.setError("Marks required");
                    txtMarks5.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(sub1)){
                    txtSub1.setError("Subject required");
                    txtSub1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(sub2)){
                    txtSub2.setError("Subject required");
                    txtSub2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(sub3)){
                    txtSub3.setError("Subject required");
                    txtSub3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(sub4)){
                    txtSub4.setError("Subject required");
                    txtSub4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(sub5)){
                    txtSub5.setError("Subject required");
                    txtSub5.requestFocus();
                    return;
                }






                HashMap<String, String> results = new HashMap<>();

                results.put("usn",usn);
                results.put("sub1",marks1);
                results.put("sub2",marks2);
                results.put("sub3",marks3);
                results.put("sub4",marks4);
                results.put("sub5",marks5);
                results.put("subject1",sub1);
                results.put("subject2",sub2);
                results.put("subject3",sub3);
                results.put("subject4",sub4);
                results.put("subject5",sub5);
                results.put("semester",semester);
                results.put("test",test);

                databaseReference.child(usn).child(semester).child(test).setValue(results).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Results Uploaded successfully...", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Error is Results Uploading...", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });


    }
}