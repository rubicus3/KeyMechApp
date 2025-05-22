package com.rubicus.keymechapp;

import androidx.annotation.NonNull;

public class Switch extends Product {
    public String short_title;
    public String switch_type;
    public String actuation_force;

    public String getKeywords() {
        return switch_type + " " + actuation_force;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " " + getKeywords();
    }
}
