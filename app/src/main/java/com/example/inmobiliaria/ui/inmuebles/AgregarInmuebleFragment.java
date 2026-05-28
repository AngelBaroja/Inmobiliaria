package com.example.inmobiliaria.ui.inmuebles;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.databinding.FragmentAgregarInmuebleBinding;

public class AgregarInmuebleFragment extends Fragment {

    private AgregarInmuebleViewModel vm;
    private FragmentAgregarInmuebleBinding binding;
    private ActivityResultLauncher<Intent> selector;
    private Intent intent;

    public static AgregarInmuebleFragment newInstance() {
        return new AgregarInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAgregarInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(AgregarInmuebleViewModel.class);

        vm.getUri().observe(getViewLifecycleOwner(), uri -> {
            binding.ivInmuebleNuevo.setImageTintList(null);
            binding.ivInmuebleNuevo.setImageURI(uri);


        });
        binding.btnCargarImagen.setOnClickListener(v -> {
            selector.launch(intent);
        });
        binding.btnGuardarInmueble.setOnClickListener(v -> {
            vm.cargarInmueble(
                    binding.etDireccion.getText().toString(),
                    binding.etUso.getText().toString(),
                    binding.etTipo.getText().toString(),
                    binding.etAmbientes.getText().toString(),
                    binding.etSuperficie.getText().toString(),
                    binding.etPrecio.getText().toString());
        });

        abrirGaleria();
        return root;
    }

    private void abrirGaleria() {
        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        selector = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult resultado) {
                vm.recibirFoto(resultado);
            }

        });

    }

}