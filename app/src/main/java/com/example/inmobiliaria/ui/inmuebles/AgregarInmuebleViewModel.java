package com.example.inmobiliaria.ui.inmuebles;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.request.ApiClient;
import com.example.inmobiliaria.request.ApiService;
import com.example.inmobiliaria.request.Token;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarInmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<Uri> mUri;
    public AgregarInmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Uri> getUri() {
        if(mUri == null){
            mUri = new MutableLiveData<>();
        }
        return mUri;
    }

    public void recibirFoto(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            Uri uri = data.getData();
            Log.d("salada", uri.toString());
            mUri.setValue(uri);
        }
    }

    public void cargarInmueble(String direccion, String uso, String tipo, String ambiente,
                               String superficie, String precio){
        try{
            if(direccion.isEmpty() || uso.isEmpty() || tipo.isEmpty() ||
                    ambiente.isEmpty() || superficie.isEmpty() || precio.isEmpty()){
                Toast.makeText(getApplication(), "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            if(mUri.getValue() == null) {
                Toast.makeText(getApplication(), "Debe seleccionar una foto", Toast.LENGTH_SHORT).show();
                return;
            }
            Inmueble inmueble = new Inmueble();
            inmueble.setDireccion(direccion);
            inmueble.setUso(uso);
            inmueble.setTipo(tipo);
            inmueble.setAmbientes(Integer.parseInt(ambiente));
            inmueble.setSuperficie(Integer.parseInt(superficie));
            inmueble.setValor(Double.parseDouble(precio));
            inmueble.setDisponible(false);
            byte[] imagen = transformarImagen();
            if (imagen.length == 0) {
                Toast.makeText(getApplication(), "Debe ingresar imagen", Toast.LENGTH_LONG).show();
                return;
            }
            String inmuebleJson = new Gson().toJson(inmueble);
            RequestBody inmuebleBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), inmuebleJson);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imagen);
            MultipartBody.Part imagenPart = MultipartBody.Part.createFormData("imagen", "imagen.jpg", requestFile);

            String token = Token.ObtenerToken(getApplication());
            ApiService api = ApiClient.getApi().create(ApiService.class);
            Call<Inmueble> llamada = api.agregarInmueble(token, imagenPart, inmuebleBody);

            llamada.enqueue(new Callback<Inmueble>() {
                @Override
                public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                    if(response.isSuccessful() && response.body() != null){
                        Toast.makeText(getApplication(),
                                "Inmueble agregado correctamente",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplication(),
                                "No se pudo agregar el inmueble",
                                Toast.LENGTH_SHORT).show();
                        Log.d("ERROR:" , "codigo" + response.code());
                        Log.d("ERROR:" , "mensaje" + response.message());
                        Log.d("ERROR:" , "body" + response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<Inmueble> call, Throwable t) {

                }
            });


        } catch (NumberFormatException e){
            Toast.makeText(getApplication(), "Debe ingresar un número en los campos numéricos, superficie y ambientes", Toast.LENGTH_LONG).show();
            return;
        }

    }

    private byte[] transformarImagen() {
        try {
            Uri uri = mUri.getValue();
            InputStream inputStream = getApplication().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException ex) {
            Toast.makeText(getApplication(), "Debe ingresar una foto", Toast.LENGTH_LONG).show();
            return new byte[]{};
        }
    }

}