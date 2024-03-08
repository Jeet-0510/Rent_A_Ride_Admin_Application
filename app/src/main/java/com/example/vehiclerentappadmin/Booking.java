package com.example.vehiclerentappadmin;

public class Booking {
    String AdminMobileNo;
    String UserMobileNo;
    String CarImage;
    String CarName;
    String CarPrice;
    String key;

    public Booking(){}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAdminMobileNo() {
        return AdminMobileNo;
    }

    public void setAdminMobileNo(String adminMobileNo) {
        AdminMobileNo = adminMobileNo;
    }

    public String getUserMobileNo() {
        return UserMobileNo;
    }

    public void setUserMobileNo(String userMobileNo) {
        UserMobileNo = userMobileNo;
    }

    public String getCarImage() {
        return CarImage;
    }

    public void setCarImage(String carImage) {
        CarImage = carImage;
    }

    public String getCarName() {
        return CarName;
    }

    public void setCarName(String carName) {
        CarName = carName;
    }

    public String getCarPrice() {
        return CarPrice;
    }

    public void setCarPrice(String carPrice) {
        CarPrice = carPrice;
    }

    public String getCarHour() {
        return CarHour;
    }

    public void setCarHour(String carHour) {
        CarHour = carHour;
    }

    public Booking(String adminMobileNo, String userMobileNo, String carImage, String carName, String carPrice, String carHour) {
        AdminMobileNo = adminMobileNo;
        UserMobileNo = userMobileNo;
        CarImage = carImage;
        CarName = carName;
        CarPrice = carPrice;
        CarHour = carHour;
    }

    String CarHour;

}
