package com.rubicus.keymechapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.rubicus.keymechapp.helper.KeyMechService;
import com.rubicus.keymechapp.helper.KeyMechServiceGenerator;
import com.rubicus.keymechapp.schemas.Token;
import com.rubicus.keymechapp.schemas.User;

import java.security.Key;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SharedPreferencesManager {
    private static final String APP_PREFS = "AppPrefsFile";
    private static final String TOKEN_KEY = "Token";
    private static final String USER_KEY = "User";

    private SharedPreferences sharedPrefs;
    private static SharedPreferencesManager instance;



    private SharedPreferencesManager(Context context) {
        sharedPrefs =
                context.getApplicationContext().getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
    }


    public static synchronized SharedPreferencesManager getInstance(Context context){
        if(instance == null)
            instance = new SharedPreferencesManager(context);

        return instance;
    }

    public void setToken(Token token) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(TOKEN_KEY, token.getFullToken());
        editor.apply();

        KeyMechServiceGenerator.service.getCurrentUser(token.getFullToken()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                setCurrentUser(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public String getToken() {
        return sharedPrefs.getString(TOKEN_KEY, null);
    }

    public void setCurrentUser(User user) {
        sharedPrefs.edit().putString(USER_KEY, new Gson().toJson(user)).apply();
    }
    public User getCurrentUser() {
        String userPlain = sharedPrefs.getString(USER_KEY, null);
        return new Gson().fromJson(userPlain, User.class);

    }

    public void clearToken() {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.remove(USER_KEY).remove(TOKEN_KEY);
        editor.apply();
    }
}
