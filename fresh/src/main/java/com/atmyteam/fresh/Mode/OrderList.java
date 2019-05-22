package com.atmyteam.fresh.Mode;

public class OrderList {
    private int orderNo;
    private String telephone;
    private String item;
    private String ordertime;
    private double number;
    private int delivery;

    @Override
    public String toString() {
        return "OrderList{" +
                "orderNo=" + orderNo +
                ", telephone='" + telephone + '\'' +
                ", item='" + item + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", number=" + number +
                ", delivery=" + delivery +
                '}';
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public int getDelivery() {
        return delivery;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }
}
