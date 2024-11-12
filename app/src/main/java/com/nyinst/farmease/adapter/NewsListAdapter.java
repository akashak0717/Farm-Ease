package com.nyinst.farmease.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nyinst.farmease.R;
import com.nyinst.farmease.model.NewsModel;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    ArrayList<NewsModel> newsArrayList;

    public NewsListAdapter(ArrayList<NewsModel> newsModelArrayList) {
        this.newsArrayList = newsModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsModel newsModel = newsArrayList.get(position);
        holder.titleTv.setText(newsModel.getTitle());
        holder.contentTv.setText(newsModel.getContent());
        holder.createByTv.setText(newsModel.getCreateBy());
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv, contentTv, createByTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.title);
            contentTv = itemView.findViewById(R.id.content);
            createByTv = itemView.findViewById(R.id.created_by);
        }

    }
}
