package com.example.inmobiliaria.ui.inmuebles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.modelo.Inmueble;

import java.util.List;

public class InmuebleAdapter extends ArrayAdapter<Inmueble> {
    List<Inmueble> inmuebles;
    private Context context;
    private LayoutInflater li;
    private int itemMostrar;

    public InmuebleAdapter(@NonNull Context context, int resource, @NonNull List<Inmueble> objects, LayoutInflater li) {
        super(context, resource, objects);
        this.inmuebles=objects;
        this.context=context;
        this.li=li;
        itemMostrar=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item=convertView;
        if(item==null){
            item=li.inflate(itemMostrar,parent,false);
        }
        ImageView foto=item.findViewById(R.id.imgInmueble);
        TextView direccion=item.findViewById(R.id.tvDireccion);
        TextView tipo=item.findViewById(R.id.tvTipo);
        TextView uso=item.findViewById(R.id.tvUso);
        TextView ambientes=item.findViewById(R.id.tvAmbientes);
        TextView valor=item.findViewById(R.id.tvValor);
        TextView estado=item.findViewById(R.id.tvEstado);

        Inmueble inmueble=inmuebles.get(position);
        //oto.setImageResource(inmueble.getImagen());
        direccion.setText(inmueble.getDireccion());
        tipo.setText(inmueble.getTipo());
        uso.setText("Uso: "+inmueble.getUso());
        ambientes.setText("Ambientes: "+inmueble.getAmbientes());
        valor.setText("$ "+inmueble.getValor());
        estado.setText(inmueble.isDisponible() ? "Disponible" : "No Disponible");
        item.setOnClickListener(v ->  {
            Bundle bundle = new Bundle();

            bundle.putInt("idInmueble", inmueble.getIdInmueble());

            Navigation.findNavController(v).navigate(R.id.nav_detalle_Contrato, bundle);
        });

        return item;
    }

    @Override
    public int getCount() {
        return inmuebles.size();
    }



}
