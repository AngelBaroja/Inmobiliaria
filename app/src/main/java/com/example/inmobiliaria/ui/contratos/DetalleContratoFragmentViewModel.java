package com.example.inmobiliaria.ui.contratos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria.modelo.Contrato;

import java.util.List;

public class DetalleContratoFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<List<Contrato>> listaContratoMutable;
    public DetalleContratoFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Contrato>> getListaContratoMutable() {
        if (listaContratoMutable == null) {
            listaContratoMutable =new MutableLiveData<>();
        }
        return listaContratoMutable;
    }

    public void listarContratosDeInmueble(int idInmueble){
    int id =idInmueble;
   }
}
