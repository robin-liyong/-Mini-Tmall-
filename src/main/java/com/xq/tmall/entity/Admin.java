package com.xq.tmall.entity;

public class Admin {
    private Integer admin_id;
    private String admin_name;
    private String admin_nickname;
    private String admin_password;
    private String admin_profile_picture_src;

    public Admin(){

    }

    public Admin(Integer admin_id, String admin_name, String admin_nickname, String admin_password) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_nickname = admin_nickname;
        this.admin_password = admin_password;
    }

    public Admin(Integer admin_id, String admin_name, String admin_nickname, String admin_password, String admin_profile_picture_src) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_nickname = admin_nickname;
        this.admin_password = admin_password;
        this.admin_profile_picture_src = admin_profile_picture_src;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_nickname() {
        return admin_nickname;
    }

    public void setAdmin_nickname(String admin_nickname) {
        this.admin_nickname = admin_nickname;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public String getAdmin_profile_picture_src() {
        return admin_profile_picture_src;
    }

    public void setAdmin_profile_picture_src(String admin_profile_picture_src) {
        this.admin_profile_picture_src = admin_profile_picture_src;
    }
}
