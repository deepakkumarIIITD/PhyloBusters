package com.example.projectz20;

import java.util.Date;

public class helperclass {

    String name,username,email,phoneno,password,datetime;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public helperclass(String NAME , String USERNAME , String EMAIL, String PHONE , String PASSWORD){
        this.name = NAME;
        this.username = USERNAME;
        this.email = EMAIL;
        this.phoneno = PHONE;
        this.password = PASSWORD;
        Date date = new Date();
        datetime = date.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}