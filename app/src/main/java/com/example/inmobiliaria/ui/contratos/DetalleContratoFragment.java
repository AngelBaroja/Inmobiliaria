package com.example.inmobiliaria.ui.contratos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.databinding.FragmentContratosBinding;
import com.example.inmobiliaria.databinding.FragmentDetalleContratoBinding;
import com.example.inmobiliaria.modelo.Contrato;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.ui.inmuebles.InmuebleAdapter;

import java.util.List;

public class DetalleContratoFragment extends Fragment {
    private FragmentDetalleContratoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DetalleContratoFragmentViewModel contratosViewModel = new ViewModelProvider(this).get(DetalleContratoFragmentViewModel.class);

        binding = FragmentDetalleContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        contratosViewModel.getListaContratoMutable().observe(getViewLifecycleOwner(), new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> contrato) {


            }
        });
        //------Paso el id del inmueble a travez del Bundle
        Bundle bundle = getArguments();
        if(bundle != null) {
            int idInmueble = bundle.getInt("idInmueble");

            contratosViewModel.listarContratosDeInmueble(idInmueble);
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}