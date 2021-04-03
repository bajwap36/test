package com.example.briskdelivery;

import java.text.DecimalFormat;

public class Orders {

    int orderId;
    int userId;
    String date;
    boolean status; //0=open order -> 1=placed
    boolean type; //0=pickup -> 1=delivery
    double total = 0;
    double delivery = 0; //fee
    double tax = 0;
    String payment;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int isStatus() {
        if(status) return  1;
        else return 0;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int isType() {
        if (type) return 1;
        else return 0;
    }

    public String formatCurrency(double value){
        DecimalFormat df = new DecimalFormat("$###.##");
        return df.format(value);
    }

    public static String formatCurrency(float value){
        DecimalFormat df = new DecimalFormat("$###.##");
        return df.format(value);
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDelivery() {
        return delivery;
    }

    public void setDelivery(double delivery) {
        this.delivery = delivery;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
    public void setTax() {
        this.tax = (total * 0.06);
        // + delivery
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getOrderTotal(){
        return this.formatCurrency(this.total + this.tax + this.delivery);
    }

    public Orders(int orderId, int userId, String date, boolean status, boolean type, double total, double delivery, double tax, String payment) {
        this.setOrderId(orderId);
        this.setUserId(userId);
        this.setDate(date);
        this.setStatus(status);
        this.setType(type);
        this.setTotal(total);
        this.setDelivery(delivery);
        this.setTax(tax);
        this.setPayment(payment);
    }


    public Orders(int userId, boolean status){
        this.setUserId(userId);
        this.setStatus(status);
    }

    public Orders(){

    }

    @Override
    public String toString() {
        return
                "Delivery? " + (type ? "Yes" : "No") +
                "\nPayment Method " + payment
                ;
    }
}
