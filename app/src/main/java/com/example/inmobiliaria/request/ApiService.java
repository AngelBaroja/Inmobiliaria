package com.example.inmobiliaria.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.inmobiliaria.modelo.Contrato;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.modelo.Propietario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    //Obtener Token en el login
    @FormUrlEncoded
    @POST("Propietarios/login")
    Call<String> login(
            @Field("Usuario") String usuario,
            @Field("Clave") String clave
    );

    //Obtener Propietario
    @GET("Propietarios")
    Call<Propietario> getPropietario(
            @Header("Authorization") String token
    );
   //Actualizar Propietario
    @PUT("Propietarios/actualizar")
    Call<Propietario> actualizarPropietario(
            @Header("Authorization") String token,
            @Body Propietario propietario
    );
    //Cambiar Clave
    @FormUrlEncoded
    @PUT("Propietarios/changePassword")
    Call<Void> actualizarClave(@Header("Authorization") String token,
                    @Field("currentPassword") String actualClave,
                    @Field("newPassword") String nuevaClave

    );


    //Listar Inmuebles con Contaro
    @GET("Inmuebles/GetContratoVigente")
    Call<List<Inmueble>> obtenerInmueblesConContratoVigente(
            @Header("Authorization") String token
    );

    //Listar Contratos por Inmueble
    @GET("contratos/inmueble/{id}")
    Call<Contrato> obtenerContratoDelInmueble(
            @Header("Authorization") String token,
            @Path("id") int idInmueble
    );

}

