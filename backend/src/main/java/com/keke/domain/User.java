package com.keke.domain;

import java.io.Serializable;

public class User implements Serializable {

    private Integer userId;

    private String username;

    private String password;

    private Integer identify;

    private String phoneNumber;

    private String intro;

    private String sex;

    public User(Integer userId, String username, String password, String phoneNumber, String intro, String sex) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.intro = intro;
        this.sex = sex;
    }

    public User() {

    }

    public User(Integer userId, String username, String password, Integer identify, String phoneNumber, String intro, String sex) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.identify = identify;
        this.phoneNumber = phoneNumber;
        this.intro = intro;
        this.sex = sex;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getIdentify() {
        return identify;
    }

    public void setIdentify(Integer identify) {
        this.identify = identify;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", identify=" + identify +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", intro='" + intro + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
