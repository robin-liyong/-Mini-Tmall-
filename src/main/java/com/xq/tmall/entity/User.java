package com.xq.tmall.entity;

import java.util.Date;
import java.util.List;

public class User {
    private Integer user_id;
    private String user_name;
    private String user_nickname;
    private String user_password;
    private String user_realname;
    private Byte user_gender;
    private Date user_birthday;
    private Address user_address;
    private Address user_homeplace;
    private String user_profile_picture_src;
    private List<Review> reviewList;

    public User() {
    }

    public User(Integer user_id, String user_name, String user_nickname, String user_password, Byte user_gender) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_nickname = user_nickname;
        this.user_password = user_password;
        this.user_gender = user_gender;
    }

    public User(Integer user_id, String user_name, String user_nickname, String user_password, String user_realname, Byte user_gender, Date user_birthday, Address user_address, Address user_homeplace, String user_profile_picture_src, List<Review> reviewList) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_nickname = user_nickname;
        this.user_password = user_password;
        this.user_realname = user_realname;
        this.user_gender = user_gender;
        this.user_birthday = user_birthday;
        this.user_address = user_address;
        this.user_homeplace = user_homeplace;
        this.user_profile_picture_src = user_profile_picture_src;
        this.reviewList = reviewList;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_realname() {
        return user_realname;
    }

    public void setUser_realname(String user_realname) {
        this.user_realname = user_realname;
    }

    public Byte getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(Byte user_gender) {
        this.user_gender = user_gender;
    }

    public Date getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(Date user_birthday) {
        this.user_birthday = user_birthday;
    }

    public Address getUser_address() {
        return user_address;
    }

    public void setUser_address(Address user_address) {
        this.user_address = user_address;
    }

    public Address getUser_homeplace() {
        return user_homeplace;
    }

    public void setUser_homeplace(Address user_homeplace) {
        this.user_homeplace = user_homeplace;
    }

    public String getUser_profile_picture_src() {
        return user_profile_picture_src;
    }

    public void setUser_profile_picture_src(String user_profile_picture_src) {
        this.user_profile_picture_src = user_profile_picture_src;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
