package com.atmyteam.fresh.Mode;
import org.springframework.stereotype.Component;

@Component
public class Goods {
    private Integer item;  //商品的ID
    private String name;   //商品的名字
    private Double price;  //商品的单价
    private String type;   //商品的类型
    private String content;  //商品的介绍
    private Integer shelves;  //商品的上架状态
    private Integer sales;   //商品的销量

    @Override
    public String toString() {
        return "Goods{" +
                "item=" + item +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", shelves=" + shelves +
                ", sales=" + sales +
                '}';
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getShelves() {
        return shelves;
    }

    public void setShelves(Integer shelves) {
        this.shelves = shelves;
    }
}

