package com.example.vehiclerentappadmin;

public class BikeData {

    public BikeData(){}
    public BikeData(String bikeName, String bikeDesc, String bikeLocation, String bikeRupees, String bikeImage) {
        this.bikeName = bikeName;
        this.bikeDesc = bikeDesc;
        this.bikeLocation = bikeLocation;
        this.bikeRupees = bikeRupees;
        this.bikeImage = bikeImage;
    }

    private String bikeName;
    private String bikeDesc;
    private String bikeLocation;
    private String bikeRupees;
    private String bikeImage;
    private String bikeKey;

    public String getBikeName() {
        return bikeName;
    }

    public String getBikeDesc() {
        return bikeDesc;
    }

    public String getBikeLocation() {
        return bikeLocation;
    }

    public String getBikeRupees() {
        return bikeRupees;
    }

    public String getBikeImage() {
        return bikeImage;
    }

    public String getBikeKey() {
        return bikeKey;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public void setBikeDesc(String bikeDesc) {
        this.bikeDesc = bikeDesc;
    }

    public void setBikeLocation(String bikeLocation) {
        this.bikeLocation = bikeLocation;
    }

    public void setBikeRupees(String bikeRupees) {
        this.bikeRupees = bikeRupees;
    }

    public void setBikeImage(String bikeImage) {
        this.bikeImage = bikeImage;
    }

    public void setBikeKey(String bikeKey) {
        this.bikeKey = bikeKey;
    }
}
