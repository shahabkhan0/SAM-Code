package com.vc.technologies.sam.models;

public class UserModel {
    String name, email, password, image, uid,mobileNo;
    int accountType;  //0=User, 1=Butcher,2=Doctor

    public UserModel() {

    }

    public UserModel(String name, String email, String password, String image, String uid, String mobileNo, int accountType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
        this.uid = uid;
        this.mobileNo = mobileNo;
        this.accountType = accountType;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}

