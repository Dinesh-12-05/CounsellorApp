package com.example.counsellingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.MyViewHolder> {

    ArrayList<StudentListModel> sList;
    Context context;


    public StudentListAdapter(Context context, ArrayList<StudentListModel> sList){
        this.sList = sList;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StudentListModel studentListModel = sList.get(position);
        holder.name.setText(studentListModel.getName());
        holder.usn.setText(studentListModel.getUsn());
        holder.email.setText(studentListModel.getEmail());

    }

    @Override
    public int getItemCount() {
        return sList.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView usn, name, email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            usn = itemView.findViewById(R.id.textviewusn);
            name = itemView.findViewById(R.id.textviewname);
            email = itemView.findViewById(R.id.textviewemail);
        }
    }
}
