package com.rubicus.keymechapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rubicus.keymechapp.CartViewModel;
import com.rubicus.keymechapp.R;
import com.rubicus.keymechapp.SharedPreferencesManager;
import com.rubicus.keymechapp.helper.KeyMechServiceGenerator;
import com.rubicus.keymechapp.schemas.Order;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderPlacementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderPlacementFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderPlacementFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderPlacementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderPlacementFragment newInstance(String param1, String param2) {
        OrderPlacementFragment fragment = new OrderPlacementFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_placement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textAddress = view.findViewById(R.id.edit_order_address);
        TextView textTotalSum = view.findViewById(R.id.text_total_price);

        CartViewModel cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);

        textTotalSum.setText(String.format("%.2f", cartViewModel.getTotalPrice()) + " ₽");

        view.findViewById(R.id.button_pay).setOnClickListener(v -> {

            String token = SharedPreferencesManager.getInstance(getContext()).getToken();
            Order order = new Order();
            order.id = 0;
            order.user_id = 0;
            order.address = textAddress.getText().toString();
            order.total_sum = cartViewModel.getTotalPrice();
            KeyMechServiceGenerator.service.createOrder(token, order).enqueue(new Callback<Order>() {
                @Override
                public void onResponse(Call<Order> call, Response<Order> response) {
                    getParentFragmentManager().beginTransaction().replace(R.id.main_navigation_layout, AccountPageFragment.class, null).commit();
                    Toast.makeText(getContext(), "Заказ успешно создан", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Order> call, Throwable t) {

                }
            });

            cartViewModel.clearCart();
            
        });
    }
}