package com.rubicus.keymechapp.schemas;

import androidx.annotation.NonNull;

import java.util.HashMap;

interface ProductMethods {
    String getKeywords();

}

public class Product implements ProductMethods{
    public Integer id;
    public String title;
    public String manufacturer;
    public Float price;
    public String description;
    public HashMap<String, String> characteristics;
    public String image_name;
    public Integer amount = 1;

    public Float getPriceRubles() {
        return price * 80.63f;
    }
    public String getPriceString() {
        return String.format("%.2f", getPriceRubles()) + " ₽";
    }

    @Override
    public String getKeywords() {
        return manufacturer;
    }

    public void setAmount (Integer amount) {
        if(amount < 1) {
            this.amount = 1;
        }
        else {
            this.amount = amount;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
