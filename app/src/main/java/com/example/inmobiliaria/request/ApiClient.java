package com.example.inmobiliaria.request;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    private static final String URL_BASE =
            "https://capacitacion.alwaysdata.net/";

    private static Retrofit retrofit;

    public static Retrofit getApi(){

        if(retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}