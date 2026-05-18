package com.example.inmobiliaria.ui.inquilinos;

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
import com.example.inmobiliaria.databinding.FragmentInquilinosBinding;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.ui.adapters.InmuebleRecyclerAdapter;
import com.example.inmobiliaria.ui.inmuebles.InmuebleAdapter;

import java.util.List;

public class InquilinosFragment extends Fragment {

    private FragmentInquilinosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InquilinosViewModel inquilinosViewModel =
                new ViewModelProvider(this).get(InquilinosViewModel.class);

        binding = FragmentInquilinosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        inquilinosViewModel.getListaMutable().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                InmuebleRecyclerAdapter ia=new InmuebleRecyclerAdapter(requireContext(),
                        inmuebles,
                        getLayoutInflater(),
                        R.id.nav_detalle_inquilino);
                binding.listaInquilinos.setLayoutManager(new LinearLayoutManager(requireContext()));
                binding.listaInquilinos.setAdapter(ia);
            }
        });
        inquilinosViewModel.listarInquilinos();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}