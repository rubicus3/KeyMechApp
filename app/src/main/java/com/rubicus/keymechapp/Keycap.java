package com.rubicus.keymechapp;

public class Keycap extends Product {
    public String material;

    @Override
    public String getKeywords() {
        return manufacturer + " " + material;
    }
}
