package com.example.inmobiliaria.ui.inicio;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.databinding.FragmentInicioBinding;
import com.google.android.gms.maps.SupportMapFragment;

public class InicioFragment extends Fragment{

    private InicioViewModel vm;
    private FragmentInicioBinding binding;

    public static InicioFragment newInstance() {
        return new InicioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentInicioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(InicioViewModel.class);

        vm.getMapaActual().observe(getViewLifecycleOwner(), mapaActual -> {
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);

            if (mapFragment != null) {
                mapFragment.getMapAsync(mapaActual);
            }
        });
        vm.cargarMapa();


        return root;
    }


}