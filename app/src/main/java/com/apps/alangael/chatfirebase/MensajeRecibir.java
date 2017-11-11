package com.apps.alangael.chatfirebase;

/**
 * Created by AlanGael on 28/10/2017.
 */

public class MensajeRecibir extends Mensaje {
    private Long hora;

    public MensajeRecibir() {
    }

    public MensajeRecibir(Long hora) {
        this.hora = hora;
    }

    public MensajeRecibir(String mensaje, String urlFoto, String nombre, String fotoperfil, String type_mensaje, Long hora) {
        super(mensaje, urlFoto, nombre, fotoperfil, type_mensaje);
        this.hora = hora;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }
}
