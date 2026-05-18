package com.example.inmobiliaria.ui.inmuebles;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.databinding.FragmentDetalleInmuebleBinding;
import com.example.inmobiliaria.modelo.Inmueble;

public class DetalleInmuebleFragment extends Fragment {

    private DetalleInmuebleViewModel vm;
    private FragmentDetalleInmuebleBinding binding;

    public static DetalleInmuebleFragment newInstance() {
        return new DetalleInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(DetalleInmuebleViewModel.class);


        vm.getInmuebleM().observe(getViewLifecycleOwner(), inmueble -> {
            binding.tvDetallePrecio.setText("$ " + inmueble.getValor());
            binding.tvDetalleDireccion.setText(inmueble.getDireccion());
            binding.tvDetalleAmbientes.setText(inmueble.getAmbientes() + "");
            binding.tvDetalleSuperficie.setText(inmueble.getSuperficie() + "");
            binding.tvDetalleTipo.setText(inmueble.getTipo());
            binding.tvDetalleUso.setText(inmueble.getUso());
            binding.tvDetalleContrato.setText(inmueble.isDisponible() ? "Disponible" : "No Disponible");
        });

        Bundle bundle = getArguments();
        if(bundle != null){
            Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
            vm.cargarInmueble(inmueble);
        } else {
            Navigation.findNavController(root).navigateUp();
        }


        return root;
    }


}