package com.keke.domain;

public class AddressDetail {
    private Integer id;

    private String name;

    private String tel;

    private String address;

    private Boolean isDefault;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public AddressDetail(Integer id, String name, String tel, String address, Boolean isDefault) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id:\"" + id +
                ",\"name:\"" + name +
                ",\"tel:\"" + tel +
                ",\"address:\"" + address +
                ",\"isDefault:\"" + isDefault +
                '}';
    }
}
