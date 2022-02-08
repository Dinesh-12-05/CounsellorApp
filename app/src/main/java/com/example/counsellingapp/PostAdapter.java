package com.example.counsellingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private ArrayList<PostModel> pList;
    private Context context;

    public PostAdapter( Context context, ArrayList<PostModel> pList) {
        this.context = context;
        this.pList = pList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singel_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PostModel postModel = pList.get(position);
        holder.Description.setText(postModel.getDescription());
        Glide.with(context).load(pList.get(position).getImageUrl()).into(holder.image_single_View);

    }

    @Override
    public int getItemCount() {
        return pList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Description;
        ImageView image_single_View;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_single_View = itemView.findViewById(R.id.image_single_view);
            Description = itemView.findViewById(R.id.tvDescription);
        }
    }
}
