package com.example.briskdelivery;

public class Dish {

    int dishId;
    int restId;
    String title;
    String description;
    Double price;

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Dish(int dishId, int restId, String title, String description, Double price) {
        this.setDishId(dishId);
        this.setRestId(restId);
        this.setTitle(title);
        this.setDescription(description);
        this.setPrice(price);
    }

    public Dish(int restId, String title, String description, Double price) {
        this.setRestId(restId);
        this.setTitle(title);
        this.setDescription(description);
        this.setPrice(price);
    }
}
