package com.example.vehiclerentappadmin;

public class ScooterData {
    private String scooterName;
    private String scooterDesc;
    private String scooterLocation;
    private String scooterRupees;
    private String scooterImage;
    private String scooterKey;

    public ScooterData() {
    }

    public ScooterData(String scooterName, String scooterDesc, String scooterLocation, String scooterRupees, String scooterImage) {
        this.scooterName = scooterName;
        this.scooterDesc = scooterDesc;
        this.scooterLocation = scooterLocation;
        this.scooterRupees = scooterRupees;
        this.scooterImage = scooterImage;
    }

    public String getScooterName() {
        return scooterName;
    }

    public void setScooterName(String scooterName) {
        this.scooterName = scooterName;
    }

    public String getScooterDesc() {
        return scooterDesc;
    }

    public void setScooterDesc(String scooterDesc) {
        this.scooterDesc = scooterDesc;
    }

    public String getScooterLocation() {
        return scooterLocation;
    }

    public void setScooterLocation(String scooterLocation) {
        this.scooterLocation = scooterLocation;
    }

    public String getScooterRupees() {
        return scooterRupees;
    }

    public void setScooterRupees(String scooterRupees) {
        this.scooterRupees = scooterRupees;
    }

    public String getScooterImage() {
        return scooterImage;
    }

    public void setScooterImage(String scooterImage) {
        this.scooterImage = scooterImage;
    }

    public String getScooterKey() {
        return scooterKey;
    }

    public void setScooterKey(String scooterKey) {
        this.scooterKey = scooterKey;
    }
}
