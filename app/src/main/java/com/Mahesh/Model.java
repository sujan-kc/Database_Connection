package com.Mahesh;

import java.io.Serializable;

public class Model implements Serializable {
    int id;
    String name,contact,password,confirmpassword;

    public Model(int id, String name, String contact, String password) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.password = password;
       // this.confirmpassword=confirmpassword;
    }

    public Model(int id, String s, String name, String contact, String password) {
        this.name = name;
        this.contact = contact;
        this.password = password;
        //this.confirmpassword=confirmpassword;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//    public String getConfirmpassword() {
//        return password;
//    }
//
//    public void setConfirmpassword(String password) {
//        this.password = password;
//    }

}
