package com.rubicus.keymechapp.schemas;

import androidx.annotation.NonNull;

public class UserReg {
    public String phone_number;
    public String password;
    public String name;
    public String surname;

    public UserReg(String phone_number, String pasword, String name, String surname) {
        this.phone_number = phone_number;
        this.password = pasword;
        this.name = name;
        this.surname = surname;
    }

    @NonNull
    @Override
    public String toString() {
        return phone_number + " " + password + " " + name + " " + surname;
    }
}
