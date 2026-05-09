package com.example.inmobiliaria.request;

import android.content.Context;
import android.content.SharedPreferences;

public class Token {
    public static void GuardarToken(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", "Bearer "+ token);
        editor.apply();
    }

    public static String ObtenerToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        return sp.getString("token", null);
    }
}
