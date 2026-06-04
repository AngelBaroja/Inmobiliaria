package com.example.inmobiliaria.ui.inquilinos;

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
import com.example.inmobiliaria.databinding.FragmentDetalleInquilinoBinding;

public class DetalleInquilinoFragment extends Fragment {

    private DetalleInquilinoViewModel vm;
    private FragmentDetalleInquilinoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleInquilinoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(this).get(DetalleInquilinoViewModel.class);

        vm.getInquilinoM().observe(getViewLifecycleOwner(), contrato -> {
            if (contrato.getInquilino() != null) {
                binding.tvInquilinoNombre.setText(contrato.getInquilino().getNombre() + " " + contrato.getInquilino().getApellido());
                binding.tvInquilinoDni.setText(String.valueOf(contrato.getInquilino().getDni()));
                binding.tvInquilinoEmail.setText(contrato.getInquilino().getEmail());
                binding.tvInquilinoTelefono.setText(contrato.getInquilino().getTelefono());
            }
        });

        vm.getNavegarM().observe(getViewLifecycleOwner(), debeVolver -> {
            if (debeVolver) {
                Navigation.findNavController(requireView()).navigateUp();
            }
        });

        vm.cargarDatos(getArguments());

        binding.btnVolver.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}