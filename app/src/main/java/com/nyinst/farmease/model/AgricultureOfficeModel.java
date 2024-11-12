package com.nyinst.farmease.model;

public class AgricultureOfficeModel {
    String name;
    String category;
    String address;
    String location;
    String pincode;
    String mobile;

    public AgricultureOfficeModel(String name,String category, String address, String location, String pincode, String mobile) {

        this.name = name;
        this.category = category;
        this.address = address;
        this.location = location;
        this.pincode = pincode;
        this.mobile = mobile;
    }

    public String getName() { return name; }
    public void setName(String name) {this.name = name; }


    public String getCategory() { return category; }
    public void setCategory(String category) {this.category = category; }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {this.address = address; }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {this.location = location; }

    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {this.pincode = pincode; }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {this.mobile = mobile; }
}
