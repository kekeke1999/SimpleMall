package com.keke.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UndeliveredOrder implements Serializable {

    private Integer orderId;

    private String username;

    private Integer userId;

    private String createTime;

    private BigDecimal price;

    private String status;

    private String address;

    private String payTime;

    private String payWay;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public UndeliveredOrder(Integer orderId, String username, Integer userId, String createTime, BigDecimal price, String status, String address, String payTime, String payWay) {
        this.orderId = orderId;
        this.username = username;
        this.userId = userId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.address = address;
        this.payTime = payTime;
        this.payWay = payWay;
    }
}
