package com.example.inmobiliaria.ui.inicio;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InicioViewModel extends AndroidViewModel {

    public class MapaActual implements OnMapReadyCallback{

        LatLng Inmobiliaria = new LatLng(-33.301988, -66.337693);
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            googleMap.addMarker(new MarkerOptions().position(Inmobiliaria).title("Inmobiliaria La Punta"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(Inmobiliaria));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.getUiSettings().setCompassEnabled(true);
            googleMap.getUiSettings().setTiltGesturesEnabled(true);
        }
    }

    private MutableLiveData<MapaActual> mapaActual = new MutableLiveData<>();
    public InicioViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<MapaActual> getMapaActual() {
        if (mapaActual == null) {
            mapaActual = new MutableLiveData<>();
        }
        return mapaActual;
    }

    public void cargarMapa(){
        MapaActual mapaActualNuevo = new MapaActual();
        mapaActual.setValue(mapaActualNuevo);
    }

}