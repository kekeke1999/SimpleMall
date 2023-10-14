package com.keke.domain;

import java.io.Serializable;

public class Address implements Serializable {
    private Integer addressId;

    private Integer userId;

    private Integer defaultStatus;

    private String name;

    private String tel;

    private String post;

    private String province;

    private String city;

    private String region;

    private String detailAddress;

    public Integer getAddressId() {
        return addressId;
    }

    public Address(Integer defaultStatus, String name, String tel, String post, String province, String city, String region, String detailAddress) {
        this.defaultStatus = defaultStatus;
        this.name = name;
        this.tel = tel;
        this.post = post;
        this.province = province;
        this.city = city;
        this.region = region;
        this.detailAddress = detailAddress;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(Integer defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", userId=" + userId +
                ", defaultStatus=" + defaultStatus +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", post='" + post + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                '}';
    }
}
