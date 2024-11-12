package com.nyinst.farmease.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nyinst.farmease.R;
import com.nyinst.farmease.model.FeedbackModel;

import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> {

    ArrayList<FeedbackModel> feedbackModelArrayList;

    public FeedbackAdapter(ArrayList<FeedbackModel> feedbackModelArrayList) {
        this.feedbackModelArrayList = feedbackModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_feedback_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedbackModel FeedbackModel = feedbackModelArrayList.get(position);
        holder.nameTv.setText(FeedbackModel.getName());
        holder.ratingTv.setText(FeedbackModel.getRating());
        holder.feedbackTv.setText(FeedbackModel.getFeedback());
    }

    @Override
    public int getItemCount() {
        return feedbackModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv, ratingTv, feedbackTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.name);
            ratingTv = itemView.findViewById(R.id.rating);
            feedbackTv = itemView.findViewById(R.id.feedback);
        }
    }
}
