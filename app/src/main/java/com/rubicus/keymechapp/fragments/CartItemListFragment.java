package com.rubicus.keymechapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rubicus.keymechapp.CartViewModel;
import com.rubicus.keymechapp.adatpers.CartItemListRecyclerViewAdapter;
import com.rubicus.keymechapp.R;

import com.rubicus.keymechapp.schemas.Product;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class CartItemListFragment extends Fragment implements CartItemListRecyclerViewAdapter.OnItemChangeListener {

    private ArrayList<Product> productArrayList = new ArrayList<>(){};

    private CartViewModel cartViewModel;
    private CartItemListRecyclerViewAdapter cartItemListRecyclerViewAdapter;
    public CartItemListFragment() {
    }

    public static CartItemListFragment newInstance() {
        return new CartItemListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_item_list, container, false);
        cartItemListRecyclerViewAdapter = new CartItemListRecyclerViewAdapter(productArrayList, this);

        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(cartItemListRecyclerViewAdapter);

        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);

        cartViewModel.getCartItems().observe(getViewLifecycleOwner(), productsMap -> {
            productArrayList.clear();
            productArrayList.addAll(productsMap.values());
            TextView tgd = view.findViewById(R.id.text_total_price);

            tgd.setText(String.format("%.2f",cartViewModel.getTotalPrice()) + " ₽");
            cartItemListRecyclerViewAdapter.notifyDataSetChanged();
        });

        view.findViewById(R.id.button_go_pay).setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().replace(R.id.main_navigation_layout, OrderPlacementFragment.class, null).commit();
        });

        return view;
    }

    @Override
    public void onItemRemove(Product product) {
        cartViewModel.removeFromCart(product);
    }

    @Override
    public void onItemAmountAdd(Product product) {
        product.setAmount(product.amount + 1);
        cartViewModel.addToCart(product);

    }

    @Override
    public void onItemAmountSubtract(Product product) {
        product.setAmount(product.amount - 1);
        cartViewModel.addToCart(product);
    }
}