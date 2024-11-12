package com.nyinst.farmease.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nyinst.farmease.R;
import com.nyinst.farmease.model.UsersModel;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<com.nyinst.farmease.adapter.UsersAdapter.ViewHolder> {

    ArrayList<UsersModel> usersList;

    public UsersAdapter(ArrayList<UsersModel> userList) {
        this.usersList = userList;
    }

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_users_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UsersModel usersModel = usersList.get(position);
        holder.nameTv.setText(usersModel.getName());
        holder.emailTv.setText(usersModel.getEmail());
        holder.mobileTv.setText(usersModel.getMobile());


    }


    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv, emailTv, mobileTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.name);
            emailTv = itemView.findViewById(R.id.email);
            mobileTv = itemView.findViewById(R.id.mobile);
        }
    }
}