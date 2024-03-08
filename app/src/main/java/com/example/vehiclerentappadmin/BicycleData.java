package com.example.vehiclerentappadmin;

public class BicycleData {
    private String bicycleName;
    private String bicycleDesc;
    private String bicycleLocation;
    private String bicycleRupees;
    private String bicycleImage;
    private String bicycleKey;

    public BicycleData(){}

    public BicycleData(String bicycleName, String bicycleDesc, String bicycleLocation, String bicycleRupees, String bicycleImage) {
        this.bicycleName = bicycleName;
        this.bicycleDesc = bicycleDesc;
        this.bicycleLocation = bicycleLocation;
        this.bicycleRupees = bicycleRupees;
        this.bicycleImage = bicycleImage;
    }

    public void setBicycleName(String bicycleName) {
        this.bicycleName = bicycleName;
    }

    public void setBicycleDesc(String bicycleDesc) {
        this.bicycleDesc = bicycleDesc;
    }

    public void setBicycleLocation(String bicycleLocation) {
        this.bicycleLocation = bicycleLocation;
    }

    public void setBicycleRupees(String bicycleRupees) {
        this.bicycleRupees = bicycleRupees;
    }

    public void setBicycleImage(String bicycleImage) {
        this.bicycleImage = bicycleImage;
    }

    public void setBicycleKey(String bicycleKey) {
        this.bicycleKey = bicycleKey;
    }

    public String getBicycleName() {
        return bicycleName;
    }

    public String getBicycleDesc() {
        return bicycleDesc;
    }

    public String getBicycleLocation() {
        return bicycleLocation;
    }

    public String getBicycleRupees() {
        return bicycleRupees;
    }

    public String getBicycleImage() {
        return bicycleImage;
    }

    public String getBicycleKey() {
        return bicycleKey;
    }

}
