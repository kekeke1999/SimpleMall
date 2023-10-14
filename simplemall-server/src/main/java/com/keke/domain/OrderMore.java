package com.keke.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderMore implements Serializable {

    private Integer orderId;

    private String username;

    private Integer userId;

    @JSONField(format ="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private BigDecimal price;

    private Integer status;

    private Integer addressId;

    private Date payTime;

    private String payWay;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderMore{" +
                "orderId=" + orderId +
                ", username='" + username + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", addressId=" + addressId +
                ", payTime=" + payTime +
                ", payWay='" + payWay + '\'' +
                '}';
    }
}
