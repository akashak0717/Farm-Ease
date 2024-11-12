package com.nyinst.farmease.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nyinst.farmease.R;
import com.nyinst.farmease.interfaces.ComplaintClickListener;
import com.nyinst.farmease.model.MyComplaintModel;

import java.util.ArrayList;

public class MyComplaintListAdapter extends RecyclerView.Adapter<MyComplaintListAdapter.ViewHolder> {

    ArrayList<MyComplaintModel> complaintArraylist;
    ComplaintClickListener mListener;

    public MyComplaintListAdapter(ArrayList<MyComplaintModel> complaintArraylist, ComplaintClickListener complaintClickListener) {
        this.complaintArraylist = complaintArraylist;
        this.mListener = complaintClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_my_complaint_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyComplaintModel myComplaintModel = complaintArraylist.get(position);
        holder.cropNameTv.setText(myComplaintModel.getCropName());
        holder.cropTypeTv.setText(myComplaintModel.getCropType());
        holder.currentDurationTv.setText(myComplaintModel.getCurrentDuration());
        holder.soilTypeTv.setText(myComplaintModel.getSoilType());
        holder.diseaseDetailsTv.setText(myComplaintModel.getDiseaseDetails());
        holder.previousHistoryTv.setText(myComplaintModel.getPreviousHistory());
        holder.symptomsTv.setText(myComplaintModel.getSymptoms());
        holder.statusTv.setText(myComplaintModel.getStatus());
        holder.reasonTv.setText(myComplaintModel.getReason());
        holder.medicinesTv.setText(myComplaintModel.getMedicines());
        holder.solutionTv.setText(myComplaintModel.getSolution());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onComplaintClicked(myComplaintModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return complaintArraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cropNameTv, cropTypeTv, currentDurationTv, soilTypeTv, diseaseDetailsTv,previousHistoryTv, symptomsTv, statusTv, reasonTv, medicinesTv, solutionTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cropNameTv = itemView.findViewById(R.id.crop_name);
            cropTypeTv = itemView.findViewById(R.id.crop_type);
            currentDurationTv = itemView.findViewById(R.id.current_duration);
            soilTypeTv = itemView.findViewById(R.id.soil_type);
            diseaseDetailsTv = itemView.findViewById(R.id.disease_details);
            previousHistoryTv = itemView.findViewById(R.id.previous_history);
            symptomsTv = itemView.findViewById(R.id.symptoms);
            statusTv = itemView.findViewById(R.id.status);
            reasonTv = itemView.findViewById(R.id.reason);
            medicinesTv = itemView.findViewById(R.id.medicines);
            solutionTv = itemView.findViewById(R.id.solution);
        }



    }
}
