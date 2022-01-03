package com.example.bibliotecareactiva.Mensaje;

import java.util.Date;

public class Mensaje {
        private boolean estado;
        private String mensaje;

        public Mensaje(boolean estado, String mensaje) {
            this.estado = estado;
            this.mensaje = mensaje;
        }

        public Mensaje() {
        }

        public boolean isEstado() {
            return estado;
        }

        public String getMensaje() {
            return mensaje;
        }

        public Mensaje imprimirMensajeDisponibilidad(Boolean disponibilidad , Date fechaPrestamo){
            if(disponibilidad){
            return new Mensaje(true,  "El material esta disponible");
        }
            return new Mensaje(false,("El material fue prestado el: "+fechaPrestamo));
        }
        public Mensaje imprimirMensajePrestamo(Boolean disponibilidad , Date fechaPrestamo){
        if(disponibilidad){
            return new Mensaje(true,  "El material esta disponible y te fue prestado");
        }
            return new Mensaje(false,("El material no esta disponible fue  prestado el: "+fechaPrestamo));
        }
    public Mensaje imprimirMensajeDevolucion(Boolean disponibilidad , Date fechaPrestamo){
        if(!disponibilidad){
            return new Mensaje(true,  "El material fue entregado con exito");
        }
        return new Mensaje(false,("El material no esta prestado" ));
    }

}

