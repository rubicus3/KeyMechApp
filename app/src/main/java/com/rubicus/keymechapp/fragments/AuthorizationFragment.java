package com.rubicus.keymechapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.rubicus.keymechapp.LoginActivity;
import com.rubicus.keymechapp.MainActivity;
import com.rubicus.keymechapp.R;
import com.rubicus.keymechapp.SharedPreferencesManager;
import com.rubicus.keymechapp.helper.KeyMechServiceGenerator;
import com.rubicus.keymechapp.schemas.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AuthorizationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuthorizationFragment extends Fragment {


    public AuthorizationFragment() {

    }

    public static AuthorizationFragment newInstance(String param1, String param2) {

        return new AuthorizationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authorization, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button_login).setOnClickListener(v -> {
            EditText editNumber = (EditText) view.findViewById(R.id.input_phone);
            EditText editPassword = (EditText) view.findViewById(R.id.input_password);

            String number = editNumber.getText().toString();
            String password = editPassword.getText().toString();

            Log.d("TAG", "onViewCreated: " + number + " "+ password);

            KeyMechServiceGenerator.service.authorize("password", number, password).enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    Token token = response.body();
                    if(token != null) {
                        SharedPreferencesManager.getInstance(getContext()).setToken(token);
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    if(!response.isSuccessful()) {
                        Toast.makeText(getContext(), "bdfssbdf", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        });
    }
}