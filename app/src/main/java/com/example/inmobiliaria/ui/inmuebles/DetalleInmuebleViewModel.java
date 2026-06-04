package com.example.inmobiliaria.ui.inmuebles;

import android.app.Application;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.request.ApiClient;
import com.example.inmobiliaria.request.ApiService;
import com.example.inmobiliaria.request.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<Inmueble> inmuebleM;
    private MutableLiveData<Boolean> navegarM;
    private MutableLiveData<String> estadoM;
    private MutableLiveData<ColorStateList> colorM;
    private MutableLiveData<String> mensajeContratoM;

    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inmueble> getInmuebleM() {
        if (inmuebleM == null) {
            inmuebleM = new MutableLiveData<>();
        }
        return inmuebleM;
    }

    public LiveData<Boolean> getNavegarM() {
        if (navegarM == null) {
            navegarM = new MutableLiveData<>();
        }
        return navegarM;
    }

    public LiveData<String> getEstadoM() {
        if (estadoM == null) {
            estadoM = new MutableLiveData<>();
        }
        return estadoM;
    }

    public LiveData<ColorStateList> getColorM() {
        if (colorM == null) {
            colorM = new MutableLiveData<>();
        }
        return colorM;
    }

    public LiveData<String> getMensajeContratoM() {
        if (mensajeContratoM == null) {
            mensajeContratoM = new MutableLiveData<>();
        }
        return mensajeContratoM;
    }

    public void cargarInmueble(Bundle bundle) {
        if (bundle != null) {
            Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
            if (inmueble != null) {
                actualizarDatos(inmueble);
            } else {
                navegarM.setValue(true);
            }
        } else {
            navegarM.setValue(true);
        }
    }

    private void actualizarDatos(Inmueble inmueble) {
        inmuebleM.setValue(inmueble);
        if (inmueble.isDisponible()) {
            estadoM.setValue("Disponible");
            colorM.setValue(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
            mensajeContratoM.setValue("Este inmueble se encuentra listo para alquilar.");
        } else {
            estadoM.setValue("No Disponible");
            colorM.setValue(ColorStateList.valueOf(Color.parseColor("#F44336")));
            mensajeContratoM.setValue("Este inmueble no está disponible por el momento.");
        }
    }

    public void cambiarDisponibilidad() {
        Inmueble inmueble = inmuebleM.getValue();
        if (inmueble != null) {
            inmueble.setDisponible(!inmueble.isDisponible());
            String token = Token.ObtenerToken(getApplication());
            ApiService api = ApiClient.getApi().create(ApiService.class);
            Call<Inmueble> llamada = api.cambiarDisponibilidad(token, inmueble);

            llamada.enqueue(new Callback<Inmueble>() {
                @Override
                public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        actualizarDatos(response.body());
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
