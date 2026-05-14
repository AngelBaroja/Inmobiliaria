package com.example.inmobiliaria.ui.contratos;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria.modelo.Contrato;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.request.ApiClient;
import com.example.inmobiliaria.request.ApiService;
import com.example.inmobiliaria.request.Token;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosViewModel extends AndroidViewModel {

private MutableLiveData<List<Inmueble>> listaInmuebleMutable;
private ArrayList<Inmueble> listaInmueble;
    public ContratosViewModel(@NonNull Application application) {
        super(application);
        listaInmueble=new ArrayList<>();
    }

    public MutableLiveData<List<Inmueble>> getListaContratoMutable() {
        if (listaInmuebleMutable == null) {
            listaInmuebleMutable = new MutableLiveData<>();
        }
        return listaInmuebleMutable;
    }

    public void ListaInmuebleConContrato(){

        String token = Token.ObtenerToken(getApplication());
        ApiService api = ApiClient.getApi().create(ApiService.class);
        Call<List<Inmueble>> llamada = api.obtenerInmueblesConContratoVigente(token);

        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {

                if(response.isSuccessful() && response.body() != null){
                    listaInmuebleMutable.postValue(response.body());
                }else{
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