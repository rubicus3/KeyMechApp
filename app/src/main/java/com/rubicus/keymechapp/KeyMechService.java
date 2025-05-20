package com.rubicus.keymechapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface KeyMechService {
    @GET("/get_keyboard")
    public Call<Keyboard> getKeyboard(
            @Query("id") int id
    );

    @GET("/get_switch")
    public Call<Switch> getSwitch(
            @Query("id") int id
    );

    @GET("/get_keycap")
    public Call<Keycap> getKeycap(
            @Query("id") int id
    );

    @GET("/get_keyboard_list")
    public Call<List<Keyboard>> getKeyboards(
        @Query("page") int page
    );

    @GET("/get_switch_list")
    public Call<List<Keyboard>> getSwitches(
        @Query("page") int page
    );

    @GET("/get_keycap_list")
    public Call<List<Keyboard>> getKeycaps(
        @Query("page") int page
    );


}
