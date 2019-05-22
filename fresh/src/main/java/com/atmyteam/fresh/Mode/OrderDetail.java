package com.atmyteam.fresh.Mode;

public class OrderDetail {
    private Integer orderNo;
    private String  ordertime;
    private Double amount;
    private Integer delivery;
    private Integer item;
    private String goodsname;
    private String username;
    private String telephone;
    private String address;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderNo=" + orderNo +
                ", ordertime='" + ordertime + '\'' +
                ", amount=" + amount +
                ", delivery=" + delivery +
                ", item=" + item +
                ", goodsname='" + goodsname + '\'' +
                ", username='" + username + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getDelivery() {
        return delivery;
    }

    public void setDelivery(Integer delivery) {
        this.delivery = delivery;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
