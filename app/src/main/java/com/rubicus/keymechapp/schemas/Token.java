package com.rubicus.keymechapp.schemas;

import android.os.Parcelable;

import java.io.Serializable;

public class Token implements Serializable {
    public String access_token;
    public String token_type;
    public String getFullToken() {
        return token_type + " " + access_token;
    }

}
