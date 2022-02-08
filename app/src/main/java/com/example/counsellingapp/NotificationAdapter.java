package com.example.counsellingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    ArrayList<NotificationModel> nList;
    Context context;

   public NotificationAdapter(Context context, ArrayList<NotificationModel> nList){
       this.nList = nList;
       this.context = context;
   }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_item,parent,false);
        return new NotificationAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NotificationModel notificationModel = nList.get(position);
        holder.title.setText(notificationModel.getTitle());
        holder.message.setText(notificationModel.getMessage());


    }

    @Override
    public int getItemCount() {
        return nList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, message;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textviewtitle);
            message = itemView.findViewById(R.id.textviewmessage);
        }
    }
}
