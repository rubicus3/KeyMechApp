package com.rubicus.keymechapp;

public class Keyboard extends Product {
    public String form_factor;

    @Override
    public String getKeywords() {
        return manufacturer + " " + form_factor;
    }
}