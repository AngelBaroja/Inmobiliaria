package com.example.inmobiliaria.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.inmobiliaria.modelo.Propietario;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    //Obtener Token en el login
    @FormUrlEncoded
    @POST("api/Propietarios/login")
    Call<String> login(
            @Field("Usuario") String usuario,
            @Field("Clave") String clave
    );

    //Obtener Propietario
    @GET("api/Propietarios")
    Call<Propietario> getPropietario(
            @Header("Authorization") String token
    );


}

