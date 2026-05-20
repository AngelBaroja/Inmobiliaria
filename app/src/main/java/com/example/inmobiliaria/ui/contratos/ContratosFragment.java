package com.example.inmobiliaria.ui.contratos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.databinding.FragmentContratosBinding;
import com.example.inmobiliaria.modelo.Contrato;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.ui.adapters.InmuebleRecyclerAdapter;
import com.example.inmobiliaria.ui.inmuebles.InmuebleAdapter;

import java.util.List;

public class ContratosFragment extends Fragment {

    private FragmentContratosBinding binding;
    private ContratosViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(ContratosViewModel.class);

        binding = FragmentContratosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm.getListaContratoMutable().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                InmuebleRecyclerAdapter ia=new InmuebleRecyclerAdapter(requireContext(),
                        inmuebles,
                        getLayoutInflater(),
                        R.id.nav_detalle_Contrato);
                binding.listaInmueblesConContrato.setAdapter(ia);
                binding.listaInmueblesConContrato.setLayoutManager(new LinearLayoutManager(requireContext()));
            }
        });

        vm.ListaInmuebleConContrato();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}