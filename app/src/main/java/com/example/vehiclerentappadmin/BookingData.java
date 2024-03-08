package com.example.vehiclerentappadmin;

public class BookingData {
    String Admin, Customer, VehicleName, Time, Rupees, Image, key;

    public BookingData(){}

    public BookingData(String admin, String customer, String vehicleName, String time, String rupees, String image) {
        Admin = admin;
        Customer = customer;
        VehicleName = vehicleName;
        Time = time;
        Rupees = rupees;
        Image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getAdmin() {
        return Admin;
    }

    public void setAdmin(String admin) {
        Admin = admin;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public String getVehicleName() {
        return VehicleName;
    }

    public void setVehicleName(String vehicleName) {
        VehicleName = vehicleName;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getRupees() {
        return Rupees;
    }

    public void setRupees(String rupees) {
        Rupees = rupees;
    }
}
