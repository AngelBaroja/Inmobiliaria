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

import com.bumptech.glide.Glide;
import com.example.inmobiliaria.R;
import com.example.inmobiliaria.databinding.FragmentDetalleInmuebleBinding;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.request.ApiClient;

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
            binding.tvDetalleAmbientes.setText(inmueble.getAmbientes() + " Ambientes");
            binding.tvDetalleSuperficie.setText(inmueble.getSuperficie() + " M2");
            binding.tvDetalleTipo.setText(inmueble.getTipo());
            binding.tvDetalleUso.setText(inmueble.getUso());

            String rutaLimpia = inmueble.getImagen().replace("\\", "/");
            if(!rutaLimpia.startsWith("/")) rutaLimpia = "/" + rutaLimpia;

            Glide.with(requireContext())
                    .load(ApiClient.url + rutaLimpia)
                    .placeholder(R.drawable.ic_camera_black_24dp)
                    .error(R.drawable.avatar_1)
                    .into(binding.ivDetalleImagen);
        });

        vm.getEstadoM().observe(getViewLifecycleOwner(), estado -> binding.tvDetalleEstado.setText(estado));

        vm.getColorM().observe(getViewLifecycleOwner(), color -> binding.tvDetalleEstado.setBackgroundTintList(color));

        vm.getMensajeContratoM().observe(getViewLifecycleOwner(), mensaje -> binding.tvDetalleContrato.setText(mensaje));

        vm.getNavegarM().observe(getViewLifecycleOwner(), debeNavegar -> {
            if(debeNavegar) Navigation.findNavController(requireView()).navigateUp();
        });

        vm.cargarInmueble(getArguments());

        binding.btnEditarInmueble.setOnClickListener(v -> {
            vm.cambiarDisponibilidad();
        });

        return root;
    }


}