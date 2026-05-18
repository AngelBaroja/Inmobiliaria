package com.example.inmobiliaria.ui.inmuebles;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria.modelo.Inmueble;

public class AgregarInmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmuebleM;
    public AgregarInmuebleViewModel(@NonNull Application application) {
        super(application);
    }



}