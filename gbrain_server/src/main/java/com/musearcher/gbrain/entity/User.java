package com.musearcher.gbrain.entity;

import java.util.Date;
import javax.persistence.Table;

@Table
public class User {
    private String userName;

    private String userPassword;

    private String userAccesstoken;

    private Date userLastlogintime;

    private String userNickname;

    private String userAvatar;

    private String userGender;

    private String userEmail;

    private String userPhonenum;

    private Date userUpdatetimme;

    private Date userCreatetime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserAccesstoken() {
        return userAccesstoken;
    }

    public void setUserAccesstoken(String userAccesstoken) {
        this.userAccesstoken = userAccesstoken == null ? null : userAccesstoken.trim();
    }

    public Date getUserLastlogintime() {
        return userLastlogintime;
    }

    public void setUserLastlogintime(Date userLastlogintime) {
        this.userLastlogintime = userLastlogintime;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender == null ? null : userGender.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserPhonenum() {
        return userPhonenum;
    }

    public void setUserPhonenum(String userPhonenum) {
        this.userPhonenum = userPhonenum == null ? null : userPhonenum.trim();
    }

    public Date getUserUpdatetimme() {
        return userUpdatetimme;
    }

    public void setUserUpdatetimme(Date userUpdatetimme) {
        this.userUpdatetimme = userUpdatetimme;
    }

    public Date getUserCreatetime() {
        return userCreatetime;
    }

    public void setUserCreatetime(Date userCreatetime) {
        this.userCreatetime = userCreatetime;
    }
}