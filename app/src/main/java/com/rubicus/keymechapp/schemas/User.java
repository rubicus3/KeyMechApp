package com.rubicus.keymechapp.schemas;

import java.io.Serializable;

public class User implements Serializable {
    public Integer id;
    public String phone_number;
    public String name;
    public String surname;

    public String getFullName() {
        return name + " " + surname;
    }
}
