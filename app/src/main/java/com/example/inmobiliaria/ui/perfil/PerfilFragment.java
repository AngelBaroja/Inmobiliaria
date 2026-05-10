package com.example.inmobiliaria.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.databinding.FragmentPerfilBinding;
import com.example.inmobiliaria.modelo.Propietario;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        binding = FragmentPerfilBinding.inflate(inflater, container, false);

        vm = new ViewModelProvider(this).get(PerfilViewModel.class);

        vm.getPropietarioMutable().observe(getViewLifecycleOwner(), propietario -> {
            binding.tvId.setText(propietario.getIdPropietario()+"");
            binding.etNombre.setText(propietario.getNombre());
            binding.etApellido.setText(propietario.getApellido());
            binding.etDni.setText(propietario.getDni());
            binding.etTelefono.setText(propietario.getTelefono());
            binding.etEmail.setText(propietario.getEmail());
        });
        vm.CargarPerfil();
        binding.btnGuardar.setOnClickListener( v -> {
            vm.actualizarPerfil(binding.tvId.getText().toString(),
                                binding.etNombre.getText().toString(),
                                binding.etApellido.getText().toString(),
                                binding.etDni.getText().toString(),
                                binding.etTelefono.getText().toString(),
                                binding.etEmail.getText().toString()
            );
        });
        vm.getErrorNombreMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tilNombre.setError(s);
                binding.etNombre.findFocus();
            }
        });
        vm.getErrorApellidoMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tilApellido.setError(s);
                binding.etApellido.findFocus();
            }
        });
        vm.getErrorDNIMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tilDNI.setError(s);
                binding.etApellido.findFocus();
            }
        });
        vm.getErrorTelefonoMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tilTelefono.setError(s);
            }
        });
        vm.getErrorEmailMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tilEmail.setError(s);
            }
        });
        binding.btnContrasena.setOnClickListener(v -> {
            Bundle bundle = new Bundle();

            //bundle.putString("clave", binding.tvContraseña.getText());

            Navigation.findNavController(v).navigate(R.id.nav_contrasenia, bundle);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}