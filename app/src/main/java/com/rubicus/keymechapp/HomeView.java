package com.rubicus.keymechapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeView extends Fragment {


    public HomeView() {
    }

    public static HomeView newInstance() {
        return new HomeView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        KeyMechService service = retrofit.create(KeyMechService.class);
        Call<Switch> callAsync = service.getSwitch(1);

        callAsync.enqueue(new Callback<Switch>() {
            @Override
            public void onResponse(Call<Switch> call, Response<Switch> response) {
                Switch switch_ = response.body();
                String image_name = switch_.image_name;

                Picasso.get()
                        .load("http://10.0.2.2:8080/image/" + image_name)
                        .placeholder(R.drawable.ic_placeholder)
                        .into((ImageView) view.findViewById(R.id.imageViewTest));
            }

            @Override
            public void onFailure(Call<Switch> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
