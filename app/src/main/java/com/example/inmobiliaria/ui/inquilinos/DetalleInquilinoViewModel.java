package com.example.inmobiliaria.ui.inquilinos;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria.modelo.Contrato;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.modelo.Inquilino;
import com.example.inmobiliaria.request.ApiClient;
import com.example.inmobiliaria.request.ApiService;
import com.example.inmobiliaria.request.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInquilinoViewModel extends AndroidViewModel {

    private MutableLiveData<Contrato> inquilinoM;

    public DetalleInquilinoViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Contrato> getInquilinoM(){
        if(inquilinoM == null){
            inquilinoM = new MutableLiveData<>();
        }
        return inquilinoM;
    }

    public void detalleInquilino(int idInmueble){
        int id = idInmueble;

        String token = Token.ObtenerToken(getApplication());
        ApiService api = ApiClient.getApi().create(ApiService.class);
        Call<Contrato> llamada = api.obtenerContratoDelInmueble(token, id);

        llamada.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if(response.isSuccessful()){
                    Contrato inquilino = response.body();
                    inquilinoM.setValue(inquilino);
                }else{
                    Toast.makeText(getApplication(),
                            "Error al buscar el contrato del inmueble",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Toast.makeText(getApplication(),
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

}