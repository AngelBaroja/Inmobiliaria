package com.example.inmobiliaria.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.databinding.FragmentInmueblesBinding;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.ui.adapters.InmuebleRecyclerAdapter;

import java.util.List;

public class InmueblesFragment extends Fragment {

    private FragmentInmueblesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InmueblesViewModel vm =
                new ViewModelProvider(this).get(InmueblesViewModel.class);

        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getListaInmuebleM().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                InmuebleRecyclerAdapter ia=new InmuebleRecyclerAdapter(requireContext(),
                        inmuebles,
                        getLayoutInflater(),
                        R.id.nav_detalle_inmueble);
                binding.listaInmuebles.setAdapter(ia);
                binding.listaInmuebles.setLayoutManager(new LinearLayoutManager(requireContext()));
            }
        });
        vm.listarInmuebles();

        binding.fbAgregarInmueble.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.nav_agregar_inmueble);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}