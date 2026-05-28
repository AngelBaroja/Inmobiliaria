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

            if (inmueble.isDisponible()) {
                binding.tvDetalleEstado.setText("Disponible");
                binding.tvDetalleEstado.setBackgroundTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#4CAF50")));
                binding.tvDetalleContrato.setText("Este inmueble se encuentra listo para alquilar.");
            } else {
                binding.tvDetalleEstado.setText("No Disponible");
                binding.tvDetalleEstado.setBackgroundTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#F44336")));
                binding.tvDetalleContrato.setText("Este inmueble no está disponible por el momento.");
            }
            String rutaLimpia = inmueble.getImagen().replace("\\", "/");
            if(!rutaLimpia.startsWith("/")) rutaLimpia = "/" + rutaLimpia;

            Glide.with(requireContext())
                    .load(ApiClient.url + rutaLimpia)
                    .placeholder(R.drawable.ic_camera_black_24dp)
                    .error(R.drawable.avatar_1)
                    .into(binding.ivDetalleImagen);
        });

        Bundle bundle = getArguments();
        if(bundle != null){
            Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
            vm.cargarInmueble(inmueble);
        } else {
            Navigation.findNavController(root).navigateUp();
        }

        binding.btnEditarInmueble.setOnClickListener(v -> {
            Inmueble actual = vm.getInmuebleM().getValue();
            if(actual != null){
                vm.cambiarDisponibilidad(actual);
            }
        });

        return root;
    }


}