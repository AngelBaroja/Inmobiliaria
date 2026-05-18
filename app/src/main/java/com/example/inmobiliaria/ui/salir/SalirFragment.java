package com.example.inmobiliaria.ui.salir;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.databinding.FragmentSalirBinding;

public class SalirFragment extends Fragment {

    private SalirViewModel mViewModel;
    private FragmentSalirBinding binding;

    public static SalirFragment newInstance() {
        return new SalirFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentSalirBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        muestraDialogo();


        return root;
    }

    private void muestraDialogo() {
        new com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
                .setTitle("Cierre de sesión")
                .setMessage("¿Estás seguro que deseas cerrar la sesión?")
                .setIcon(R.drawable.icon_logout)
                .setCancelable(false)
                .setPositiveButton("Sí, salir", (dialog, which) -> {

                    requireActivity().finish();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    androidx.navigation.Navigation.findNavController(requireView())
                            .navigate(R.id.nav_inicio);
                })
                .show();
    }
}


