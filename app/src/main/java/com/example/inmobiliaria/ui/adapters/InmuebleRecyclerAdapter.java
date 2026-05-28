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
import com.example.inmobiliaria.request.ApiClient;
import com.example.inmobiliaria.request.ApiService;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class InmuebleRecyclerAdapter extends RecyclerView.Adapter<InmuebleRecyclerAdapter.ViewHolder> {

    private List<Inmueble> inmuebles;
    private Context context;
    private LayoutInflater inflater;
    private int idAccionNavegacion;


    public InmuebleRecyclerAdapter(Context context, List<Inmueble> inmuebles, LayoutInflater inflater, int idAccionNavegacion) {
        this.context = context;
        this.inmuebles = inmuebles;
        this.inflater = inflater;
        this.idAccionNavegacion = idAccionNavegacion;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inmueble inmueble = inmuebles.get(position);

        NumberFormat nf = NumberFormat.getInstance(new Locale("es", "AR"));
        String valorFormateado = nf.format(inmueble.getValor());

        holder.direccion.setText(inmueble.getDireccion());
        holder.tipo.setText(inmueble.getTipo());
        holder.uso.setText("Uso: " + inmueble.getUso());
        holder.ambientes.setText("Ambientes: " + inmueble.getAmbientes());
        holder.valor.setText("$ " + valorFormateado);
        holder.estado.setText(inmueble.isDisponible() ? "Disponible" : "No Disponible");


        String ruta = inmueble.getImagen().replace("\\", "/");  // Algunas vienen con // entonces la reemplazo para GLIDE
        if (!ruta.startsWith("/")) {
            ruta = "/" + ruta;
        }
        String urlImagen = ApiClient.url + ruta;
        Glide.with(context)
                .load(urlImagen)
                .placeholder(R.drawable.ic_camera_black_24dp)
                .error(R.drawable.ic_slideshow_black_24dp)
                .into(holder.foto);


        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("idInmueble", inmueble.getIdInmueble());
            bundle.putSerializable("inmueble", inmueble);
            Navigation.findNavController(v).navigate(idAccionNavegacion, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView direccion, tipo, uso, ambientes, valor, estado;
        private ImageView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.imgInmueble);
            direccion = itemView.findViewById(R.id.tvDireccion);
            tipo = itemView.findViewById(R.id.tvTipo);
            uso = itemView.findViewById(R.id.tvUso);
            ambientes = itemView.findViewById(R.id.tvAmbientes);
            valor = itemView.findViewById(R.id.tvValor);
            estado = itemView.findViewById(R.id.tvEstado);
        }
    }
}