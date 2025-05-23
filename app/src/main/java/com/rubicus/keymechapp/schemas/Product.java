package com.rubicus.keymechapp.schemas;

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

    public String getPrice() {
        return String.format("%.2f", price * 80.63) + " ₽";
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
}
