package com.apps.alangael.chatfirebase;

import java.util.Map;

/**
 * Created by AlanGael on 28/10/2017.
 */

public class MensajeEnviar extends Mensaje {
    private Map hora;

   public MensajeEnviar(){

   }
    public MensajeEnviar(Map hora){

    }

    public MensajeEnviar(String mensaje, String nombre, String fotoperfil, String type_mensaje, Map hora) {
        super(mensaje, nombre, fotoperfil, type_mensaje);
        this.hora = hora;
    }

    public MensajeEnviar(String mensaje, String urlFoto, String nombre, String fotoperfil, String type_mensaje, Map hora) {
        super(mensaje, urlFoto, nombre, fotoperfil, type_mensaje);
        this.hora = hora;
    }

    public Map getHora() {
        return hora;
    }

    public void setHora(Map hora) {
        this.hora = hora;
    }
}
