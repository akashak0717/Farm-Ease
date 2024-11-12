package com.nyinst.farmease.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nyinst.farmease.R;
import com.nyinst.farmease.model.AgricultureOfficeModel;
import com.nyinst.farmease.ui.dashboard.AgricultureOfficeListActivity;

import java.util.ArrayList;

public class AgricultureOfficeAdapter extends RecyclerView.Adapter<AgricultureOfficeAdapter.ViewHolder> {

    ArrayList<AgricultureOfficeModel> agricultureOfficeList;
    private Context context;

    public AgricultureOfficeAdapter(ArrayList<AgricultureOfficeModel> agricultureOfficeList, AgricultureOfficeListActivity context) {
        this.agricultureOfficeList = agricultureOfficeList;
        this.context = (Context) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_agriculture_office, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AgricultureOfficeModel agricultureOfficeModel = agricultureOfficeList.get(position);
        holder.nameTv.setText(agricultureOfficeModel.getName());
        holder.categoryTv.setText(agricultureOfficeModel.getCategory());
        holder.addressTv.setText(agricultureOfficeModel.getAddress());
        holder.locationTv.setText(agricultureOfficeModel.getLocation());
        holder.pincodeTv.setText(agricultureOfficeModel.getPincode());
        holder.mobileTv.setText(agricultureOfficeModel.getMobile());

        holder.mapImage.setOnClickListener(v -> {
            String location = agricultureOfficeModel.getLocation();
            openLocationInMaps(location);
        });
    }

    @Override
    public int getItemCount() {
        return agricultureOfficeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv, categoryTv, addressTv, locationTv, pincodeTv, mobileTv;
        LinearLayout mapImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.name);
            categoryTv = itemView.findViewById(R.id.category);
            addressTv = itemView.findViewById(R.id.address);
            locationTv = itemView.findViewById(R.id.location);
            pincodeTv = itemView.findViewById(R.id.pincode);
            mobileTv = itemView.findViewById(R.id.mobile);

            mapImage = itemView.findViewById(R.id.google_map_image);
        }
    }

    private void openLocationInMaps(String location) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        context.startActivity(mapIntent);

    }
}