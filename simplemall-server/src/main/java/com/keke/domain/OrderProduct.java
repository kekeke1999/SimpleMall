package com.keke.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderProduct implements Serializable {

    private String productName;
    private String coverImg;
    private BigDecimal price;
    private Integer num;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "productName='" + productName + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }
}
