package com.example.inmobiliaria.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    private static final String URL_BASE =
            "https://capacitacion.alwaysdata.net/api/";

    private static Retrofit retrofit;

    public static Retrofit getApi(){
        Gson gson = new GsonBuilder().setLenient().create();
        if(retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    //.addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }
}