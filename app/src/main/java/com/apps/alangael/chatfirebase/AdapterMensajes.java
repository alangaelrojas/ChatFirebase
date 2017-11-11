package com.apps.alangael.chatfirebase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by AlanGael on 28/10/2017.
 */

public class AdapterMensajes extends RecyclerView.Adapter<HolderMensaje> {


    private List<MensajeRecibir> listMensaje = new ArrayList<>();
    private Context c;

    public AdapterMensajes( Context c) {
        this.c = c;
    }
    public void addmensaje (MensajeRecibir m){
        listMensaje.add(m);
        notifyItemInserted(listMensaje.size());
    }
    @Override
    public HolderMensaje onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_viewmensajes, parent,  false);

        return new HolderMensaje(v);
    }

    @Override
    public void onBindViewHolder(HolderMensaje holder, int position) {
        holder.getNombre().setText(listMensaje.get(position).getNombre());
        holder.getMenssaje().setText(listMensaje.get(position).getMensaje());
        //holder.getHora().setText(listMensaje.get(position).getHora());
        if (listMensaje.get(position).getType_mensaje().equals("2")){
            holder.getFotoMensajeSend().setVisibility(View.VISIBLE);
            holder.getMenssaje().setVisibility(View.VISIBLE);
            Glide.with(c).load(listMensaje.get(position).getUrlFoto()).into(holder.getFotoMensajeSend());
        }else if(listMensaje.get(position).getType_mensaje().equals("1")){
            holder.getFotoMensajeSend().setVisibility(View.GONE);
            holder.getMenssaje().setVisibility(View.VISIBLE);
        }
        if (listMensaje.get(position).getFotoperfil().isEmpty()){
            holder.getFotomensaje().setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(c).load(listMensaje.get(position).getFotoperfil()).into(holder.getFotomensaje());
        }
        Long codigoHora = listMensaje.get(position).getHora();
        Date d = new Date(codigoHora);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");// a pmm o am
        holder.getHora().setText(sdf.format(d));
    }

    @Override
    public int getItemCount() {
        return listMensaje.size();
    }
}
