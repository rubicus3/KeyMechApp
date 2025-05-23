package com.rubicus.keymechapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.rubicus.keymechapp.schemas.Token;

public class SharedPreferencesManager {
    private static final String APP_PREFS = "AppPrefsFile";
    private static final String TOKEN_KEY = "Token";

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
    }

    public String getToken() {
        return sharedPrefs.getString(TOKEN_KEY, null);
    }
}
