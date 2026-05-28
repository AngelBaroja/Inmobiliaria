package com.example.inmobiliaria.ui.inmuebles;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.request.ApiClient;
import com.example.inmobiliaria.request.ApiService;
import com.example.inmobiliaria.request.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<Inmueble> inmuebleM;
    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inmueble> getInmuebleM() {
        if (inmuebleM == null) {
            inmuebleM = new MutableLiveData<>();
        }
        return inmuebleM;
    }

    public void cargarInmueble(Inmueble inmueble) {
        if(inmueble!=null)
            inmuebleM.setValue(inmueble);
        else{
            Toast.makeText(getApplication(),"No se encontro el inmueble",Toast.LENGTH_SHORT).show();
        }
    }

    public void cambiarDisponibilidad(Inmueble inmueble){
        if(inmueble != null){
            inmueble.setDisponible(!inmueble.isDisponible());
            String token = Token.ObtenerToken(getApplication());
            ApiService api = ApiClient.getApi().create(ApiService.class);
            Call<Inmueble> llamada = api.cambiarDisponibilidad(token, inmueble);

            llamada.enqueue(new Callback<Inmueble>() {
                @Override
                public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                    if(response.isSuccessful() && response.body() != null){
                        inmuebleM.setValue(response.body());
                    } else {
                        Toast.makeText(getApplication(),
                                "No se pudo cambiar la disponibilidad",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Inmueble> call, Throwable t) {
                    Toast.makeText(getApplication(),
                            t.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        }


    }

}