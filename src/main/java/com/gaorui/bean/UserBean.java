package com.gaorui.bean;

/**
 * Created by gaorui on 16/10/15.
 */
public class UserBean {
    private int uid;
    private String login_name;
    private String avatar;
    private String email;
    private int create_time;
    private int update_time;

    public int getStatus() {
        return status;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAvatar() {

        return avatar;
    }

    public int getUid() {
        return uid;
    }

    public String getLogin_name() {
        return login_name;
    }

    public String getEmail() {
        return email;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public int getCreate_time() {
        return create_time;
    }

    private int role_id;
    private int status;
}
