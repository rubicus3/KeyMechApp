package com.rubicus.keymechapp.fragments;

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

import com.rubicus.keymechapp.MainActivity;
import com.rubicus.keymechapp.R;
import com.rubicus.keymechapp.SharedPreferencesManager;
import com.rubicus.keymechapp.helper.KeyMechServiceGenerator;
import com.rubicus.keymechapp.schemas.Token;
import com.rubicus.keymechapp.schemas.UserReg;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment {


    public RegistrationFragment() {
        // Required empty public constructor
    }


    public static RegistrationFragment newInstance(String param1, String param2) {

        return new RegistrationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_authorization_link).setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().replace(R.id.login_layout_manager, AuthorizationFragment.class, null).commit();

        });

        EditText editNumber = view.findViewById(R.id.input_phone);
        EditText editPassword = view.findViewById(R.id.input_password);
        EditText editPasswordRepeat = view.findViewById(R.id.input_password_repeat);
        EditText editName = view.findViewById(R.id.input_name);
        EditText editSurname = view.findViewById(R.id.input_surname);





        view.findViewById(R.id.button_register).setOnClickListener(v -> {
            if(!editPassword.getText().toString().equals(editPasswordRepeat.getText().toString())) {
                Toast.makeText(getContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                return;
            }
            UserReg userReg = new UserReg(
                    editNumber.getText().toString(),
                    editPassword.getText().toString(),
                    editName.getText().toString(),
                    editSurname.getText().toString()
            );

            Log.d("TAG", "onViewCreated: " + userReg);


            KeyMechServiceGenerator.service.registration(userReg).enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    if(!response.isSuccessful()) {
                        Toast.makeText(getContext(), "Пользователь с данным номером уже существует", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Token token = response.body();
                    if(token != null) {
                        SharedPreferencesManager.getInstance(getContext()).setToken(token);
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    if(!response.isSuccessful()) {
                        Toast.makeText(getContext(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    Toast.makeText(getContext(), "Что-то пошло не так", Toast.LENGTH_SHORT).show();
                }
            });


        });

    }
}