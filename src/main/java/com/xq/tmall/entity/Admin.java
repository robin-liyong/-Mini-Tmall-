package com.xq.tmall.entity;

/**
 * 管理员实体类
 * @author 贤趣项目小组
 */
public class Admin {
    private Integer admin_id/*管理员ID*/;

    private String admin_name/*管理员登录名*/;

    private String admin_nickname/*管理员昵称*/;

    private String admin_password/*管理员密码*/;

    private String admin_profile_picture_src/*管理员头像路径*/;

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", admin_name='" + admin_name + '\'' +
                ", admin_nickname='" + admin_nickname + '\'' +
                ", admin_password='" + admin_password + '\'' +
                ", admin_profile_picture_src='" + admin_profile_picture_src + '\'' +
                '}';
    }

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

    public Admin setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
        return this;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public Admin setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
        return this;
    }

    public String getAdmin_nickname() {
        return admin_nickname;
    }

    public Admin setAdmin_nickname(String admin_nickname) {
        this.admin_nickname = admin_nickname;
        return this;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public Admin setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
        return this;
    }

    public String getAdmin_profile_picture_src() {
        return admin_profile_picture_src;
    }

    public Admin setAdmin_profile_picture_src(String admin_profile_picture_src) {
        this.admin_profile_picture_src = admin_profile_picture_src;
        return this;
    }
}
