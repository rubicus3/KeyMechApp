package com.rubicus.keymechapp;

import com.rubicus.keymechapp.schemas.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartManager {
    private static CartManager instance;
    private Map<String, Product> cartItems;
    private CartManager() {
        cartItems = new HashMap<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(Product product) {
        cartItems.put(product.title, product);
    }

    public void removeFromCart(Product product) {
        cartItems.remove(product.title);
    }

    public List<Product> getCartItems() {
        return new ArrayList<>(cartItems.values());
    }

    public void clearCart() {
        cartItems.clear();
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product item : cartItems.values()) {
            total += item.price * item.amount;
        }
        return total;
    }
}