package com.nyinst.farmease.model;

public class ViewCropModel {
    String categoryId;
    String seedCategory;
    String soilType;
    String waterIrrigation;
    String farmMachanization;
    String cropProtection;
    String deficiencySymptoms;
    String harvest;
    String costOfCultivation;

    public ViewCropModel(String categoryId,String seedCategory, String soilType, String waterIrrigation, String farmMachanization, String cropProtection, String deficiencySymptoms, String harvest, String costOfCultivation) {
        this.categoryId = categoryId;
        this.seedCategory = seedCategory;
        this.soilType = soilType;
        this.waterIrrigation = waterIrrigation;
        this.farmMachanization = farmMachanization;
        this.cropProtection = cropProtection;
        this.deficiencySymptoms = deficiencySymptoms;
        this.harvest = harvest;
        this.costOfCultivation = costOfCultivation;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getSeedCategory() {
        return seedCategory;
    }

    public void setSeedCategory(String seedCategory) {
        this.seedCategory = seedCategory;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getWaterIrrigation() {
        return waterIrrigation;
    }

    public void setWaterIrrigation(String waterIrrigation) {
        this.waterIrrigation = waterIrrigation;
    }

    public String getFarmMachanization() {
        return farmMachanization;
    }

    public void setFarmMachanization(String farmMachanization) {
        this.farmMachanization = farmMachanization;
    }

    public String getCropProtection() {
        return cropProtection;
    }

    public void setCropProtection(String cropProtection) {
        this.cropProtection = cropProtection;
    }

    public String getDeficiencySymptoms() {
        return deficiencySymptoms;
    }

    public void setDeficiencySymptoms(String deficiencySymptoms) {
        this.deficiencySymptoms = deficiencySymptoms;
    }

    public String getHarvest() {
        return harvest;
    }

    public void setHarvest(String harvest) {
        this.harvest = harvest;
    }

    public String getCostOfCultivation() {
        return costOfCultivation;
    }

    public void setCostOfCultivation(String costOfCultivation) {
        this.costOfCultivation = costOfCultivation;
    }

}
