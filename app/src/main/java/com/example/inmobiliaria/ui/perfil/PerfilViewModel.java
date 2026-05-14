package com.example.inmobiliaria.ui.perfil;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria.modelo.Propietario;
import com.example.inmobiliaria.request.ApiClient;
import com.example.inmobiliaria.request.ApiService;
import com.example.inmobiliaria.request.Token;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> propietarioMutable;
    private MutableLiveData<String> errorNombreMutable, errorApellidoMutable,
                                    errorDNIMutable, errorTelefonoMutable,
                                    errorEmailMutable;
    private MutableLiveData<String> editarBotonMutable, guardarBotonMutable;



    public PerfilViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Propietario> getPropietarioMutable() {
        if (propietarioMutable == null) {
            propietarioMutable=new MutableLiveData<>();
        }
        return propietarioMutable;
    }

    public MutableLiveData<String> getEditarBotonMutable() {
        if (editarBotonMutable == null) {
            editarBotonMutable = new MutableLiveData<>();
        }
        return editarBotonMutable;
    }

    public MutableLiveData<String> getGuardarBotonMutable() {
        if (guardarBotonMutable == null) {
            guardarBotonMutable = new MutableLiveData<>();
        }
        return guardarBotonMutable;
    }

    public MutableLiveData<String> getErrorNombreMutable() {
        if (errorNombreMutable == null) {
            errorNombreMutable = new MutableLiveData<>();
        }
        return errorNombreMutable;
    }

    public MutableLiveData<String> getErrorApellidoMutable() {
        if (errorApellidoMutable == null) {
            errorApellidoMutable =new MutableLiveData<>();
        }
        return errorApellidoMutable;
    }

    public MutableLiveData<String> getErrorDNIMutable() {
        if (errorDNIMutable == null) {
            errorDNIMutable = new MutableLiveData<>();
        }
        return errorDNIMutable;
    }

    public MutableLiveData<String> getErrorTelefonoMutable() {
        if (errorTelefonoMutable == null) {
            errorTelefonoMutable = new MutableLiveData<>();
        }
        return errorTelefonoMutable;
    }

    public MutableLiveData<String> getErrorEmailMutable() {
        if (errorEmailMutable == null) {
            errorEmailMutable = new MutableLiveData<>();
        }
        return errorEmailMutable;
    }



    public void CargarPerfil(){
        // ----------- API --------
        String tokem= Token.ObtenerToken(getApplication());
        ApiService api = ApiClient.getApi().create(ApiService.class);
        Call<Propietario> llamada = api.getPropietario(tokem);
        llamada.enqueue(new Callback<Propietario>() {

            @Override
            public void onResponse(Call<Propietario> call,Response<Propietario> response) {

                if(response.isSuccessful()){
                    Propietario p = response.body();
                    propietarioMutable.postValue(p);

                }else{
                    Toast.makeText(getApplication(),
                            "Propietario no encontrado",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {

                Toast.makeText(getApplication(),
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void EditarGuardar(String boton, String IdPropietario,
                              String nombre,
                              String apellido,
                              String dni,
                              String telefono,
                              String email){
        if (boton.equals("Editar Perfil")) {
            editarBotonMutable.setValue("Guardar");
        }else {
            actualizarPerfil(IdPropietario,
                             nombre,
                             apellido,
                             dni,
                             telefono,
                             email);


        }
    }

    public void actualizarPerfil(String IdPropietario,
                                 String nombre,
                                 String apellido,
                                 String dni,
                                 String telefono,
                                 String email){
        //-----Limpiar--------
        errorNombreMutable.setValue(null);
        errorApellidoMutable.setValue(null);
        errorDNIMutable.setValue(null);
        errorTelefonoMutable.setValue(null);
        errorEmailMutable.setValue(null);

        boolean hayError = false;

        // ------- VALIDAR NOMBRE ---------
        if(nombre.isEmpty()){
            errorNombreMutable.setValue("El nombre es obligatorio");
            hayError = true;

        }else if(!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")){
            errorNombreMutable.setValue("El nombre solo puede contener letras");
            hayError = true;
        }

        // ------- VALIDAR APELLIDO ---------
        if(apellido.isEmpty()){
            errorApellidoMutable.setValue("El apellido es obligatorio");
            hayError = true;

        }else if(!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")){
            errorApellidoMutable.setValue("El apellido solo puede contener letras");
            hayError = true;
        }

        // ------- VALIDAR DNI ---------
        if(dni.isEmpty()){
            errorDNIMutable.setValue("El DNI es obligatorio");
            hayError = true;

        }else if(!dni.matches("\\d+")){
            errorDNIMutable.setValue("El DNI solo puede contener números");
            hayError = true;

        }else if(dni.length() != 8){
            errorDNIMutable.setValue("El DNI debe tener 8 dígitos");
            hayError = true;
        }

        // ------- VALIDAR TELEFONO ---------
        if(telefono.isEmpty()){
            errorTelefonoMutable.setValue("El teléfono es obligatorio");
            hayError = true;

        }else if(!telefono.matches("\\d+")){
            errorTelefonoMutable.setValue("El teléfono solo puede contener números");
            hayError = true;

        }else if(telefono.length() != 10){
            errorTelefonoMutable.setValue("El teléfono debe tener 10 dígitos");
            hayError = true;
        }

        // ------- VALIDAR EMAIL ---------
        if(email.isEmpty()){
            errorEmailMutable.setValue("El email es obligatorio");
            hayError = true;

        }else if(!email.matches("[A-Za-z0-9._%+-]+@gmail\\.com")){
            errorEmailMutable.setValue("Debe ingresar un Gmail válido");
            hayError = true;
        }

        // ----- SI HAY ERRORES NO CONTINUAR -----
        if(hayError){
            return;
        }

        // -------- CREAR PROPIETARIO -----------

        Propietario p = new Propietario();

        p.setIdPropietario(Integer.parseInt(IdPropietario));
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setDni(dni);
        p.setTelefono(telefono);
        p.setEmail(email);
        p.setClave(null);

        // ----------- API --------

        String token = Token.ObtenerToken(getApplication());

        ApiService api = ApiClient.getApi().create(ApiService.class);

        Call<Propietario> llamada = api.actualizarPropietario(token, p);

        llamada.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call,Response<Propietario> response) {

                if(response.isSuccessful()){
                    Propietario propietario = response.body();
                    Toast.makeText(getApplication(),
                            "Perfil actualizado",
                            Toast.LENGTH_SHORT).show();
                    propietarioMutable.postValue(propietario);
                    guardarBotonMutable.postValue("Editar Perfil");
                }else{
                    Toast.makeText(getApplication(),
                            "Error al actualizar",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call,Throwable t) {
                Toast.makeText(getApplication(),
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

}