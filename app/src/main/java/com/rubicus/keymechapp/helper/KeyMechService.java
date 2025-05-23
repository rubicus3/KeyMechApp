package com.rubicus.keymechapp.helper;

import com.rubicus.keymechapp.schemas.Keyboard;
import com.rubicus.keymechapp.schemas.Keycap;
import com.rubicus.keymechapp.schemas.Switch;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
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
    public Call<ArrayList<Keyboard>> getKeyboards(
        @Query("page") int page
    );

    @GET("/get_switch_list")
    public Call<ArrayList<Switch>> getSwitches(
        @Query("page") int page
    );

    @GET("/get_keycap_list")
    public Call<ArrayList<Keycap>> getKeycaps(
        @Query("page") int page
    );

    @GET("/get_popular_switches")
    public Call<ArrayList<Switch>> getPopularSwitches(
    );


}
