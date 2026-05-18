package com.example.inmobiliaria.ui.inmuebles;

import android.app.Application;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> listaInmuebleM;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Inmueble>> getListaInmuebleM() {
        if (listaInmuebleM == null) {
            listaInmuebleM = new MutableLiveData<>();
        }
        return listaInmuebleM;
    }


    public void listarInmuebles(){
        String token = Token.ObtenerToken(getApplication());
        ApiService api = ApiClient.getApi().create(ApiService.class);
        Call<List<Inmueble>> llamada = api.obtenerInmuebles(token);

        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful() && response.body() != null){
                    listaInmuebleM.setValue(response.body());
                } else {
                    Toast.makeText(getApplication(),
                            "No se encontraron inmuebles",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(getApplication(),
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}