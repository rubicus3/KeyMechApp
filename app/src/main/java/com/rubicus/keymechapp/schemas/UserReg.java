package com.rubicus.keymechapp.schemas;

public class UserReg {
    public String phone_number;
    public String pasword;
    public String name;
    public String surname;

    public UserReg(String phone_number, String pasword, String name, String surname) {
        this.phone_number = phone_number;
        this.pasword = pasword;
        this.name = name;
        this.surname = surname;
    }
}
