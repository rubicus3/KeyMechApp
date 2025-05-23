package com.rubicus.keymechapp.helper;

import com.rubicus.keymechapp.schemas.Keyboard;
import com.rubicus.keymechapp.schemas.Keycap;
import com.rubicus.keymechapp.schemas.Order;
import com.rubicus.keymechapp.schemas.Switch;
import com.rubicus.keymechapp.schemas.Token;
import com.rubicus.keymechapp.schemas.User;
import com.rubicus.keymechapp.schemas.UserReg;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface KeyMechService {

    @FormUrlEncoded
    @POST("token")
    Call<String> getToken(
        @Field("grant_type") String grantType,
        @Field("username") String username,
        @Field("password") String password
    );

    @GET("users/me/")
    Call<User> getCurrentUser(
        @Header("Authorization") String authToken
    );
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

    @POST("/create_order")
    Call<Order> createOrder(
        @Header("Authorization") String authToken,
        @Body Order order
    );

    @GET("/get_orders")
    Call<ArrayList<Order>> getOrderList(
        @Header("Authorization") String authToken
    );

    @POST("/register")
    Call<Token> registration(
        @Body UserReg userReg
    );


}
