package com.example.counsellingapp;

import static com.example.counsellingapp.App.CHANNEL_1_ID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Notifications extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle,editTextMessage;


    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        notificationManager = NotificationManagerCompat.from(this);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextMessage = findViewById(R.id.editTextMessage);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        //databaseReference = FirebaseDatabase.getInstance().getReference().child("Students");


    }
    public void send(View v){

        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        if (TextUtils.isEmpty(title)){
            editTextTitle.setError("Type Title here....");
            editTextTitle.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(message)){
            editTextMessage.setError("Type message here..");
            editTextMessage.requestFocus();
            return;
        }

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notifi)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);

        HashMap<String, String> notificationmessages = new HashMap<>();

        notificationmessages.put("title",title);
        notificationmessages.put("message",message);

        FirebaseDatabase.getInstance().getReference("Notifications")
                .push()
                .setValue(notificationmessages).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Message saved....", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}