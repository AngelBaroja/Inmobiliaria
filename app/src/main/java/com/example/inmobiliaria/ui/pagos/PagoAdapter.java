package com.example.inmobiliaria.ui.pagos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.inmobiliaria.databinding.ItemPagoBinding;
import com.example.inmobiliaria.modelo.Pago;

import java.util.List;

public class PagoAdapter extends ArrayAdapter<Pago> {

    private List<Pago> pagos;
    private Context context;

    private int itemMostrar;
    private LayoutInflater li;

    public PagoAdapter(@NonNull Context context,int resource,@NonNull List<Pago> objects,LayoutInflater li) {
        super(context, resource, objects);

        this.pagos = objects;
        this.context = context;
        this.li = li;
        itemMostrar=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ItemPagoBinding binding;

        if(convertView == null){

            binding = ItemPagoBinding.inflate(li, parent, false);

            convertView = binding.getRoot();

            convertView.setTag(binding);

        }else{

            binding = (ItemPagoBinding) convertView.getTag();
        }

        Pago pago = pagos.get(position);

        binding.tvIdPago.setText("Pago #" + pago.getIdPago());
        binding.tvFechaPago.setText(pago.getFechaPago());
        binding.tvMontoPago.setText("$ " + pago.getMonto());
        binding.tvDetallePago.setText(pago.getDetalle());

        binding.tvEstadoPago.setText(pago.isEstado() ? "Pagado" : "Pendiente");

        return convertView;
    }

    @Override
    public int getCount() {
        return pagos.size();
    }
}