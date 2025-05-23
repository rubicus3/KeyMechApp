package com.rubicus.keymechapp.schemas;

import java.util.HashMap;

interface ProductMethods {
    String getKeywords();

}

public abstract class Product implements ProductMethods{
    public Integer id;
    public String title;
    public String manufacturer;
    public Float price;
    public String description;
    public HashMap<String, String> characteristics;
    public String image_name;

    public String getPrice() {
        return String.format("%.2f", price * 80.63) + " ₽";
    }
}
