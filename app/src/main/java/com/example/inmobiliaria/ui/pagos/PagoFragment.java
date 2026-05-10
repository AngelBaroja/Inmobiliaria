package com.example.inmobiliaria.ui.pagos;

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
import com.example.inmobiliaria.databinding.FragmentPagoBinding;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.modelo.Pago;
import com.example.inmobiliaria.ui.contratos.ContratosViewModel;
import com.example.inmobiliaria.ui.inmuebles.InmuebleAdapter;

import java.util.List;


public class PagoFragment extends Fragment {

    private FragmentPagoBinding binding;
    private PagoFragmentViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(PagoFragmentViewModel.class);

        binding = FragmentPagoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm.getListaPagosMuteable().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                PagoAdapter pa=new PagoAdapter(requireContext(), R.layout.item_pago, pagos,getLayoutInflater());
                binding.lvListaPagos.setAdapter(pa);
            }
        });
        Bundle bundle = getArguments();
        int idContrato = bundle.getInt("idContrato");
        vm.MostrarPagos(idContrato);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}