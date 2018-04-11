package com.Entity;

import java.io.Serializable;
import com.sun.xml.internal.ws.util.StringUtils;

public class User implements Serializable {

    /**
     * primary key of User
     */
    private int idUser;
    private String name;
    private String lastName;
    private String username;
    private transient String password;
    private String officeNumber;
    private String userPhone;
    private String mail;
    private String role;

    public User(){}
    public User(String name, String lastName, String username, String password, String officeNumber, String userPhone, String mail, String role) {
        this.name = StringUtils.capitalize(name);
        this.lastName = StringUtils.capitalize(lastName);
        this.username = username;
        this.password = password;
        this.officeNumber = officeNumber;
        this.userPhone = userPhone;
        this.mail = mail;
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
