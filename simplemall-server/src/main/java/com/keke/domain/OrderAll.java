package com.keke.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderAll implements Serializable {

    private Integer userId;

    private Integer orderId;

    private String name;

    private String tel;

    private String province;

    private String city;

    private String region;

    private String detailAddress;

    private List<OrderProduct> products;

    private Date createTime;

    private Date payTime;

    private String payWay;

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public OrderAll(Integer userId, Integer orderId, String name, String tel, String province, String city, String region, String detailAddress, List<OrderProduct> products, Date createTime, Date payTime, String payWay, BigDecimal price) {
        this.userId = userId;
        this.orderId = orderId;
        this.name = name;
        this.tel = tel;
        this.province = province;
        this.city = city;
        this.region = region;
        this.detailAddress = detailAddress;
        this.products = products;
        this.createTime = createTime;
        this.payTime = payTime;
        this.payWay = payWay;
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderAll{" +
                "userId=" + userId +
                ", orderId=" + orderId +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", products=" + products +
                ", createTime=" + createTime +
                ", payTime=" + payTime +
                ", payWay='" + payWay + '\'' +
                ", price=" + price +
                '}';
    }
}
