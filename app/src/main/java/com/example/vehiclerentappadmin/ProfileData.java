package com.example.vehiclerentappadmin;

public class ProfileData{
    String name;
    String photo;
    String email;
    String phoneNo;
    String address;

    public ProfileData() {
    }

    public ProfileData(String name, String photo, String email, String phoneNo, String address) {
        this.name = name;
        this.photo = photo;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
