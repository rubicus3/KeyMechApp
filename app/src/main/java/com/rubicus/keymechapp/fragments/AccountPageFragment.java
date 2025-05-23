package com.rubicus.keymechapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rubicus.keymechapp.LoginActivity;
import com.rubicus.keymechapp.MainActivity;
import com.rubicus.keymechapp.R;
import com.rubicus.keymechapp.SharedPreferencesManager;
import com.rubicus.keymechapp.helper.KeyMechServiceGenerator;
import com.rubicus.keymechapp.schemas.Order;
import com.rubicus.keymechapp.schemas.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountPageFragment extends Fragment {


    public AccountPageFragment() {
        // Required empty public constructor
    }

    public static AccountPageFragment newInstance(String param1, String param2) {
        return new AccountPageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance(getContext());
        TextView nameView = view.findViewById(R.id.text_account_name);
        TextView numberView = view.findViewById(R.id.text_account_number);

        User user = sharedPreferencesManager.getCurrentUser();
        nameView.setText(user.getFullName());
        numberView.setText(user.phone_number);

        view.findViewById(R.id.button_logout).setOnClickListener(v -> {
            sharedPreferencesManager.clearToken();

            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        });

        String token = SharedPreferencesManager.getInstance(getContext()).getToken();
        LinearLayout historyLayout = view.findViewById(R.id.layout_order_history);


        KeyMechServiceGenerator.service.getOrderList(token).enqueue(new Callback<ArrayList<Order>>() {
            @Override
            public void onResponse(Call<ArrayList<Order>> call, Response<ArrayList<Order>> response) {
                for (Order i : response.body()) {
                    LayoutInflater inflater1 = LayoutInflater.from(requireContext());
                    View historyView = inflater1.inflate(R.layout.order_history_item, historyLayout, true);

                    TextView textDate = historyView.findViewById(R.id.text_order_history_date);
                    TextView textAddress = historyView.findViewById(R.id.text_order_history_address);
                    TextView textTotalPrice = historyView.findViewById(R.id.text_order_history_total_price);

                    textDate.setText(i.date);
                    textAddress.setText(i.address);
                    textTotalPrice.setText(i.total_sum.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Order>> call, Throwable t) {

            }
        });


    }
}