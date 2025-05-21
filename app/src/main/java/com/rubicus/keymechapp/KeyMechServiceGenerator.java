package com.rubicus.keymechapp;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KeyMechServiceGenerator {

    private static final String BASE_URL = "http://10.0.2.2:8000/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    public static final KeyMechService service
            = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(KeyMechService.class);

}
