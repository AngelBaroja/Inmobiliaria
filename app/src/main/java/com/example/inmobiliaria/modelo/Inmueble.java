package com.example.inmobiliaria.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Inmueble implements Serializable {

        private int idInmueble;
        private String direccion;
        private double valor;
        private int idPropietario;
        private String uso;
        private String tipo;
        private int ambientes;
        private int superficie;
        private String imagen;
        private boolean disponible;

        public Inmueble() {
        }

        public Inmueble(int idInmueble,
                        String direccion,
                        double valor,
                        int idPropietario,
                        String uso,
                        String tipo,
                        int ambientes,
                        int superficie,
                        String imagen,
                        boolean disponible) {

            this.idInmueble = idInmueble;
            this.direccion = direccion;
            this.valor = valor;
            this.idPropietario = idPropietario;
            this.uso = uso;
            this.tipo = tipo;
            this.ambientes = ambientes;
            this.superficie = superficie;
            this.imagen = imagen;
            this.disponible = disponible;
        }

        public int getIdInmueble() {
            return idInmueble;
        }

        public void setIdInmueble(int idInmueble) {
            this.idInmueble = idInmueble;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public double getValor() {
            return valor;
        }

        public void setValor(double valor) {
            this.valor = valor;
        }

        public int getIdPropietario() {
            return idPropietario;
        }

        public void setIdPropietario(int idPropietario) {
            this.idPropietario = idPropietario;
        }

        public String getUso() {
            return uso;
        }

        public void setUso(String uso) {
            this.uso = uso;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public int getAmbientes() {
            return ambientes;
        }

        public void setAmbientes(int ambientes) {
            this.ambientes = ambientes;
        }

        public int getSuperficie() {
            return superficie;
        }

        public void setSuperficie(int superficie) {
            this.superficie = superficie;
        }

        public String getImagen() {
            return imagen;
        }

        public void setImagen(String imagen) {
            this.imagen = imagen;
        }

        public boolean isDisponible() {
            return disponible;
        }

        public void setDisponible(boolean disponible) {
            this.disponible = disponible;
        }

}
