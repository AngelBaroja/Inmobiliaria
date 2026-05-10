package com.example.inmobiliaria.ui.perfil;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria.modelo.Propietario;
import com.example.inmobiliaria.request.ApiClient;
import com.example.inmobiliaria.request.ApiService;
import com.example.inmobiliaria.request.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContraseniaFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<String> actualContraseñaMutable;
    private MutableLiveData<String> nuevaContraseñaMutable;
    private MutableLiveData<String> repetirContraseñaMutable;
    private MutableLiveData<String> limbiarMutable;

    public ContraseniaFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getLimbiarMutable() {
        if (limbiarMutable == null) {
            limbiarMutable = new MutableLiveData<>();
        }
        return limbiarMutable;
    }

    public MutableLiveData<String> getActualContraseñaMutable() {
        if (actualContraseñaMutable == null) {
            actualContraseñaMutable = new MutableLiveData<>();
        }
        return actualContraseñaMutable;
    }

    public MutableLiveData<String> getNuevaContraseñaMutable() {
        if (nuevaContraseñaMutable == null) {
            nuevaContraseñaMutable = new MutableLiveData<>();
        }
        return nuevaContraseñaMutable;
    }

    public MutableLiveData<String> getRepetirContraseñaMutable() {
        if (repetirContraseñaMutable == null) {
            repetirContraseñaMutable = new MutableLiveData<>();
        }
        return repetirContraseñaMutable;
    }

    public void CambiarContraseña(String actualContraseña, String nuevaContraseña, String repetirContraseña) {

        //-----Limpiar--------
        actualContraseñaMutable.setValue(null);
        nuevaContraseñaMutable.setValue(null);
        repetirContraseñaMutable.setValue(null);


        //------VALIDACION--------
        boolean hayError = false;
        if (!actualContraseña.equals("DEEKQW")) {
            actualContraseñaMutable.setValue("Clave Incorrecta");
            hayError = true;
        }
        if (nuevaContraseña.isEmpty() ) {
            nuevaContraseñaMutable.setValue("Ingrese una nueva clave");
            hayError=true;
        }
        if (!nuevaContraseña.equals(repetirContraseña) ) {
            repetirContraseñaMutable.setValue("Las claves no coinciden");
            hayError = true;
        }
        if (hayError) {
            return;
        }

        //-----API----------
        String tokem = Token.ObtenerToken(getApplication());
        ApiService api = ApiClient.getApi().create(ApiService.class);
        Call<Void> llamada = api.actualizarClave(tokem, actualContraseña, nuevaContraseña);
        llamada.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {

                    Toast.makeText(getApplication(),
                            "Clave Actualizada",
                            Toast.LENGTH_SHORT).show();

                    //.------LIMPIO ERROR-----------
                    actualContraseñaMutable.setValue(null);
                    nuevaContraseñaMutable.setValue(null);
                    repetirContraseñaMutable.setValue(null);
                    //.------LIMPIO EDITTEXT-----------
                    limbiarMutable.setValue(null);

                } else {
                    Toast.makeText(getApplication(),
                            "Error al Actualizar Clave",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(getApplication(),
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }

        });

    }
}
