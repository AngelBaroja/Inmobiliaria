package com.example.inmobiliaria;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria.InicioActivity;
import com.example.inmobiliaria.request.ApiClient;
import com.example.inmobiliaria.request.ApiService;
import com.example.inmobiliaria.request.Token;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<String> errorLogin;
    private Context context;
    private MutableLiveData<String> mLlamada;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ShakeDetector shakeDetector;
    private boolean sensorR = false;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
        configurarShakeDetector();
    }

    public MutableLiveData<String> getErrorLogin() {
        if (errorLogin == null) {
            errorLogin = new MutableLiveData<>();
        }
        return errorLogin;
    }

    public MutableLiveData<String> getmLlamada() {

        if (mLlamada == null) {
            mLlamada = new MutableLiveData<>();
        }
        return mLlamada;
    }

    public void login(String usuario, String clave){

        ApiService api = ApiClient.getApi().create(ApiService.class);

        Call<String> llamada = api.login(usuario, clave);

        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.isSuccessful()){

                    String token = response.body();

                    Token.GuardarToken(getApplication(), token); //Guardar el TOKEN

                    Toast.makeText(getApplication(),
                            "Login Correcto",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplication(), InicioActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(intent);

                }else{

                    errorLogin.setValue("Usuario o Clave Incorrectos");
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

    private void configurarShakeDetector() {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        shakeDetector = new ShakeDetector();
        shakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                mLlamada.postValue("LLAMAR");
            }
        });
    }
    public void abrirAppLlamadas() {
        String numeroInmobiliaria = "2664256789";
        Intent intentLlamada = new Intent(Intent.ACTION_DIAL);
        intentLlamada.setData(Uri.parse("tel:" + numeroInmobiliaria));
        intentLlamada.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentLlamada);

        //mLlamada.postValue(false);
    }

    public void iniciarDeteccionShake() {
        configurarShakeDetector();
        sensorManager.registerListener(shakeDetector, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorR = true;
    }

    public void detenerDeteccionShake() {
        sensorManager.unregisterListener(shakeDetector);
        sensorR = false;
    }
}