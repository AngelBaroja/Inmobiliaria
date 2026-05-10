package com.example.inmobiliaria.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliaria.databinding.FragmentContraseniaBinding;
import com.example.inmobiliaria.databinding.FragmentNavContraseniaBinding;

public class ContraseniaFragment extends Fragment {

    private FragmentContraseniaBinding binding;
    private ContraseniaFragmentViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentContraseniaBinding.inflate(inflater, container, false);

        vm = new ViewModelProvider(this).get(ContraseniaFragmentViewModel.class);

        vm.getActualContraseñaMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tilClaveActual.setError(s);
            }
        });
        vm.getNuevaContraseñaMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tilClaveNueva.setError(s);
            }
        });
        vm.getRepetirContraseñaMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tilRepetirClave.setError(s);
            }
        });
        vm.getLimbiarMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.etRepetirClave.setText(s);
                binding.etClaveActual.setText(s);
                binding.etClaveNueva.setText(s);
            }
        });

        binding.btnActualizarClave.setOnClickListener(v -> {
            /*Bundle bundle = getArguments();

                if(bundle != null){

                    String clave = bundle.getString("clave");

                }*/
            String actual = binding.etClaveActual.getText().toString();
            String nueva = binding.etClaveNueva.getText().toString();
            String repetir = binding.etRepetirClave.getText().toString();
            vm.CambiarContraseña(actual,nueva,repetir);
        });


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}