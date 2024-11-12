package com.nyinst.farmease.model;

public class MyComplaintModel {
    int id;
    String createdBy;
    String cropName;
    String cropType;
    String currentDuration;
    String soilType;
    String diseaseDetails;
    String previousHistory;
    String symptoms;
    String status;
    String reason;
    String medicines;
    String solution;

    public MyComplaintModel(int id, String createdBy,String cropName, String cropType, String currentDuration, String soilType, String diseaseDetails, String previousHistory, String symptoms, String status,String reason, String medicines, String solution) {
        this.id = id;
        this.createdBy = createdBy;
        this.cropName = cropName;
        this.cropType = cropType;
        this.currentDuration = currentDuration;
        this.soilType = soilType;
        this.diseaseDetails = diseaseDetails;
        this.previousHistory = previousHistory;
        this.symptoms = symptoms;
        this.status = status;
        this.reason = reason;
        this.medicines = medicines;
        this.solution = solution;
    }

    public String getCreateBy() {
        return createdBy;
    }

    public String getCropName() { return cropName; }
    public void setCropName(String cropName) {this.cropName = cropName; }


    public String getCropType() { return cropType; }
    public void setCropType(String cropType) {this.cropType = cropType; }

    public String getCurrentDuration() {
        return currentDuration;
    }
    public void setCurrentDuration(String currentDuration) {this.currentDuration = currentDuration; }

    public String getSoilType() {
        return soilType;
    }
    public void setSoilType(String soilType) {this.soilType = soilType; }

    public String getDiseaseDetails() {
        return diseaseDetails;
    }
    public void setDiseaseDetails(String diseaseDetails) {this.diseaseDetails = diseaseDetails; }

    public String getPreviousHistory() {
        return previousHistory;
    }
    public void setPreviousHistory(String previousHistory) {this.previousHistory = previousHistory; }

    public String getSymptoms() {
        return symptoms;
    }
    public void setSymptoms(String symptoms) {this.symptoms = symptoms; }

    public String getStatus() {return status;}

    public String getReason() {
        return reason;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getSolution() {
        return solution;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

