package com.example.inmobiliaria.ui.adapters;

import android.content.Context;
import android.opengl.GLES30;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.inmobiliaria.R;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.modelo.Pago;

import java.util.List;

public class PagoRecyclerAdapter extends RecyclerView.Adapter<PagoRecyclerAdapter.ViewHolder> {

    private List<Pago> pagos;
    private Context context;
    private LayoutInflater inflater;
    private int idAccionNavegacion;

    public PagoRecyclerAdapter(Context context, List<Pago> pagos, LayoutInflater inflater, int idAccionNavegacion) {
        this.context = context;
        this.pagos = pagos;
        this.inflater = inflater;
        this.idAccionNavegacion = idAccionNavegacion;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_pago, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pago pago = pagos.get(position);

        holder.idPago.setText("Pago #" + pago.getIdPago());
        holder.fechaPago.setText(pago.getFechaPago());
        holder.montoPago.setText("$ " + pago.getMonto());
        holder.detallePago.setText(pago.getDetalle());
        holder.estadoPago.setText(pago.isEstado() ? "Pagado" : "Pendiente");

    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView idPago, fechaPago, montoPago, detallePago, estadoPago;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idPago = itemView.findViewById(R.id.tvIdPago);
            fechaPago = itemView.findViewById(R.id.tvFechaPago);
            montoPago = itemView.findViewById(R.id.tvMontoPago);
            detallePago = itemView.findViewById(R.id.tvDetallePago);
            estadoPago = itemView.findViewById(R.id.tvEstadoPago);

        }
    }
}