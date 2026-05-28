package com.example.inmobiliaria.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.inmobiliaria.modelo.Contrato;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.modelo.Pago;
import com.example.inmobiliaria.modelo.Propietario;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    //Listar todos los inmuebles
    @GET("Inmuebles")
    Call<List<Inmueble>> obtenerInmuebles(
            @Header("Authorization") String token
    );


    //Listar Inmuebles con Contaro
    @GET("Inmuebles/GetContratoVigente")
    Call<List<Inmueble>> obtenerInmueblesConContratoVigente(
            @Header("Authorization") String token
    );
    //Cambiar disponibilidad Inmueble
    @PUT("Inmuebles/actualizar")
    Call<Inmueble> cambiarDisponibilidad(
            @Header("Authorization") String token,
            @Body Inmueble inmueble
    );
    //Agregar Inmueble
    @Multipart
    @POST("Inmuebles/cargar")
    Call<Inmueble> agregarInmueble(@Header("Authorization") String token,
                                   @Part MultipartBody.Part imagen,
                                   @Part("inmueble")RequestBody inmuebleBody
                                   );

    // Contrato del Inmueble
    @GET("contratos/inmueble/{id}")
    Call<Contrato> obtenerContratoDelInmueble(
            @Header("Authorization") String token,
                                   @Path("id") int idInmueble
    );
    // Lista de los Pagos de un contrato
    @GET("pagos/contrato/{id}")
    Call<List<Pago>> obtenerPagosDelContrato(
            @Header("Authorization") String token,
            @Path("id") int idContrato
    );

}

