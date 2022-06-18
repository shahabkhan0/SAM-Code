package com.vc.technologies.sam.models;

public class PostModel {
    String image,name,price,userUid;

    public PostModel() {
    }

    public PostModel(String image, String name, String price, String userUid) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.userUid = userUid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }
}
