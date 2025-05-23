package com.rubicus.keymechapp.schemas;

public class Token{
    public String access_token;
    public String token_type;
    public String get_full_token() {
        return token_type + " " + access_token;
    }
}
