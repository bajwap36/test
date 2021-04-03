package com.example.briskdelivery;

public class Item {

    int orderId;
    int dishId;
    int qty;
    double price;
    int restId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

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
        this.restId= restId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public Item(int orderId, int dishId, int qty, double price,  int restId) {
        this.setOrderId(orderId);
        this.setDishId(dishId);
        this.setQty(qty);
        this.setPrice(price);
       // this.setRestriction(restriction);
        this.setRestId(restId);
    }
}
