package com.rubicus.keymechapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rubicus.keymechapp.schemas.Product;

import java.util.HashMap;
import java.util.Map;

public class CartViewModel extends ViewModel {
    private static CartViewModel instance;
    private MutableLiveData<Map<String, Product>> cartItems;
    private CartViewModel() {
        cartItems = new MutableLiveData<>(new HashMap<>());
    }

    public LiveData<Map<String, Product>> getCartItems() {
        return cartItems;
    }

    public void addToCart(Product product)  {
        Log.d("TAG", "addToCart: " + product);
        Map<String, Product> currentItems = cartItems.getValue();
        if (currentItems != null) {
            Log.d("TAG", "addToCart: put  " + product);

            currentItems.put(product.title, product);
            cartItems.setValue(currentItems);
        }
    }

    public void removeFromCart(Product product) {
        Map<String, Product> currentItems = cartItems.getValue();
        if (currentItems != null) {
            currentItems.remove(product.title);
            cartItems.setValue(currentItems);
        }
    }



    public void clearCart() {
        Map<String, Product> currentItems = cartItems.getValue();
        if(currentItems != null) {
            currentItems.clear();
            cartItems.setValue(currentItems);
        }
    }

    public Float getTotalPrice() {
        Float total = .0f;
        Map<String, Product> currentItems = cartItems.getValue();
        if (currentItems != null) {
            for (Product item : currentItems.values()) {
                total += item.getPriceRubles() * item.amount;
            }
        }
        return total;
    }

}