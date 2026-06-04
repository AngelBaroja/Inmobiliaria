package com.example.inmobiliaria.ui.inquilinos;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria.modelo.Contrato;
import com.example.inmobiliaria.request.ApiClient;
import com.example.inmobiliaria.request.ApiService;
import com.example.inmobiliaria.request.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInquilinoViewModel extends AndroidViewModel {

    private MutableLiveData<Contrato> inquilinoM;
    private MutableLiveData<Boolean> navegarM;

    public DetalleInquilinoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Contrato> getInquilinoM() {
        if (inquilinoM == null) {
            inquilinoM = new MutableLiveData<>();
        }
        return inquilinoM;
    }

    public LiveData<Boolean> getNavegarM() {
        if (navegarM == null) {
            navegarM = new MutableLiveData<>();
        }
        return navegarM;
    }

    public void cargarDatos(Bundle bundle) {
        if (bundle != null) {
            int id = bundle.getInt("idInmueble");
            if (id != -1) {
                obtenerInquilinoApi(id);
            } else {
                navegarM.setValue(true);
            }
        } else {
            navegarM.setValue(true);
        }
    }

    private void obtenerInquilinoApi(int idInmueble) {
        String token = Token.ObtenerToken(getApplication());
        ApiService api = ApiClient.getApi().create(ApiService.class);
        Call<Contrato> llamada = api.obtenerContratoDelInmueble(token, idInmueble);

        llamada.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if (response.isSuccessful() && response.body() != null) {
                    inquilinoM.setValue(response.body());
                } else {
                    Toast.makeText(getApplication(), "No se encontró contrato para este inmueble", Toast.LENGTH_SHORT).show();
                    navegarM.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Toast.makeText(getApplication(), "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                navegarM.setValue(true);
            }
        });
    }
}