package com.example.inmobiliaria.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.databinding.FragmentDetalleInquilinoBinding;
import com.example.inmobiliaria.modelo.Contrato;

public class DetalleInquilinoFragment extends Fragment {

    private DetalleInquilinoViewModel vm;
    private FragmentDetalleInquilinoBinding binding;

    public static DetalleInquilinoFragment newInstance() {
        return new DetalleInquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(DetalleInquilinoViewModel.class);
        binding = FragmentDetalleInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getInquilinoM().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                binding.tvInquilinoNombre.setText(contrato.getInquilino().getNombre()
                        + " " + contrato.getInquilino().getApellido());
                binding.tvInquilinoDni.setText(contrato.getInquilino().getDni() + "");
                binding.tvInquilinoEmail.setText(contrato.getInquilino().getEmail());
                binding.tvInquilinoTelefono.setText(contrato.getInquilino().getTelefono());
            }
        });
        Bundle bundle = getArguments();
        if(bundle != null){
            int idInmueble = bundle.getInt("idInmueble");
            vm.detalleInquilino(idInmueble);
        } else {
            Navigation.findNavController(root).navigate(R.id.nav_inquilinos);
            Toast.makeText(getContext(), "No se encontraron datos", Toast.LENGTH_SHORT).show();
        }

        binding.btnVolver.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.nav_inquilinos);
        });
        return root;
    }


}