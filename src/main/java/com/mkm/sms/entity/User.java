package com.mkm.sms.entity;


import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class User {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNum;

    protected User() {}

    public User(String name, String email, String phoneNum) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phoneNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone_num) {
        this.phoneNum = phone_num;
    }

    @Override
    public String toString() {
        return "User{ " +
                "Name: " + name +
                " | E-mail: " + email +
                " | Phone number: "  + phoneNum + " }";
    }
}
