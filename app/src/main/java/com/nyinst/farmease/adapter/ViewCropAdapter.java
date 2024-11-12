package com.nyinst.farmease.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nyinst.farmease.R;
import com.nyinst.farmease.model.ViewCropModel;

import java.util.ArrayList;

public class ViewCropAdapter extends RecyclerView.Adapter<com.nyinst.farmease.adapter.ViewCropAdapter.ViewHolder> {

        ArrayList<ViewCropModel> viewCropList;

        public ViewCropAdapter(ArrayList<ViewCropModel> viewCropList) {
            this.viewCropList = viewCropList;
        }

        @NonNull
        @Override
        public com.nyinst.farmease.adapter.ViewCropAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view_crop_activity, parent, false);
            return new com.nyinst.farmease.adapter.ViewCropAdapter.ViewHolder(view);
        }

    @Override
        public void onBindViewHolder(@NonNull com.nyinst.farmease.adapter.ViewCropAdapter.ViewHolder holder, int position) {
            ViewCropModel ViewCropModel = viewCropList.get(position);
            holder.seedCategoryTv.setText(ViewCropModel.getSeedCategory());
            holder.soilTypeTv.setText(ViewCropModel.getSoilType());
            holder.waterIrrigationTv.setText(ViewCropModel.getWaterIrrigation());
            holder.farmMechanizationTv.setText(ViewCropModel.getFarmMachanization());
            holder.cropProtectionTv.setText(ViewCropModel.getCropProtection());
            holder.deficiencySymptomsTv.setText(ViewCropModel.getDeficiencySymptoms());
            holder.harvestTv.setText(ViewCropModel.getHarvest());
            holder.costOfCultivationTv.setText(ViewCropModel.getCostOfCultivation());
        }

        @Override
        public int getItemCount() {
            return viewCropList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView seedCategoryTv, soilTypeTv, waterIrrigationTv, farmMechanizationTv, cropProtectionTv, deficiencySymptomsTv, harvestTv, costOfCultivationTv;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                seedCategoryTv = itemView.findViewById(R.id.seed_category);
                soilTypeTv = itemView.findViewById(R.id.soil_type);
                waterIrrigationTv = itemView.findViewById(R.id.water_irrigation);
                farmMechanizationTv = itemView.findViewById(R.id.farm_machanization);
                cropProtectionTv = itemView.findViewById(R.id.crop_protection);
                deficiencySymptomsTv = itemView.findViewById(R.id.deficiency_symptoms);
                harvestTv = itemView.findViewById(R.id.harvest);
                costOfCultivationTv = itemView.findViewById(R.id.cost_of_cultivation);
            }
        }
    }

