package com.example.briskdelivery;

public class Restaurant {

    int restId;
    String restName;
    int restImage;

    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public int getRestImage() {
        return restImage;
    }

    public void setRestImage(int restImage) {
        this.restImage = restImage;
    }

    public Restaurant(int restId, String restName, int restImage) {
        this.setRestId(restId);
        this.setRestName(restName);
        this.setRestImage(restImage);
    }
    public Restaurant(String restName, int restImage) {
        //this.setRestId(restId);
        this.setRestName(restName);
        this.setRestImage(restImage);
    }
}
