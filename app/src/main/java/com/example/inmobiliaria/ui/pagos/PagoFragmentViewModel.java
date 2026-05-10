package com.example.inmobiliaria.ui.pagos;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria.modelo.Contrato;
import com.example.inmobiliaria.modelo.Pago;
import com.example.inmobiliaria.request.ApiClient;
import com.example.inmobiliaria.request.ApiService;
import com.example.inmobiliaria.request.Token;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<List<Pago>>  listaPagosMuteable;

    public PagoFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Pago>> getListaPagosMuteable() {
        if (listaPagosMuteable == null) {
            listaPagosMuteable = new MutableLiveData<>();
        }
        return listaPagosMuteable;
    }

    public void MostrarPagos(int idContrato){
        int idcontrato = idContrato;

        String token = Token.ObtenerToken(getApplication());

        ApiService api = ApiClient.getApi().create(ApiService.class);

        Call<List<Pago>> llamada = api.obtenerPagosDelContrato(token, idcontrato);

        llamada.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {

                if(response.isSuccessful()){
                    listaPagosMuteable.setValue(response.body());
                }else{
                    Toast.makeText(getApplication(),
                            "Error al buscar los Pagos del Contrato",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call,Throwable t) {
                Toast.makeText(getApplication(),
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
