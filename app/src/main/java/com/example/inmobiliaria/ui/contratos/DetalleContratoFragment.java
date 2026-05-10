package com.example.inmobiliaria.ui.contratos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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
    private DetalleContratoFragmentViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(DetalleContratoFragmentViewModel.class);

        binding = FragmentDetalleContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm.getListaContratoMutable().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                // -------- CONTRATO --------

                binding.tvIdContrato.setText(
                        contrato.getIdContrato()+"");

                binding.tvFechaInicio.setText(
                        contrato.getFechaInicio());

                binding.tvFechaFin.setText(
                        contrato.getFechaFinalizacion());

                binding.tvMonto.setText(
                        "$ " + contrato.getMontoAlquiler());

                binding.tvEstado.setText(
                        contrato.isEstado() ? "Activo" : "Finalizado");


                // -------- INQUILINO --------
                /*
                binding.tvNombreInquilino.setText(
                        contrato.getInquilino().getNombre() + " " +
                                contrato.getInquilino().getApellido());

                binding.tvDni.setText(
                        "DNI: " + contrato.getInquilino().getDni());

                binding.tvTelefono.setText(
                        "Teléfono: " + contrato.getInquilino().getTelefono());

                binding.tvEmail.setText(
                        "Email: " + contrato.getInquilino().getEmail());
                 */

                // -------- INMUEBLE --------

                binding.tvDireccion.setText(
                        contrato.getInmueble().getDireccion());

                binding.tvTipo.setText(
                        contrato.getInmueble().getTipo());

                binding.tvUso.setText(
                        "Uso: " + contrato.getInmueble().getUso());

                binding.tvAmbientes.setText(
                        "Ambientes: " + contrato.getInmueble().getAmbientes());
                binding.btnPagos.setOnClickListener(v -> {
                    Bundle bundle = new Bundle();

                    bundle.putInt("idContrato", contrato.getIdContrato());

                    Navigation.findNavController(v).navigate(R.id.nav_pago, bundle);
                });
            }
        });
        //------Paso el id del inmueble a travez del Bundle
        Bundle bundle = getArguments();
        int idInmueble = bundle.getInt("idInmueble");
        vm.listarContratosDeInmueble(idInmueble);



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}