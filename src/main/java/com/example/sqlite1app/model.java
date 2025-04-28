package com.example.sqlite1app;

import java.io.Serializable;

public class model implements Serializable {

    int id;
    String Name;
    String Phone;
    String Role;
    String Company;

    public model() {

    }


    public model(String name, String phone, String role, String company) {
        Name = name;
        Phone = phone;
        Role = role;
        Company = company;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }

    public String getRole() {
        return Role;
    }

    public String getCompany() {
        return Company;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setRole(String role) {
        Role = role;
    }

    public void setCompany(String company) {
        Company = company;
    }
}
