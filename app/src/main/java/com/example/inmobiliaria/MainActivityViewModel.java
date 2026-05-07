package com.example.inmobiliaria;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmobiliaria.InicioActivity;
import com.example.inmobiliaria.request.ApiClient;
import com.example.inmobiliaria.request.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(String usuario, String clave){

        ApiService api = ApiClient.getApi().create(ApiService.class);

        Call<String> llamada = api.login(usuario, clave);

        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call,
                                   Response<String> response) {

                if(response.isSuccessful()){

                    String token = response.body();
                    Toast.makeText(getApplication(),
                            "Login Correcto",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplication(), InicioActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(intent);

                }else{

                    Toast.makeText(getApplication(),
                            "Usuario o Clave incorrectos",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(getApplication(),
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}