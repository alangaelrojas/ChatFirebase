package com.apps.alangael.chatfirebase;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by AlanGael on 28/10/2017.
 */

public class HolderMensaje extends RecyclerView.ViewHolder {

    private TextView nombre;
    private TextView menssaje;
    private TextView hora;
    private CircleImageView fotomensaje;
    private ImageView fotoMensajeSend;

    public HolderMensaje (View itemview){
        super(itemview);
        nombre = (TextView) itemview.findViewById(R.id.txtNombreMensaje);
        menssaje = (TextView) itemview.findViewById(R.id.txtmensajeMensaje);
        hora =(TextView) itemview.findViewById(R.id.txthoraMensaje);
        fotomensaje = (CircleImageView) itemview.findViewById(R.id.fotomensaje);
        fotoMensajeSend = (ImageView) itemview.findViewById(R.id.MensajeFoto);
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getMenssaje() {
        return menssaje;
    }

    public void setMenssaje(TextView menssaje) {
        this.menssaje = menssaje;
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }

    public CircleImageView getFotomensaje() {
        return fotomensaje;
    }

    public void setFotomensaje(CircleImageView fotomensaje) {
        this.fotomensaje = fotomensaje;
    }

    public ImageView getFotoMensajeSend() {
        return fotoMensajeSend;
    }

    public void setFotoMensajeSend(ImageView fotoMensajeSend) {
        this.fotoMensajeSend = fotoMensajeSend;
    }
}
