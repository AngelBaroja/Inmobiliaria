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
}